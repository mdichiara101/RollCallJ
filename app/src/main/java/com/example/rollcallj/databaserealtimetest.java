package com.example.rollcallj;

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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class databaserealtimetest extends AppCompatActivity {

    private Button logout;
    private EditText edit;
    private Button add;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databaserealtimetest);

    add = findViewById(R.id.add);
    listview = findViewById(R.id.listview);
    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            opencreateclass();
        }
    });
/*
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
*/
    //data retreival
    final ArrayList<String> list =new ArrayList<>();
    final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item,list);
    listview.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Classes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                //all data
                //for(DataSnapshot snapshot : dataSnapshot.getChildren())
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    ClassInformation info = snapshot.getValue(ClassInformation.class);
                    String ClassKey = info.getRegistrationKey();
                    list.add(ClassKey);
                    //all
                    //list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String ClassKey = "CS3456-001";
                       // openClass(ClassKey);
                        String selectedItem = (String) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                        openclassmenu(selectedItem);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void openclassmenu(String selectedItem) {
        Intent intent;
        intent = new Intent(databaserealtimetest.this , ClassViewPage.class);
        intent.putExtra("ClassKey", selectedItem);
        startActivity(intent);
    }


    private void opencreateclass() {
        Intent intent;
        intent = new Intent(databaserealtimetest.this , ClassList.class);
        startActivity(intent);
    }
    //public String openClass(String classKey) {
    //    Intent intent;
    //    intent = new Intent(databaserealtimetest.this , ClassList.class);
    //    startActivity(intent);
    //}

}
/*
* listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Dessert dessert = desserts.get(i);
                switch(i) {
                    case 0:
                        Intent donut = new Intent(MainActivity.this, Donut.class);
                        startActivity(donut);
                        break;
                    case 1:
                        Intent cookie = new Intent(MainActivity.this, Cookie.class);
                        startActivity(cookie);
                        break;
                    case 2:
                        Intent pieceOfCake = new Intent(MainActivity.this, PieceOfCake.class);
                        startActivity(pieceOfCake);
                        break;
                    case 3:
                        Intent pastry = new Intent(MainActivity.this, Pastry.class);
                        startActivity(pastry);
                        break;
                }
    }
});
*
*
*
* */