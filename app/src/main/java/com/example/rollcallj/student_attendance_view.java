package com.example.rollcallj;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class student_attendance_view extends AppCompatActivity {
    private Button logout;
    private EditText edit;
    private Button add;
    private ListView listview;
    private String classkey,studentkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_view);

        Intent intent = getIntent();
        classkey = intent.getStringExtra("classkey");
        studentkey = intent.getStringExtra("studentkey");

        add = findViewById(R.id.add);
        listview = findViewById(R.id.listview);

        final ArrayList<String> list =new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item,list);
        listview.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("Students").child(studentkey).child("Attendance");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                //all data
                //for(DataSnapshot snapshot : dataSnapshot.getChildren())
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
                /* will be used to allow him to edit the attendance
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = (String) parent.getItemAtPosition(position);
                    }
                });
                */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
