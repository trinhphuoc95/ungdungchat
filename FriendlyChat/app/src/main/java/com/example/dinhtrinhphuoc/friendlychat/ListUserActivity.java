package com.example.dinhtrinhphuoc.friendlychat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private AdapterListUsers adapterListUsers;
    private RecyclerView recyclerView;
    private ArrayList<UserMessage> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        recyclerView = (RecyclerView) findViewById(R.id.listuser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterListUsers = new AdapterListUsers(list, ListUserActivity.this);
        recyclerView.setAdapter(adapterListUsers);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserMessage userMessage = dataSnapshot.getValue(UserMessage.class);
                list.add(userMessage);
              //  Log.d("asdas", "jjj" + userMessage.getName());
                adapterListUsers.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
