package com.example.assigments_;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_btn;
    RecyclerView list;
    UserAdapter adapter;
    List<User> users;
    FirebaseFirestore firebaseFirestore;
    ListenerRegistration task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupItems();
        OnClick();

        list.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter(MainActivity.this, users);
        list.setAdapter(adapter);
        ReadData();
    }


    public void setupItems() {

        add_btn = findViewById(R.id.add_btn);
        list = findViewById(R.id.list_users);
        users = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void OnClick() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ReadData() {
        task = firebaseFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    for (DocumentChange ds : value.getDocumentChanges()) {
                        users.add(ds.getDocument().toObject(User.class));
                        adapter.notifyItemInserted(users.size()-1);
                    }

                }
                value.toObjects(User.class);
            }
        });

    }
}