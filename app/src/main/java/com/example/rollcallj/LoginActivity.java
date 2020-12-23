package com.example.rollcallj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.LOGIN);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();


                if (txt_email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Missing email!", Toast.LENGTH_SHORT).show();
                } else if(txt_password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Missing password!", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginUser(txt_email, txt_password);
                }
            }
        });



    }

    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                FirebaseUser user = auth.getCurrentUser();
                updateUI(user);
            }

        });
    }

    private void updateUI(FirebaseUser user) {
        Intent profile = new Intent(this,MainActivity.class);
        profile.putExtra("email",user.getEmail());
        startActivity(profile);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }
}
