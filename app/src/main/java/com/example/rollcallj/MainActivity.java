package com.example.rollcallj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private Button logout;
    private EditText edit;
    private Button add;
    private ListView listView;
    private Button addClass,info;
    private String txtemail,Fname,Lname,Rnum;
    private static final String USER = "user";
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String classinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");
        txtemail = intent.getStringExtra("email");
        logout = findViewById(R.id.LOGOUT);
       // edit = findViewById(R.id.edit);
        //add = findViewById(R.id.add);
        listView = findViewById(R.id.listview);
        addClass = findViewById(R.id.addClass);
        info = findViewById(R.id.userinfo);
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USER);
        //Toast.makeText(this, txtemail, Toast.LENGTH_LONG).show();

       // Toast.makeText(this, Rnum + " : " + Fname + " " + Lname, Toast.LENGTH_SHORT).show();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(txtemail)) {
                        Rnum = ds.child("rnumber").getValue(String.class);
                        //Toast.makeText(MainActivity.this, Rnum, Toast.LENGTH_SHORT).show();
                        Fname = ds.child("first_Name").getValue(String.class);
                        //Toast.makeText(MainActivity.this, Fname, Toast.LENGTH_SHORT).show();
                        Lname = ds.child("last_Name").getValue(String.class);
                        //Toast.makeText(MainActivity.this, Lname, Toast.LENGTH_SHORT).show();
                        //openclassinfo(classkey,Rnum,Fname,Lname);
                        //Toast.makeText(MainActivity.this, Lname, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"Logged out!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent profile = new Intent(MainActivity.this, student_profile.class);
                //Toast.makeText(MainActivity.this, "made it here: "+ txtemail, Toast.LENGTH_SHORT).show();
                profile.putExtra("email",txtemail);
                startActivity(profile);
            }
        });


        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        /*
        * Log.e("Count " ,""+snapshot.getChildrenCount());
          for (DataSnapshot postSnapshot: snapshot.getChildren()) {
            <YourClass> post = postSnapshot.getValue(<YourClass>.class);
            Log.e("Get Data", post.<YourMethod>());
          }
      }
        *
        * */
// this checks to see if a student if in a class and display those classes
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Classes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Classes").child(snapshot.getKey()).child("Students");
                    DataSnapshot studentref = snapshot.child("Students");
                    Iterable<DataSnapshot> studentin = studentref.getChildren();
                    for (DataSnapshot student : studentin) {
                        if(student.getKey().equals(Rnum + " : " + Fname + " " + Lname)){
                            ClassInformation info = snapshot.getValue(ClassInformation.class);
                            String txt = info.getRegistrationKey();
                            list.add(txt);
                        }else{
                            //list.add(student.getKey());
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = (String) parent.getItemAtPosition(position);
                        String key = Rnum + " : " + Fname + " " + Lname;
                        //Toast.makeText(MainActivity.this, key, Toast.LENGTH_SHORT).show();
                        //openclassinfo(classkey,Rnum + " : " + Fname + " " + Lname);
                       openviewclass(selectedItem,Rnum + " : " + Fname + " " + Lname);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.child("email").getValue().equals(txtemail)) {
                                Rnum = ds.child("rnumber").getValue(String.class);
                                Toast.makeText(MainActivity.this, Rnum, Toast.LENGTH_SHORT).show();
                                Fname = ds.child("first_Name").getValue(String.class);
                                Toast.makeText(MainActivity.this, Fname, Toast.LENGTH_SHORT).show();
                                Lname = ds.child("last_Name").getValue(String.class);
                                Toast.makeText(MainActivity.this, Lname, Toast.LENGTH_SHORT).show();
                                openclassinfo(classkey,Rnum,Fname,Lname);
                                Toast.makeText(MainActivity.this, Lname, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }

    private void openviewclass(String selectedItem, String student) {
        Intent intent;
        intent = new Intent(this, ClassViewActivity.class);
        intent.putExtra("ClassKey", selectedItem);
        Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show();
        intent.putExtra("student",student);
        Toast.makeText(this, student, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    private void openclassinfo(String classkey,String Rnum, String Fname, String Lname) {
        Intent intent;
        String student = Rnum + " : " + Fname + " " + Lname;
        intent = new Intent(this, EnterCodeActivity.class);
        intent.putExtra("ClassKey", classkey);
        intent.putExtra("student",student);
        Toast.makeText(this, student, Toast.LENGTH_SHORT).show();
        intent.putExtra("email",txtemail);
        startActivity(intent);
    }

}
