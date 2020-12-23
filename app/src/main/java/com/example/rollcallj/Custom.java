package com.example.rollcallj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Custom extends AppCompatActivity {
    Button maxlat;
    Button minlon;
    Button maxlon;
    Button minlat;
    Button finish;
    FirebaseDatabase database;
    DatabaseReference mdatabase, latmax, latmin, lonmax, lonmin;
    TextView minloninfo, maxloninfo, minlatinfo, maxlatinfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        minloninfo = (TextView) findViewById(R.id.minloninfo);
        maxloninfo = (TextView) findViewById(R.id.maxloninfo);
        minlatinfo = (TextView) findViewById(R.id.minlatinfo);
        maxlatinfo = (TextView) findViewById(R.id.maxlatinfo);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("Classkey");
        database = FirebaseDatabase.getInstance();
        mdatabase = database.getReference().child("Classes").child(classkey).child("GPSInfo");
        latmax = mdatabase.child("Latitude_Max");
        latmin = mdatabase.child("Latitude_Min");
        lonmax = mdatabase.child("Longitude_Max");
        lonmin = mdatabase.child("Longitude_Min");


        maxlat = (Button) findViewById(R.id.maxlat);
        ActivityCompat.requestPermissions(Custom.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        maxlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "\n LON: " + lon, Toast.LENGTH_LONG).show();
                    GPSboundary.setlatitudeMax(lat);
                    //latmax.setValue(lat);
                    maxlatinfo.setText(String.valueOf(lat));
                }


            }
        });
        minlat = (Button) findViewById(R.id.minlat);
        ActivityCompat.requestPermissions(Custom.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        minlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "\n LON: " + lon, Toast.LENGTH_LONG).show();
                    GPSboundary.setlatitudeMin(lat);
                    //latmin.setValue(lat);
                    minlatinfo.setText(String.valueOf(lat));

                }


            }
        });
        maxlon = (Button) findViewById(R.id.maxlon);
        ActivityCompat.requestPermissions(Custom.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        maxlon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "\n LON: " + lon, Toast.LENGTH_LONG).show();
                    GPSboundary.setlongitudeMax(lon);
                    //lonmax.setValue(lat);
                    maxloninfo.setText(String.valueOf(lon));


                }


            }
        });
        minlon = (Button) findViewById(R.id.minlon);
        ActivityCompat.requestPermissions(Custom.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        minlon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "\n LON: " + lon, Toast.LENGTH_LONG).show();
                    GPSboundary.setlongitudeMin(lon);
                    //lonmin.setValue(lat);
                    minloninfo.setText(String.valueOf(lon));

                }


            }
        });


        finish = (Button) findViewById(R.id.finish);
        ActivityCompat.requestPermissions(Custom.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}






