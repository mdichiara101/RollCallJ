package com.example.rollcallj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class setClassKey extends AppCompatActivity {

    private EditText keyCode, startTime, endTime;
    private Button RegisterKeyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_sboundary);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");

        // get our input fields by its ID
        keyCode = (EditText) findViewById(R.id.keyCode);
        startTime = (EditText) findViewById(R.id.startTime);
        endTime = (EditText) findViewById(R.id.endTime);

        // get our button by its ID
        RegisterKeyCode = (Button) findViewById(R.id.keycode);

        RegisterKeyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_KeyCode = keyCode.getText().toString();
                String txt_startTime = startTime.getText().toString();
                String txt_endTime = endTime.getText().toString();
                if (txt_KeyCode.isEmpty() || txt_startTime.isEmpty() || txt_endTime.isEmpty()) {
                    Toast.makeText(setClassKey.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, Object> classinfo = new HashMap<>();
                    classinfo.put("KeyCode", txt_KeyCode);
                    classinfo.put("startTime", txt_startTime);
                    classinfo.put("endTime", txt_endTime);
                    FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("AttendanceKeyInfo").updateChildren(classinfo);
                    finish();
                }

            }
        });

    }
}