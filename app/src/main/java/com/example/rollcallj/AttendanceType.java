package com.example.rollcallj;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AttendanceType extends AppCompatActivity implements ValueEventListener{
    //private TextView txt;
    private Switch gpsswitch;
    private Switch Facialswitch;
    private Switch keyswitch;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private DatabaseReference gpsinfo;
    private DatabaseReference facialinfo;
    private DatabaseReference keyinfo;
    private String keyroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");
        root = firebaseDatabase.getReference().child("Classes").child(classkey).child("AttendanceType");
        gpsinfo = root.child("GPS");
        facialinfo = root.child("Facial");
        keyinfo = root.child("keyCode");
        keyroot = classkey;
        setContentView(R.layout.activity_attendance_type);
        gpsswitch = (Switch) findViewById(R.id.switch1);
        Facialswitch = (Switch) findViewById(R.id.switch2);
        keyswitch = (Switch) findViewById(R.id.switch3);
    }


    public void submitGPS(View view) {
        if(gpsswitch.isChecked())
        {
            Toast.makeText(this, "GPS: On", Toast.LENGTH_SHORT).show();
            gpsinfo.setValue("On");
            facialinfo.setValue("Off");
            keyinfo.setValue("Off");
            opensetgps(keyroot);

        }else{
            Toast.makeText(this, "GPS: Off", Toast.LENGTH_SHORT).show();
            gpsinfo.setValue("Off");
        }
    }

    public void submitFacial(View view) {
        if(Facialswitch.isChecked())
        {
            Toast.makeText(this, "Facial: On", Toast.LENGTH_SHORT).show();
            facialinfo.setValue("On");
            gpsinfo.setValue("Off");
            keyinfo.setValue("Off");
        }else{
            Toast.makeText(this, "Facial: Off", Toast.LENGTH_SHORT).show();
            facialinfo.setValue("Off");
        }
    }

    public void submitKeyCode(View view) {
        if(keyswitch.isChecked())
        {
            Toast.makeText(this, "KeyCode: On", Toast.LENGTH_SHORT).show();
            keyinfo.setValue("On");
            gpsinfo.setValue("Off");
            facialinfo.setValue("Off");
            opensetkey(keyroot);
        }else{
            Toast.makeText(this, "KeyCode: Off", Toast.LENGTH_SHORT).show();
            keyinfo.setValue("Off");
        }
    }

    private void opensetgps(String classkey) {
        Intent intent;
        intent = new Intent(this, GPSboundary.class);
        intent.putExtra("ClassKey", classkey);
        startActivity(intent);
    }

    private void opensetkey(String classkey) {
        Intent intent;
        intent = new Intent(this, setClassKey.class);
        intent.putExtra("ClassKey", classkey);
        startActivity(intent);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue(String.class)!=null){
            String key = dataSnapshot.getKey();
            if(key.equals("GPS")){
                String gps = dataSnapshot.getValue(String.class);
                if(gps.equals("On")){
                    gpsswitch.setChecked(true);
                    Facialswitch.setChecked(false);
                    keyswitch.setChecked(false);
                }else if(gps.equals("Off"))
                {
                    gpsswitch.setChecked(false);
                }
            }
            if(key.equals("Facial")){
                String facial = dataSnapshot.getValue(String.class);
                if(facial.equals("On")){
                    Facialswitch.setChecked(true);
                    gpsswitch.setChecked(false);
                    keyswitch.setChecked(false);
                }else if(facial.equals("Off"))
                {
                    Facialswitch.setChecked(false);
                }
            }
            if(key.equals("keyCode")){
                String keycode = dataSnapshot.getValue(String.class);
                if(keycode.equals("On")){
                    keyswitch.setChecked(true);
                    gpsswitch.setChecked(false);
                    Facialswitch.setChecked(false);
                }else if(keycode.equals("Off"))
                {
                    keyswitch.setChecked(false);
                }
            }

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        gpsinfo.addValueEventListener(this);
        facialinfo.addValueEventListener(this);
        keyinfo.addValueEventListener(this);
    }
}


/*
gpsswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                    Toast.makeText(AttendanceType.this, "GPS on", Toast.LENGTH_SHORT).show();
                    opensetgps(classkey);
                    HashMap<String, Object> gps = new HashMap<>();
                    gps.put("GPS", "On");
                    FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("AttendanceType").updateChildren(gps);
                }else{
                    Toast.makeText(AttendanceType.this, "GPS off", Toast.LENGTH_SHORT).show();
                    HashMap<String, Object> gps = new HashMap<>();
                    gps.put("GPS", "Off");
                    FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("AttendanceType").updateChildren(gps);
                }
            }
        });
        Facialswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                    Toast.makeText(AttendanceType.this, "Facial On", Toast.LENGTH_SHORT).show();
                    HashMap<String, Object> faceinfo = new HashMap<>();
                    faceinfo.put("Facial", "On");
                    FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("AttendanceType").updateChildren(faceinfo);
                }else{
                    Toast.makeText(AttendanceType.this, "Facial Off", Toast.LENGTH_SHORT).show();
                    HashMap<String, Object> faceinfo = new HashMap<>();
                    faceinfo.put("Facial", "Off");
                    FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("AttendanceType").updateChildren(faceinfo);
                }
            }
        });
        keyswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                    Toast.makeText(AttendanceType.this, "Key On", Toast.LENGTH_SHORT).show();
                    opensetkey(classkey);
                    HashMap<String, Object> keyinfo = new HashMap<>();
                    keyinfo.put("keyCode", "On");
                    FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("AttendanceType").updateChildren(keyinfo);
                }else{
                    Toast.makeText(AttendanceType.this, "Key Off", Toast.LENGTH_SHORT).show();
                    HashMap<String, Object> keyinfo = new HashMap<>();
                    keyinfo.put("keyCode", "Off");
                    FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("AttendanceType").updateChildren(keyinfo);
                }
            }
        });
* */
