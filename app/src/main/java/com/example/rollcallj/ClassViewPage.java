package com.example.rollcallj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClassViewPage extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView classviewtop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view_page);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");

        classviewtop = (TextView)findViewById(R.id.classViewPageName);
        classviewtop.setText(classkey);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openViewClass(classkey);
            }
        } );

        button2 = (Button)findViewById(R.id.Attendance);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAttendance(classkey);
            }
        } );

        button3 = (Button)findViewById(R.id.type);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAttendanceType(classkey);
            }
        } );

        button4 = (Button)findViewById(R.id.surprise);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSurpriseAttendance(classkey);
            }
        } );
    }
    public void openViewClass(String classkey){
        Intent intent;
        intent = new Intent(this, ViewClass.class);
        intent.putExtra("ClassKey", classkey);
        startActivity(intent);
    }
    public void openAttendance(String classkey){
        Intent intent1;
        intent1 = new Intent(this, PrintAttendance.class);
        intent1.putExtra("ClassKey", classkey);
        startActivity(intent1);
    }
    public void openAttendanceType(String classkey){
        Intent intent2;
        intent2 = new Intent(this, AttendanceType.class);
        intent2.putExtra("ClassKey", classkey);
        startActivity(intent2);
    }
    public void openSurpriseAttendance(String classkey){
        Intent intent3;
        intent3 = new Intent(this, SurpriseAttendance.class);
        intent3.putExtra("ClassKey", classkey);
        startActivity(intent3);
    }

}
