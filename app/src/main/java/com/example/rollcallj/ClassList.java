package com.example.rollcallj;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ClassList extends AppCompatActivity {
    private EditText ClassName, ClassStartTime, ClassEndTime, RegistrationKey,days,Start_day,End_day;
    private Button ClassRegistrationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        // get our input fields by its ID
        ClassName = (EditText)findViewById(R.id.ClassName);
        days = (EditText)findViewById(R.id.days);
        Start_day = (EditText)findViewById(R.id.Start_date);
        End_day = (EditText)findViewById(R.id.End_date);
        ClassStartTime = (EditText)findViewById(R.id.ClassStartTime);
        ClassEndTime = (EditText)findViewById(R.id.ClassEndTime);
        RegistrationKey= (EditText)findViewById(R.id.RegistrationKey);

        // get our button by its ID
        ClassRegistrationBtn = (Button) findViewById(R.id.CreateClassBtn);

        // set its click listener for class info
        ClassRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_class = ClassName.getText().toString();
                String txt_Stime = ClassStartTime.getText().toString();
                String txt_days = days.getText().toString();
                String txt_EndTime = ClassEndTime.getText().toString();
                String txt_Regkey = RegistrationKey.getText().toString();
                String txt_Start = Start_day.getText().toString();
                String txt_End = End_day.getText().toString();

                if(txt_class.isEmpty()||txt_Stime.isEmpty()||txt_EndTime.isEmpty()||txt_Regkey.isEmpty()||txt_days.isEmpty()||txt_End.isEmpty()||txt_Start.isEmpty())
                {
                    Toast.makeText(ClassList.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String, Object> classinfo = new HashMap<>();
                    classinfo.put("Name",txt_class);
                    classinfo.put("StartTime",txt_Stime);
                    classinfo.put("EndTime",txt_EndTime);
                    classinfo.put("RegistrationKey",txt_Regkey);
                    classinfo.put("Days",txt_days);
                    classinfo.put("Start_Day",txt_Start);
                    classinfo.put("End_Day",txt_End);
                    FirebaseDatabase.getInstance().getReference().child("Classes").child(txt_Regkey).updateChildren(classinfo);
                    Toast.makeText(ClassList.this,"Class Created Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });


        /*firebase test info below
        FirebaseDatabase.getInstance().getReference().child("Progammingknowledge").child("android").setValue("abcd");

            add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String txt_name = edit.getText().toString();
            if(txt_name.isEmpty())
            {
                Toast.makeText(databaserealtimetest.this,"no Name entered",Toast.LENGTH_SHORT).show();
            }else{
                FirebaseDatabase.getInstance().getReference().child("ProgrammingKnowledge").push().child("Name").setValue(txt_name);
            }

        }
    });

    FirebaseDatabase.getInstance().getReference().child("Progammingknowledge").child("android").setValue("abcd");
        HashMap<String, Object> map = new HashMap<>();
        map.put("Name","John");
        map.put("Email","john.com");

        FirebaseDatabase.getInstance().getReference().child("ProgrammingKnowledge").child("multiplevalues").updateChildren(map);


*/
        }

}

