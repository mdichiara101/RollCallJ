package com.example.rollcallj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class individual_Class_Info extends AppCompatActivity implements ValueEventListener {
    private TextView titleClassName,titleregistrationkey,titlestartTime,titleendtime,titledays,titlestartDay,titleEndday,titleAttendanceKey,titleAttendanceStart,titleAttendanceEnd;
    private EditText classNameUpdate,regKeyUpdate,sTimeUpdate,eTimeUpdate,daysUpdate,sDayUpdate,eDayUpdate;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private DatabaseReference root2;
    private DatabaseReference root3;
    private DatabaseReference ClassNameinfo;
    private DatabaseReference ClassRegistrationKeyinfo;
    private DatabaseReference StartTimeinfo;
    private DatabaseReference StartDayinfo;
    private DatabaseReference EndDayinfo;
    private DatabaseReference EndTimeinfo;
    private DatabaseReference daysinfo;
    private DatabaseReference Attendancekeycodeinfo;
    private DatabaseReference Attendancestartinfo;
    private DatabaseReference Attendanceendinfo;
    private DatabaseReference Registrationfolderinfo;
    private String keyroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual__class__info);
        //get current class key
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");
        //database assigns
        root = firebaseDatabase.getReference().child("Classes").child(classkey);
        root2 = firebaseDatabase.getReference().child("Classes").child(classkey).child("AttendanceKeyInfo");
        root3 = firebaseDatabase.getReference().child("Classes");
        ClassNameinfo = root.child("Name");
        ClassRegistrationKeyinfo = root.child("RegistrationKey");
        StartTimeinfo = root.child("StartTime");
        EndTimeinfo = root.child("EndTime");
        StartDayinfo = root.child("Start_Day");
        EndDayinfo = root.child("End_Day");
        daysinfo = root.child("End_Day");
        Attendancekeycodeinfo = root2.child("KeyCode");
        Attendancestartinfo = root2.child("startTime");
        Attendanceendinfo = root2.child("endTime");
        Registrationfolderinfo = root3.child(classkey);
        keyroot = classkey;

        //text view assigns
        titleClassName = (TextView) findViewById(R.id.titleClassName);
        titleregistrationkey = (TextView) findViewById(R.id.titleregistrationkey);
        titlestartTime = (TextView) findViewById(R.id.titlestartTime);
        titleendtime = (TextView) findViewById(R.id.titleendtime);
        titledays = (TextView) findViewById(R.id.titledays);
        titlestartDay = (TextView) findViewById(R.id.titlestartDay);
        titleEndday = (TextView) findViewById(R.id.titleEndday);
        titleAttendanceKey = (TextView) findViewById(R.id.titleAttendanceKey);
        titleAttendanceStart = (TextView) findViewById(R.id.titleAttendanceStart);
        titleAttendanceEnd = (TextView) findViewById(R.id.titleAttendanceEnd);
        //edittext assigns
        classNameUpdate = (EditText) findViewById(R.id.classNameUpdate);
        regKeyUpdate = (EditText) findViewById(R.id.regKeyUpdate);
        sTimeUpdate = (EditText) findViewById(R.id.sTimeUpdate);
        eTimeUpdate = (EditText) findViewById(R.id.eTimeUpdate);
        daysUpdate = (EditText) findViewById(R.id.daysUpdate);
        sDayUpdate = (EditText) findViewById(R.id.sDayUpdate);
        eDayUpdate = (EditText) findViewById(R.id.eDayUpdate);


    }

    public void updateClassName(View view) {
        String key = classNameUpdate.getText().toString();
        if(!key.equals("")) {
            Toast.makeText(this, "Class Name Updated", Toast.LENGTH_SHORT).show();
            ClassNameinfo.setValue(key);
            titleClassName.setText("");
        }else{
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateRegKey(View view) {
        String key = regKeyUpdate.getText().toString();
        if(!key.equals("")) {
            Toast.makeText(this, "Registration Key Updated", Toast.LENGTH_SHORT).show();
            //FirebaseDatabase.getInstance().getReference().child("Classes").child(txt_Regkey).updateChildren(classinfo);
            // Registrationfolderinfo.child(keyroot).setValue(key);
            ClassRegistrationKeyinfo.setValue(key);
            titleregistrationkey.setText("");
        }else{
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }





    public void updateSTime(View view) {
        String key = sTimeUpdate.getText().toString();
        if(!key.equals("")) {
            Toast.makeText(this, "Start Time Updated", Toast.LENGTH_SHORT).show();
            StartTimeinfo.setValue(key);
            titlestartTime.setText("");
        }else{
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateeTime(View view) {
        String key = eTimeUpdate.getText().toString();
        if(!key.equals("")) {
            Toast.makeText(this, "End Time Updated", Toast.LENGTH_SHORT).show();
            EndTimeinfo.setValue(key);
            titleendtime.setText("");
        }else{
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDays(View view) {
        String key = daysUpdate.getText().toString();
        if(!key.equals("")) {
            Toast.makeText(this, "Days Updated", Toast.LENGTH_SHORT).show();
            daysinfo.setValue(key);
            titledays.setText("");
        }else{
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateSDay(View view) {
        String key = sDayUpdate.getText().toString();
        if(!key.equals("")) {
            Toast.makeText(this, "Start Day Updated", Toast.LENGTH_SHORT).show();
            StartDayinfo.setValue(key);
            titlestartDay.setText("");
        }else{
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateeDay(View view) {
        String key = eDayUpdate.getText().toString();
        if(!key.equals("")) {
            Toast.makeText(this, "Class Name Updated", Toast.LENGTH_SHORT).show();
            EndDayinfo.setValue(key);
            titleEndday.setText("");
        }else{
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateAttendanceKey(View view) {
        Intent intent;
        intent = new Intent(this, setClassKey.class);
        intent.putExtra("ClassKey", keyroot);
        startActivity(intent);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue(String.class)!=null){
            String key = dataSnapshot.getKey();
            if(key.equals("Name"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleClassName.setText(heading);
            }else if(key.equals("RegistrationKey"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleregistrationkey.setText(heading);
            }else if(key.equals("Days"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titledays.setText(heading);
            }else if(key.equals("StartTime"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titlestartTime.setText(heading);
            }else if(key.equals("EndTime"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleendtime.setText(heading);
            }else if(key.equals("Start_Day"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titlestartDay.setText(heading);
            }else if(key.equals("End_Day"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleEndday.setText(heading);
            }else if(key.equals("KeyCode"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleAttendanceKey.setText(heading);
            }else if(key.equals("endTime"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleAttendanceEnd.setText(heading);
            }else if(key.equals("startTime"))
            {
                String heading = dataSnapshot.getValue(String.class);
                titleAttendanceStart.setText(heading);
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
        ClassRegistrationKeyinfo.addValueEventListener(this);
        StartTimeinfo.addValueEventListener(this);
        EndTimeinfo.addValueEventListener(this);
        StartDayinfo.addValueEventListener(this);
        EndDayinfo.addValueEventListener(this);
        daysinfo.addValueEventListener(this);
        Attendancekeycodeinfo.addValueEventListener(this);
        Attendancestartinfo.addValueEventListener(this);
        Attendanceendinfo.addValueEventListener(this);
    }
}
