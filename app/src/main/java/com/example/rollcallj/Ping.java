package com.example.rollcallj;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ping extends AppCompatActivity {
    TextView a,b,c,d;
    private FirebaseDatabase database;
    private DatabaseReference reff,studentref;
    private String studentkey,classkey,Date,maxlatitude,minlatitude,maxlongitude,minlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);
        Toast.makeText(this, "Im still Crashing", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        studentkey = intent.getStringExtra("student");
        classkey = intent.getStringExtra("classkey");
        database = FirebaseDatabase.getInstance();
        reff = database.getReference().child("Classes").child(classkey).child("GPSInfo");
        studentref = database.getReference().child("Classes").child(classkey).child("Students").child(studentkey);
        Toast.makeText(this, "Im still flagging", Toast.LENGTH_SHORT).show();
        ActivityCompat.requestPermissions(Ping.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        Toast.makeText(this, "line above killed me", Toast.LENGTH_SHORT).show();


    }

    public void SubmitPing(View view) {

        Toast.makeText(this, "button works?????", Toast.LENGTH_SHORT).show();



        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Toast.makeText(Ping.this, dataSnapshot.child("Latitude_Max").getValue().toString(), Toast.LENGTH_SHORT).show();
                    maxlatitude = dataSnapshot.child("Latitude_Max").getValue().toString();
                    minlatitude = dataSnapshot.child("Latitude_Min").getValue().toString();
                    maxlongitude = dataSnapshot.child("Longitude_Max").getValue().toString();
                    minlongitude = dataSnapshot.child("Longitude_Min").getValue().toString();

                    //Toast.makeText(Ping.this, "I got the data", Toast.LENGTH_SHORT).show();

                    double maxlat = Double.parseDouble(maxlatitude);
                    double minlat = Double.parseDouble(minlatitude);
                    double maxlon = Double.parseDouble(maxlongitude);
                    double minlon = Double.parseDouble(minlongitude);
                    GPStracker g = new GPStracker(getApplicationContext());
                    Location l = g.getLocation();

                    if (l != null) {
                        double lat = l.getLatitude();
                        double lon = l.getLongitude();
                        //Toast.makeText(getApplicationContext(), "LAT: " + lat + "\n LON: " + lon, Toast.LENGTH_LONG).show();
                        if (lat <= maxlat && lat >= minlat && lon <= maxlon && lon >= minlon) {
                            Toast.makeText(Ping.this,"Inside",Toast.LENGTH_SHORT).show();
                            setattendance(classkey,studentkey);
                            break;
                        } else {
                            Toast.makeText(Ping.this, "Outside", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        //Toast.makeText(Ping.this, "I tried", Toast.LENGTH_SHORT).show();
                    }

                }
                finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

    private void setattendance(String classkey, String studentkey) {
        studentref = database.getReference().child("Classes").child(classkey).child("Students").child(studentkey).child("Attendance");
        Calendar calander1 = Calendar.getInstance();
        SimpleDateFormat simpledateformat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String Date1 = simpledateformat1.format(calander1.getTime());
        studentref.child(Date1).setValue("Present");
        finish();
    }
}
