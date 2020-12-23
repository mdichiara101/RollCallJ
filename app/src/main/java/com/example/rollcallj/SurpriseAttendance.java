package com.example.rollcallj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SurpriseAttendance extends AppCompatActivity {
    private Button surprise;
    private String currentclass;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private DatabaseReference surpriseinfo;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprise_attendance);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");
        currentclass=classkey;

        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date = simpledateformat.format(calander.getTime());

        surprise = (Button) findViewById(R.id.Surprise);
        surprise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                root = firebaseDatabase.getReference().child("Classes").child(classkey).child("AttendanceType");
                surpriseinfo = root.child("SurpriseTime");
                Toast.makeText(SurpriseAttendance.this, "Surprise Started", Toast.LENGTH_SHORT).show();
                surpriseinfo.setValue(Date);
            }
        } );
    }
}
