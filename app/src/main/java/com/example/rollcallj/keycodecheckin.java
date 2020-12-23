package com.example.rollcallj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class keycodecheckin extends AppCompatActivity implements ValueEventListener {
    private EditText keycode;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference root,start,end,code,studentreference,ref;
    private String classkey,studentkeys,Date,startdate,enddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keycodecheckin);
        Intent intent = getIntent();
        classkey = intent.getStringExtra("classkey");
        studentkeys = intent.getStringExtra("student");

        firebaseDatabase = FirebaseDatabase.getInstance();
        keycode = (EditText) findViewById(R.id.editCodeValue);
        studentreference = firebaseDatabase.getReference().child("Classes").child(classkey).child("Students").child(studentkeys).child("Attendance");
        root = firebaseDatabase.getReference().child("Classes").child(classkey).child("AttendanceKeyInfo");
        start = root.child("startTime");
        end = root.child("endTime");
        code = root.child("KeyCode");
    }
        public void submit(View view) {
            ref = firebaseDatabase.getReference().child("Classes").child(classkey).child("AttendanceKeyInfo");
            ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //all data
                            //for(DataSnapshot snapshot : dataSnapshot.getChildren())
                            String key = keycode.getText().toString();
                            for(DataSnapshot snapshot : dataSnapshot.getChildren())
                            {

                                if(dataSnapshot.child("KeyCode").getValue().toString().equals(key)) {
                                    //Toast.makeText(keycodecheckin.this, "Class Found: ", Toast.LENGTH_SHORT).show();
                                    String ClassStart = dataSnapshot.child("startTime").getValue().toString();
                                    int start = Integer.parseInt(ClassStart);
                                    String ClassEnd = dataSnapshot.child("endTime").getValue().toString();
                                    int end = Integer.parseInt(ClassEnd);
                                    Calendar calander = Calendar.getInstance();
                                    SimpleDateFormat simpledateformat;
                                    simpledateformat = new SimpleDateFormat("HHmm");
                                    Date = simpledateformat.format(calander.getTime());
                                    Calendar calander1 = Calendar.getInstance();
                                    SimpleDateFormat simpledateformat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                    String Date1 = simpledateformat1.format(calander1.getTime());
                                    int currenttime = Integer.parseInt(Date1);
                                    if(currenttime<=end && currenttime>=start)
                                        {
                                            studentreference = firebaseDatabase.getReference().child("Classes").child(classkey).child("Students").child(studentkeys).child("Attendance");
                                            studentreference.child(Date1).setValue("Present");
                                        }else{
                                            Toast.makeText(keycodecheckin.this, "Attendance Failed", Toast.LENGTH_SHORT).show();
                                        }
                                }else
                                {
                                    Toast.makeText(keycodecheckin.this, "Key not found", Toast.LENGTH_SHORT).show();
                                }
                                //ClassAttendanceKey info = snapshot.getValue(ClassAttendanceKey.class);
                                //String ClassKey = info.getKeyCode();
                                //String ClassStart = info.getStartTime();
                                //int start = Integer.parseInt(ClassStart);
                                //Toast.makeText(keycodecheckin.this, "Class Found: "+ClassStart, Toast.LENGTH_SHORT).show();
                                //String ClassEnd = info.getEndTime();
                                //int end = Integer.parseInt(ClassEnd);
                                /*
                                Calendar calander = Calendar.getInstance();
                                SimpleDateFormat simpledateformat;
                                simpledateformat = new SimpleDateFormat("HHmm");
                                Date = simpledateformat.format(calander.getTime());
                                int currenttime = Integer.parseInt(Date);

                                if(ClassKey.equals(keycode)){
                                    if(currenttime<=end && currenttime>=start)
                                    {
                                        studentreference.child(Date).setValue("Present");
                                    }else{
                                        Toast.makeText(keycodecheckin.this, "Attendance Failed", Toast.LENGTH_SHORT).show();
                                    }


                                }

                                 */
                            }
                        }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue(String.class)!=null){

     
     
            }
        }


    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        start.addValueEventListener(this);
        end.addValueEventListener(this);
        code.addValueEventListener(this);
    }
}
