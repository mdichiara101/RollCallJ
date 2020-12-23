package com.example.rollcallj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClassViewActivity extends AppCompatActivity implements ValueEventListener {

    private TextView title;
    private TextView className;
    private TextView startTime;
    private TextView endTime;
    private TextView days;
    private Button signin,suprise;
    private String titles, starttime, endtime, day;
    private FirebaseDatabase database;
    private String classkey,studentkey,method,time;
    private DatabaseReference classref, studentref, root, ClassNameinfo, StartTimeinfo, EndTimeinfo, daysinfo,attendancetyperef,facial,GPS,keyCode,surprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view);

        Intent intent = getIntent();
        classkey = intent.getStringExtra("ClassKey");
        studentkey = intent.getStringExtra("student");

        database = FirebaseDatabase.getInstance();
        //Toast.makeText(this, classkey, Toast.LENGTH_SHORT).show();
        attendancetyperef = database.getReference().child("Classes").child(classkey).child("AttendanceType");
        surprise = database.getReference().child("Classes").child(classkey).child("AttendanceType").child("SurpriseTime");
        root = database.getReference().child("Classes").child(classkey);

        ClassNameinfo = root.child("Name");
        StartTimeinfo = root.child("StartTime");
        EndTimeinfo = root.child("EndTime");
        daysinfo = root.child("Days");
        facial = attendancetyperef.child("Facial");
        GPS = attendancetyperef.child("GPS");
        keyCode = attendancetyperef.child("keyCode");

        title = findViewById(R.id.title);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        days = findViewById(R.id.days);
        signin = findViewById(R.id.signin);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnterCode(classkey, studentkey);
            }
        });
        
    }



    private void openEnterCode(String classkey, String studentkey) {
        switch(method){
            case "GPS":
                Toast.makeText(this, "GPS", Toast.LENGTH_SHORT).show();
                openMethodGPS(classkey,studentkey);
                finish();
                break;
            case "Facial":
                Toast.makeText(this, "Facial", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case "keyCode":
                Toast.makeText(this, "KeyCode", Toast.LENGTH_SHORT).show();
                openMethodkeykode(classkey,studentkey);
                finish();
                break;
        }
    }

    private void openMethodkeykode(String classk,String student) {
        Intent intent;
        intent = new Intent(this,keycodecheckin.class);
        intent.putExtra("classkey",classk);
        intent.putExtra("student",student);
        startActivity(intent);
    }
    private void openMethodGPS(String classk,String student) {
        Intent intent;
        intent = new Intent(this,Ping.class);
        intent.putExtra("classkey",classk);
        intent.putExtra("student",student);
        startActivity(intent);
    }


    @Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
    if(dataSnapshot.getValue(String.class)!=null){
        String key = dataSnapshot.getKey();
        String val = dataSnapshot.getValue().toString();
        if(key.equals("Name"))
        {
            String heading = dataSnapshot.getValue(String.class);
            title.setText(heading);
        }else if(key.equals("StartTime"))
        {
            String heading = dataSnapshot.getValue(String.class);
            startTime.setText(heading);
        }else if(key.equals("EndTime"))
        {
            String heading = dataSnapshot.getValue(String.class);
            endTime.setText(heading);
        }else if(key.equals("Days"))
        {
            String heading = dataSnapshot.getValue(String.class);
            days.setText(heading);
        }
        else if(key.equals("Facial") && val.equals("On"))
        {
            method="Facial";
            Toast.makeText(ClassViewActivity.this, method, Toast.LENGTH_SHORT).show();
        }else if (key.equals("GPS") && val.equals("On")){
            method="GPS";
            Toast.makeText(ClassViewActivity.this, method, Toast.LENGTH_SHORT).show();
            //openMethodGPS(classkey,studentkey);
            //finish();
        }else if(key.equals("keyCode") && val.equals("On")){
            method="keyCode";
            Toast.makeText(ClassViewActivity.this, method, Toast.LENGTH_SHORT).show();
            //openMethodkeykode(classkey,studentkey);
            //finish();
        }else if(key.equals("SurpriseTime")){
            time = val;
        }
    }
}

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        ClassNameinfo.addValueEventListener(this);
        StartTimeinfo.addValueEventListener(this);
        EndTimeinfo.addValueEventListener(this);
        daysinfo.addValueEventListener(this);
        facial.addValueEventListener(this);
        GPS.addValueEventListener(this);
        keyCode.addValueEventListener(this);
    }

}
