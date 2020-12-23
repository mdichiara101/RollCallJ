package com.example.rollcallj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,rnum,first,last;
    private EditText password;
    private Button register;
    private RadioButton teacher;
    private RadioButton student;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USER = "user";
    private String user_type;
    private User_Information user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailR);
        password = findViewById(R.id.passwordR);
        register = findViewById(R.id.registerR);
        rnum = findViewById(R.id.rNum);
        first = findViewById(R.id.fName);
        last = findViewById(R.id.lName);
        teacher = (RadioButton) findViewById(R.id.teacher);
        student = (RadioButton) findViewById(R.id.student);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        auth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               String txt_email = email.getText().toString();
               String txt_password = password.getText().toString();

               if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                   Toast.makeText(RegisterActivity.this, "Empty credentials",Toast.LENGTH_SHORT).show();
               } else if (txt_password.length() < 6){
                   Toast.makeText(RegisterActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
               } else if(!teacher.isChecked() && !student.isChecked()){
                   Toast.makeText(RegisterActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
               }else {
                   user = new User_Information(email.getText().toString(), password.getText().toString(), user_type,first.getText().toString(),last.getText().toString(),rnum.getText().toString());
                   registerUser(txt_email, txt_password);
               }
           }

        });

    }
    public void Radioclick(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.teacher:
                if (checked)
                    student.setChecked(false);
                    user_type = "teacher";
                    break;
            case R.id.student:
                if (checked)
                    teacher.setChecked(false);
                    user_type = "student";
                    break;
        }
    }


    private void registerUser(String email, String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registering user successful!", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();
                    updateUI(user);
                    startActivity(new Intent(RegisterActivity.this, StartActivity.class)); //Changed to main in video
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateUI(FirebaseUser current_user) {
        String keyid = mDatabase.push().getKey();
        mDatabase.child(keyid).setValue(user);
        Intent log = new Intent(this, StartActivity.class);
        startActivity(log);
    }


}
