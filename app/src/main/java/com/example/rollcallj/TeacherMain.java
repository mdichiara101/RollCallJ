package com.example.rollcallj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class TeacherMain extends AppCompatActivity {
    /*
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    */
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main);

        register = findViewById(R.id.registerT);



        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                register();
            }
        } );
    }


    public void register(){
        Intent intent;
        intent = new Intent(TeacherMain.this , databaserealtimetest.class);
        startActivity(intent);
    }

}