package com.kubatov.todo.Activities;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kubatov.todo.Interface.ClickListener;
import com.kubatov.todo.R;
import com.kubatov.todo.Task;
import com.kubatov.todo.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TaskAdapter adapter;
    private List<Task>list;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);

        boolean shown = preferences.getBoolean("shown", false);

        if (!shown){
            startActivity(new Intent(this, OnBoardActivty.class));
            finish();
        }

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initList();
        loadTasks();
    }

    private void initList(){
        list = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter( list, this);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new ClickListener() {
            @Override
            public void onClicks(int pos) {
                position=pos;
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("task", list.get(pos));
                startActivity(intent);
            }

            @Override
            public void deleteOnClick(final int pos) {                                                     //delete tasks
                Task task = list.get(pos);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Do you want to delete: " + task.getTitle() + "?")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                App.getInstance().getDataBase().taskDAO().delete(list.remove(pos));
                            }
                        }).setNegativeButton("No", null)
                        .setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void loadTasks(){
        App.getInstance().getDataBase().taskDAO().getAll().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                list.clear();
                list.addAll(tasks);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onBackPressed() {                                                               //onBackPressed
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Хочешь выйти?")
                .setMessage("Может останешься еще чуть чуть ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                }).setNegativeButton("No", null)
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private boolean sort;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(MainActivity.this, Customize.class));
            return true;
        }


        if (id == R.id.action_sort){
            Log.d("ololo", "kdskjfldsf");
            if (sort){
                App.getInstance().getDataBase().taskDAO().sortByIdAsc().observe(this, new Observer<List<Task>>() {
                    @Override
                    public void onChanged(@Nullable List<Task> tasks) {
                        list.clear();
                        list.addAll(tasks);
                        sort = false;
                        adapter.notifyDataSetChanged();
                    }
                });
            }else {
                App.getInstance().getDataBase().taskDAO().sortByIdDesc().observe(this, new Observer<List<Task>>() {
                    @Override
                    public void onChanged(@Nullable List<Task> tasks) {
                        list.clear();
                        list.addAll(tasks);
                        sort = true  ;
                        adapter.notifyDataSetChanged();
                    }
                });
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_all:

                break;
            case R.id.nav_srochno:

                break;
            case R.id.nav_ochen_srochno:

                break;
            case R.id.nav_sverh_srochno:

                break;

            case R.id.users:
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                break;

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
