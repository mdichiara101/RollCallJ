package com.example.rollcallj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GPSboundary extends AppCompatActivity {

    Button smallbtn;
    Button mediumbtn;
    Button largebtn;
    Button custombtn;
    Button finish;
    double longitudeS;
    double latitudeS;
    public static double latitudeMax;
    public static double latitudeMin;
    public static double longitudeMax;
    public static double longitudeMin;
    FirebaseDatabase database;
    DatabaseReference mdatabase,latmax,latmin,lonmax,lonmin;

    public static void setlatitudeMax(Double latmax){
        latitudeMax = latmax;
    }
    public static void setlatitudeMin(Double latmin ){
        latitudeMin = latmin;
    }
    public static void setlongitudeMax(Double lonmax ){
        longitudeMax = lonmax;
    }
    public static void setlongitudeMin(Double lonmin ){
        longitudeMin = lonmin;
    }
    public static double getlatitudeMax(){
        return latitudeMax;
    }
    public static double getlatitudeMin(){
        return latitudeMin;
    }
    public static double getlongitudeMax(){
        return longitudeMax;
    }
    public static double getlongitudeMin(){
        return longitudeMin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsboundary);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");
        database = FirebaseDatabase.getInstance();
        mdatabase = database.getReference().child("Classes").child(classkey).child("GPSInfo");
        latmax = mdatabase.child("Latitude_Max");
        latmin = mdatabase.child("Latitude_Min");
        lonmax = mdatabase.child("Longitude_Max");
        lonmin = mdatabase.child("Longitude_Min");



        smallbtn = (Button) findViewById(R.id.smallbtn);
        ActivityCompat.requestPermissions(GPSboundary.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        smallbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if(l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(),"LAT: "+lat+"\n LON: "+ lon, Toast.LENGTH_LONG).show();

                    longitudeS = (Math.asin(10 / (6378000 * Math.cos(Math.PI*lat/180))))*180/Math.PI;
                    latitudeS = (Math.asin((double)10 / (double)6378000))*180/Math.PI;
                    latitudeMax = lat+(latitudeS);
                    latitudeMin = lat-(latitudeS);
                    longitudeMax = lon+(longitudeS);
                    longitudeMin = lon-(longitudeS);
                    latmax.setValue(latitudeMax);
                    latmin.setValue(latitudeMin);
                    lonmax.setValue(longitudeMax);
                    lonmin.setValue(longitudeMin);
                    finish();


                }


            }
        });
        mediumbtn = (Button) findViewById(R.id.mediumbtn);
        ActivityCompat.requestPermissions(GPSboundary.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        mediumbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if(l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(),"LAT: "+lat+"\n LON: "+ lon, Toast.LENGTH_LONG).show();

                    longitudeS = (Math.asin(20 / (6378000 * Math.cos(Math.PI*lat/180))))*180/Math.PI;
                    latitudeS = (Math.asin((double)20 / (double)6378000))*180/Math.PI;
                    latitudeMax = lat+(latitudeS);
                    latitudeMin = lat-(latitudeS);
                    longitudeMax = lon+(longitudeS);
                    longitudeMin = lon-(longitudeS);
                    latmax.setValue(latitudeMax);
                    latmin.setValue(latitudeMin);
                    lonmax.setValue(longitudeMax);
                    lonmin.setValue(longitudeMin);
                    finish();

                }


            }
        });
        largebtn = (Button) findViewById(R.id.largebtn);
        ActivityCompat.requestPermissions(GPSboundary.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        largebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if(l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(),"LAT: "+lat+"\n LON: "+ lon, Toast.LENGTH_LONG).show();

                    longitudeS = (Math.asin(1000 / (6378000 * Math.cos(Math.PI*lat/180))))*180/Math.PI;
                    latitudeS = (Math.asin((double)1000 / (double)6378000))*180/Math.PI;
                    latitudeMax = lat+(latitudeS);
                    latitudeMin = lat-(latitudeS);
                    longitudeMax = lon+(longitudeS);
                    longitudeMin = lon-(longitudeS);
                    latmax.setValue(latitudeMax);
                    latmin.setValue(latitudeMin);
                    lonmax.setValue(longitudeMax);
                    lonmin.setValue(longitudeMin);
                    finish();


                }


            }
        });

        custombtn= (Button) findViewById(R.id.custombtn);
        ActivityCompat.requestPermissions(GPSboundary.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        custombtn.setOnClickListener(new View.OnClickListener(){
                                         @Override
                                         public void onClick(View v){
                                             openCustom(classkey);
                                             latmax.setValue(latitudeMax);
                                             latmin.setValue(latitudeMin);
                                             lonmax.setValue(longitudeMax);
                                             lonmin.setValue(longitudeMin);
                                             finish();
                                         }


                                     }
        );
        }
    public void openCustom(String classkey){
        Intent intent33;
        intent33 = new Intent(this, Custom.class);
        intent33.putExtra("Classkey",classkey);
        startActivity(intent33);
    }
}







