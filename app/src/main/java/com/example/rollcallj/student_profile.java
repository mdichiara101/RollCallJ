package com.example.rollcallj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class student_profile extends AppCompatActivity {
    //private static final String USER = "user";
    private TextView email,password,type,first,last,rnum;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        type = (TextView) findViewById(R.id.type);
        first = (TextView) findViewById(R.id.fname);
        last = (TextView) findViewById(R.id.lname);
        rnum = (TextView) findViewById(R.id.Rnum);
        //Toast.makeText(this, "made it here", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        txtemail = intent.getStringExtra("email");

        //Toast.makeText(this, txtemail, Toast.LENGTH_SHORT).show();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("user");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    if(ds.child("email").getValue().equals(txtemail)){
                        email.setText(ds.child("email").getValue(String.class));
                        type.setText(ds.child("account_type").getValue(String.class));
                        password.setText(ds.child("password").getValue(String.class));
                        first.setText(ds.child("first_Name").getValue(String.class));
                        last.setText(ds.child("last_Name").getValue(String.class));
                        rnum.setText(ds.child("rnumber").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}