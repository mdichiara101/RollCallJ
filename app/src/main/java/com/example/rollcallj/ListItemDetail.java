package com.example.rollcallj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListItemDetail extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);
/*
        listView = (ListView) findViewById(R.id.listview);
        String [] values = new String[]{"button1","button2","button3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item,android.R.id.text1,values);
        listView.setAdapter(adapter);
        listView.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if(position == 0)
                {
                    Intent myintent = new Intent(view.getContext(),MainActivity.class);
                    startActivityForResult(myintent,0);
                }
                if(position == 1)
                {
                    Intent myintent = new Intent(view.getContext(),MainActivity.class);
                    startActivityForResult(myintent,1);
                }
                if(position == 2)
                {
                    Intent myintent = new Intent(view.getContext(),MainActivity.class);
                    startActivityForResult(myintent,2);
                }
            }
        });


        /*
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        // Here we turn your string.xml in an array
        String[] myKeys = getResources().getStringArray(R.array.sections);

        TextView myTextView = (TextView) findViewById(R.id.my_textview);
        myTextView.setText(myKeys[position]);
        */
    }
}
