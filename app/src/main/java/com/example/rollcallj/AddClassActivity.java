package com.example.rollcallj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddClassActivity extends AppCompatActivity {

    private Button submit;
    private EditText codeBox;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        codeBox = findViewById(R.id.codeBox);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_code = codeBox.getText().toString();
                // if txt_code = teachers_class_code then add class and go to add image

                startActivity(new Intent(AddClassActivity.this, AddImageActivity.class));
            }
        });
    }
}
