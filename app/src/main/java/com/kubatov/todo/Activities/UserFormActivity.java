package com.kubatov.todo.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kubatov.todo.R;

public class UserFormActivity extends AppCompatActivity {

    TextInputLayout textInputName;
    TextInputLayout textInputAge;
    FloatingActionButton circleButton;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        textInputName = findViewById(R.id.name_input);
        textInputAge= findViewById(R.id.age_input);
        circleButton = findViewById(R.id.user_add_button);

        user = (User) getIntent().getSerializableExtra("user");
        if (user != null){
            textInputName.getEditText().setText(user.getName());
            textInputAge.getEditText().setText(String.valueOf(user.getAge()));
        }


    }

    private boolean validateName(){
        String nameInput = textInputName
                .getEditText()
                .getText()
                .toString()
                .trim();

        if (nameInput.isEmpty()){
            textInputName.setError("This field can`t be empty!");
            return false;

        }else if (nameInput.length() >15 ){
            textInputName.setError("Name to long!");
            return false;

        }else {
            textInputName.setError(null);
            return true;
        }
    }

    private boolean validateAge(){
        Toast.makeText(UserFormActivity.this, "press on more time to add", Toast.LENGTH_SHORT).show();
        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textInputName.getEditText().getText().toString().trim();
                int age = Integer.parseInt(textInputAge.getEditText().getText().toString().trim());
                if (user != null) {
                    user.setName(name);
                    user.setAge(age);
                    App.getInstance().getDataBase().userDAO().update(user);

                } else {
                    User users = new User();
                    user.setName(name);
                    user.setAge(age);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    App.getInstance().getDataBase().userDAO().insert(users);

                }
                finish();
            }
        });


        String ageInput = textInputAge
                .getEditText()
                .getText()
                .toString()
                .trim();

        if (ageInput.isEmpty()){
            textInputAge.setError("This field can`t be empty!");
            return false;

        }else {
            textInputAge.setError(null);
            return true;
        }
    }

    public void confirmInput(View view){

        if (!validateName() | !validateAge()){
            return;
        }
        String input = "Name: " + textInputName.getEditText().getText().toString();
        input += "\n";
        input += "Age: " + textInputAge.getEditText().getText().toString();
        input += "\n";

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

    }

}
