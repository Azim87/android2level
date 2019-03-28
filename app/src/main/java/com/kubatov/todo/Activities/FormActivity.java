package com.kubatov.todo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kubatov.todo.R;
import com.kubatov.todo.Task;

public class FormActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editDescription;
    TextView textView;

    RadioGroup radioGroup;
    int status;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        textView = findViewById(R.id.textStatus);

        radioGroup = findViewById(R.id.radio_group);

        Task task = (Task) getIntent().getSerializableExtra("task");
        if (task != null) {
            editTitle.setText(task.getTitle());

            switch (task.getStatus()) {
                case 0:
                    radioGroup.check(R.id.radio_button1);
                    break;
                case 1:
                    radioGroup.check(R.id.radio_button2);
                    break;
                case 2:
                    radioGroup.check(R.id.radio_button3);
                    break;

            }
            editDescription.setText(task.getDescription());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button1:
                        status = 0;
                        break;

                    case R.id.radio_button2:
                        status = 1;
                        break;

                    case R.id.radio_button3:
                        status = 2;
                        break;
                }
            }
        });
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);
        finish();

    }


}
