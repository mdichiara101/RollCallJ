package com.example.rollcallj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Individual_Student_Information extends AppCompatActivity implements ValueEventListener {

    private EditText fNameUpdate,lNameUpdate,RNumUpdate,emailUpdate;
    private TextView email,titleRnum,titleLName,titleFName;
    private Button changefName,changelName,changeRnum,changeEmail,changePhoto,studentAttendance;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private DatabaseReference studentFnameinfo;
    private DatabaseReference studentLnameinfo;
    private DatabaseReference studentEmailinfo;
    private DatabaseReference studentRNuminfo;
    private DatabaseReference photoid;
    private String keyroot,studentkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual__student__information);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");
        final String studentkey = intent.getStringExtra("StudentKey");
        root = firebaseDatabase.getReference().child("Classes").child(classkey).child("Students").child(studentkey);
        studentFnameinfo = root.child("First_Name");
        studentLnameinfo = root.child("Last_Name");
        studentEmailinfo = root.child("Email");
        studentRNuminfo = root.child("RNumber");
        keyroot = classkey;

        titleFName = (TextView) findViewById(R.id.titleFName);
        titleLName = (TextView) findViewById(R.id.titleLName);
        titleRnum = (TextView) findViewById(R.id.titleRnum);
        email = (TextView) findViewById(R.id.email);
        fNameUpdate = (EditText) findViewById(R.id.fNameUpdate);
        lNameUpdate = (EditText) findViewById(R.id.lNameUpdate);
        RNumUpdate = (EditText) findViewById(R.id.RNumUpdate);
        emailUpdate = (EditText) findViewById(R.id.emailUpdate);
        changefName = (Button) findViewById(R.id.changefName);
        changelName = (Button) findViewById(R.id.changelName);
        changeRnum = (Button) findViewById(R.id.changeRnum);
        changeEmail = (Button) findViewById(R.id.changeEmail);
        changePhoto = (Button) findViewById(R.id.changePhoto);
        studentAttendance = (Button) findViewById(R.id.studentAttendance);



    }
    public void StudentAttendanceSheet(View view) {
        Toast.makeText(this, "Student Attendance Clicked", Toast.LENGTH_SHORT).show();

    }

    public void updatePhoto(View view) {
    }

    public void updateEmail(View view) {
        String email = emailUpdate.getText().toString();
        if(!email.equals("")) {
            Toast.makeText(this, "Email Updated", Toast.LENGTH_SHORT).show();
            studentEmailinfo.setValue(email);
            emailUpdate.setText("");
        }else {
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateRNumber(View view) {
        String R = RNumUpdate.getText().toString();
        if(!R.equals(""))
        {
            Toast.makeText(this, "Last Name Updated", Toast.LENGTH_SHORT).show();
            studentRNuminfo.setValue(R);
            RNumUpdate.setText("");
        }else {
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateLname(View view) {
        String Lname = lNameUpdate.getText().toString();
        if(!Lname.equals(""))
        {
            Toast.makeText(this, "Last Name Updated", Toast.LENGTH_SHORT).show();
            studentLnameinfo.setValue(Lname);
            lNameUpdate.setText("");
        }else {
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateFname(View view) {
        String Fname = fNameUpdate.getText().toString();
        if(!Fname.equals("")) {
            Toast.makeText(this, "First Name Updated", Toast.LENGTH_SHORT).show();
            studentFnameinfo.setValue(Fname);
            fNameUpdate.setText("");
        }else{
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
        //studentFnameinfo.setValue();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue(String.class)!=null){
            String key = dataSnapshot.getKey();
            if(key.equals("First_Name"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleFName.setText(heading);
            }else if(key.equals("Last_Name"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleLName.setText(heading);
            }else if(key.equals("Email"))
            {
                String heading = dataSnapshot.getValue(String.class);
                email.setText(heading);
            }else if(key.equals("RNumber"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleRnum.setText(heading);
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        studentEmailinfo.addValueEventListener(this);
        studentFnameinfo.addValueEventListener(this);
        studentLnameinfo.addValueEventListener(this);
        studentRNuminfo.addValueEventListener(this);
    }

}
