package com.example.rollcallj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddImageActivity extends AppCompatActivity {

    private Button upload;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        upload = findViewById(R.id.upload);
        finish = findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(AddImageActivity.this, "Joined class", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddImageActivity.this, MainActivity.class));
            }

        });
    }

}
