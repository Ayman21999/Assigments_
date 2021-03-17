package com.example.assigments_;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.HashMap;

public class AddActivity extends AppCompatActivity {
    EditText nameed;
    EditText addressed;
    EditText numbered;
    Button add;
    FirebaseFirestore firebaseFirestore;
    CollectionReference reference;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setUpItems();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });
    }


    public void setUpItems() {
        nameed = findViewById(R.id.username);
        addressed = findViewById(R.id.useraddress);
        numbered = findViewById(R.id.usernumber);
        add = findViewById(R.id.addbtn);
        firebaseFirestore = FirebaseFirestore.getInstance();
        reference = firebaseFirestore.collection("Users");
        progressDialog = new ProgressDialog(this);


    }

    public void AddData() {
        String username = nameed.getText().toString();
        String addresss = addressed.getText().toString();
        String number = numbered.getText().toString();
        if (!username.isEmpty() && !addresss.isEmpty() && !number.isEmpty()) {
            HashMap<String , Object>hashMap = new HashMap<>();
            hashMap.put("username",username);
            hashMap.put("address",addresss);
            hashMap.put("number",number);
            progressDialog.setMessage("Adding....");
            progressDialog.show();
            reference.document().set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(AddActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                    Log.d("ttt",username);
                    progressDialog.dismiss();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }else {
            Toast.makeText(this, "You Have to fill all fields", Toast.LENGTH_SHORT).show();
        }
    }
}