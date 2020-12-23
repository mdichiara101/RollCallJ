package com.example.rollcallj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Addstudent extends AppCompatActivity {

    private EditText FName,LName,Email,RNumber;
    private Button AddStudent,Photo;
    private Uri imageuri;
    private final int IMAGE_RESULTS = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        Intent intent = getIntent();
        final String classkey = intent.getStringExtra("ClassKey");

            // get our input fields by its ID
            FName = (EditText)findViewById(R.id.fName);
            LName = (EditText)findViewById(R.id.lName);
            Email = (EditText)findViewById(R.id.email);
            RNumber= (EditText)findViewById(R.id.rNum);

            // get our button by its ID
            AddStudent = (Button) findViewById(R.id.AddStudent);
            Photo = (Button) findViewById(R.id.photo);
            // set its click listener for class info


        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
                uploadimage();
            }
        });

            AddStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //openImage();
                    //uploadimage();
                    String txt_firstName = FName.getText().toString();
                    String txt_lastName = LName.getText().toString();
                    String txt_Email = Email.getText().toString();
                    String txt_RNumber = RNumber.getText().toString();
                    if(txt_Email.isEmpty()||txt_firstName.isEmpty()||txt_lastName.isEmpty()||txt_RNumber.isEmpty())
                    {
                        Toast.makeText(Addstudent.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                    }else{
                        HashMap<String, Object> classinfo = new HashMap<>();
                        classinfo.put("First_Name",txt_firstName);
                        classinfo.put("Last_Name",txt_lastName);
                        classinfo.put("Email",txt_Email);
                        classinfo.put("RNumber",txt_RNumber);
                        FirebaseDatabase.getInstance().getReference().child("Classes").child(classkey).child("Students").child(txt_RNumber+" : "+txt_firstName+" "+txt_lastName).updateChildren(classinfo);
                        Toast.makeText(Addstudent.this, "Student Created Successfuly", Toast.LENGTH_SHORT).show();
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

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("Image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_RESULTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode== IMAGE_RESULTS && resultCode == RESULT_OK)
        {
            imageuri = data.getData();
            uploadimage();
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadimage() {
        final ProgressDialog pd =new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        if(imageuri != null) {
            final StorageReference fileref = FirebaseStorage.getInstance().getReference().child("uplaods").child(System.currentTimeMillis() + "." + getFileExtension(imageuri));
            fileref.putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            Log.d("Downloadurl", url);
                            pd.dismiss();
                            Toast.makeText(Addstudent.this, "Image upload successful", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        pd.dismiss();
    }

}
