package com.kubatov.todo.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.kubatov.todo.R;
import com.kubatov.todo.Task;

public class Customize extends AppCompatActivity {
    RadioGroup radioGroup, radioGroupFont;
    int color;
    int font;
    Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusomize);

        radioGroup = findViewById(R.id.radio_group_color);
        radioGroupFont = findViewById(R.id.radio_group_text_size);

        radioGroupFont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                        switch (checkedId) {
                            case R.id.font_size_1:
                                font = 16;
                                break;
                            case R.id.font_size_2:
                                font = 20;
                                break;
                            case R.id.font_size_3:
                                font = 25;
                                break;
              }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.text_color_1:
                        color = Color.RED;
                        break;
                    case R.id.text_color_2:
                        color = Color.BLUE;
                        break;
                    case R.id.text_color_3:
                        color = Color.GREEN;
                        break;
                }
            }
        });
    }

    private void saveSetting() {
        SharedPreferences preferences = getSharedPreferences("color", MODE_PRIVATE);
        preferences.edit()
                .putInt("color", color)
                .putInt("font", font )
                .apply();
    }

    public void onSave(View view) {
        saveSetting();
        Task task = new Task();
        task.setStatus(color);
        task.setStatus(font);
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);
        finish();
    }
}
