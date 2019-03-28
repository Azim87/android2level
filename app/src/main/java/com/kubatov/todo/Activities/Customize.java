package com.kubatov.todo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.kubatov.todo.R;
import com.kubatov.todo.Task;

public class Customize extends AppCompatActivity {
    //EditText editTitle;
    RadioGroup radioGroup;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusomize);

        radioGroup = findViewById(R.id.radio_group_color);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.text_color_1:
                        color = 0;
                        break;
                    case R.id.text_color_2:
                        color = 1;
                        break;
                    case R.id.text_color_3:
                        color = 2;
                        break;
                }
            }
        });
    }
    public void onSave(View view) {
        Task task = new Task();
        task.setTitle(String.valueOf(color));
        task.setDescription(String.valueOf(color));
        task.setStatus(color);
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);
        finish();
    }
}
