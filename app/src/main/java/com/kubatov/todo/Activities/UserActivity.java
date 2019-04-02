package com.kubatov.todo.Activities;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kubatov.todo.R;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    UserAdapter adapter;
    List<User> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getUsers();
        initRecycler();
    }

    public void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recycler_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    public void getUsers(){
        list = new ArrayList<>();
        list.add(new User("Alex", 30));
        list.add(new User("Alex", 30));
    }

}
