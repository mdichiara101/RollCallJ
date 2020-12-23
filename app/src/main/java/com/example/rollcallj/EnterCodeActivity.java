package com.example.rollcallj;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class EnterCodeActivity extends AppCompatActivity /*implements ValueEventListener*/{

    private EditText editKeyValue;
    //private Button goToNext;
    private String txt;
    //private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private DatabaseReference classroom;
    String key;
    private String classkey,studentkeys,studentemail,location;
    private String Rnum, Fname, Lname;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference("user");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_key);
        Intent intent = getIntent();
        classkey = intent.getStringExtra("classkey");
        studentkeys = intent.getStringExtra("student");
        studentemail = intent.getStringExtra("email");
        //key = classkey;
        editKeyValue = findViewById(R.id.editKeyValue);
        root = firebaseDatabase.getReference().child("Classes");


        //goToNext = findViewById(R.id.goToNext);
    }

    public void submit(View view) {
        final String txt_code = editKeyValue.getText().toString();
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.hasChild(txt_code)) {
                        Toast.makeText(EnterCodeActivity.this, "Class Found!", Toast.LENGTH_SHORT).show();
                        location = txt_code;
                        addstudent();
                        Toast.makeText(EnterCodeActivity.this, "Registered For Class!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(EnterCodeActivity.this, "Class Not Found!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

    private void addstudent() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(studentemail)) {
                        Rnum = ds.child("rnumber").getValue(String.class);
                        //Toast.makeText(MainActivity.this, Rnum, Toast.LENGTH_SHORT).show();
                        Fname = ds.child("first_Name").getValue(String.class);
                        //Toast.makeText(MainActivity.this, Fname, Toast.LENGTH_SHORT).show();
                        Lname = ds.child("last_Name").getValue(String.class);
                        //Toast.makeText(MainActivity.this, Lname, Toast.LENGTH_SHORT).show();
                    }
                }
                HashMap<String, Object> classinfo = new HashMap<>();
                classinfo.put("First_Name",Fname);
                classinfo.put("Last_Name",Lname);
                classinfo.put("Email",studentemail);
                classinfo.put("RNumber",Rnum);
                //Toast.makeText(EnterCodeActivity.this, studentkeys, Toast.LENGTH_SHORT).show();
                //Toast.makeText(EnterCodeActivity.this, location, Toast.LENGTH_SHORT).show();
                root.child(location).child("Students").child(studentkeys).updateChildren(classinfo);
                //Toast.makeText(EnterCodeActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

/*
        goToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String txt_code = editKeyValue.getText().toString();
                if (txt_code.equals("QW289")){
                    //if txt_code = teachers code then

                    //if location activated go to location sign in or

                    //go to success
                    Toast.makeText(EnterCodeActivity.this, "Attendance successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EnterCodeActivity.this, KeySuccess.class));
                } else if(txt_code.isEmpty()){
                    Toast.makeText(EnterCodeActivity.this, "Missing code!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EnterCodeActivity.this, "Wrong code!", Toast.LENGTH_SHORT).show();
                }
                /// add on
 //               reference.child("Classes").child("QW289").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        //ClassInformation info = dataSnapshot.getValue(ClassInformation.class);
//                        if (dataSnapshot.getValue(String.class)!= null){
//                            String key = dataSnapshot.getKey();
//                            if(key.equals("RegistrationKey")){
//                                String reg_key = dataSnapshot.getValue(String.class);
//                                if (txt_code.equals(reg_key)){
//                                    //if txt_code = teachers code then
//
//                                    //if location activated go to location sign in or
//
//                                    //go to success
//                                    startActivity(new Intent(EnterCodeActivity.this, KeySuccess.class));
//                                }
//
//
//
//                            }
//                        }
//                    }

//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });



                // else
                    // failed toast
            }
        });


    }
*/


}
