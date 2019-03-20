package com.kubatov.todo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kubatov.todo.R;
import com.kubatov.todo.Task;

public class FormActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);

        Task task = (Task) getIntent().getSerializableExtra("task");
        if (task !=null){
            editTitle.setText(task.getTitle());
            editDescription.setText(task.getDescription());
        }
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);
        finish();

    }
}
