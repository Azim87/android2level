package com.kubatov.todo.Activities;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kubatov.todo.Interface.ClickListener;
import com.kubatov.todo.R;
import com.kubatov.todo.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    UserAdapter adapter;
    List<User> list;
    FloatingActionButton userAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userAddButton = findViewById(R.id.user_add_float_button);

        userAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, UserFormActivity.class));
            }
        });
        initRecycler();
        loadUsers();
    }

    public void initRecycler() {
        list = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new ClickListener() {
            @Override
            public void onClicks(int pos) {
                int position = pos;
                Intent intent = new Intent(UserActivity.this, UserFormActivity.class);
                intent.putExtra("user", (Serializable) list.get(position));
                startActivity(intent);
            }


            @Override
            public void deleteOnClick(final int pos) {
                User user = list.get(pos);
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder
                        .setTitle("Delete: " + user.getName() + "?")
                        .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                App.getInstance().getDataBase().userDAO().delete(list.remove(pos));
                            }
                        })
                        .setNegativeButton("No!", null)
                        .setCancelable(true);
                builder
                        .create()
                        .show();
            }
        });

    }

    private void loadUsers() {
        App.getInstance().getDataBase().userDAO().getAll().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                list.clear();
                list.addAll(users);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    boolean sortUser;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.user_menu_name) {
            if (sortUser) {
                App.getInstance().getDataBase().userDAO().getNameSortDESC().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        list.clear();
                        list.addAll(users);
                        sortUser = false;
                        adapter.notifyDataSetChanged();
                    }
                });

            } else {
                App.getInstance().getDataBase().userDAO().getNameSortASC().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        list.clear();
                        list.addAll(users);
                        sortUser = true;
                        adapter.notifyDataSetChanged();
                    }
                });
            }
            return true;
        }

        if (id == R.id.user_menu_age) {
            if (sortUser) {
                App.getInstance().getDataBase().userDAO().getAgeSortASC().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        list.clear();
                        list.addAll(users);
                        sortUser = false;
                        adapter.notifyDataSetChanged();
                    }
                });
            } else {
                App.getInstance().getDataBase().userDAO().getAgeSortDESC().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        list.clear();
                        list.addAll(users);
                        sortUser = true;
                        adapter.notifyDataSetChanged();
                    }
                });
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
