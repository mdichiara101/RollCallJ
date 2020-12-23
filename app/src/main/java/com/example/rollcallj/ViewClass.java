package com.example.rollcallj;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewClass extends AppCompatActivity {
    private Button addStudent,classinfo;
    private ListView listview;
    private TextView classtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");

        listview = findViewById(R.id.listview);
        addStudent = findViewById(R.id.add);
        classtitle = findViewById(R.id.classkey);
        classinfo = findViewById(R.id.classinfo);
        classtitle.setText("");
        classtitle.setText(classkey);


        addStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openaddstudent(classkey);
            }
        } );

        classinfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openclassinfo(classkey);
            }
        } );



        final ArrayList<String> list =new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item,list);
        listview.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("Students");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                //all data
                //for(DataSnapshot snapshot : dataSnapshot.getChildren())
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    StudentInformation info = snapshot.getValue(StudentInformation.class);
                    String FirstName = info.getFirst_Name();
                    String LastName = info.getLast_Name();
                    String RNumber = info.getRNumber();
                    String StudentDatabase =RNumber+ " : " +FirstName+ " " + LastName;
                    list.add(StudentDatabase);
                    //all
                    //list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String ClassKey = "CS3456-001";
                        // openClass(ClassKey);
                        String selectedItem = (String) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                        openstudentinfo(selectedItem,classkey);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void openclassinfo(String classkey) {
        Intent intent;
        intent = new Intent(this, individual_Class_Info.class);
        intent.putExtra("ClassKey", classkey);
        startActivity(intent);
    }

    private void openaddstudent(String classkey) {
        Intent intent;
        intent = new Intent(this, Addstudent.class);
        intent.putExtra("ClassKey", classkey);
        startActivity(intent);
    }
    private void openstudentinfo(String studentkey,String classkey) {
        Intent intent;
        intent = new Intent(this, Individual_Student_Information.class);
        intent.putExtra("ClassKey", classkey);
        intent.putExtra("StudentKey", studentkey);
        startActivity(intent);
    }
}
