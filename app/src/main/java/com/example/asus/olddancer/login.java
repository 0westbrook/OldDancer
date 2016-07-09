package com.example.asus.olddancer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Activity {

    private EditText login_username,login_password;
    private TextView forget;
    private Button register,login;
    private  String username,password;
    private CheckBox isremember;
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sharedPreferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        initInt();
        initClick();

        String name = sharedPreferences.getString("username","");
        String word = sharedPreferences.getString("password","");
        if(name == null && word == null){
            isremember.setChecked(false);
        }else {
            isremember.setChecked(true);
            login_username.setText(name);
            login_password.setText(word);
        }

    }

    private void initClick() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
                judgment();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);
            }
        });
    }

    private void judgment() {
    }

    private void saveInfo() {
        username = login_username.getText().toString().trim();
        password = login_password.getText().toString().trim();
        if (username.length() > 4 && password.length() > 6){
            if (isremember.isChecked()){
                editor.putString("username",username);
                editor.putString("password",password);
                editor.apply();
            }else {
                editor.remove("username");
                editor.remove("password");
                editor.apply();
            }
        }else {
            Toast.makeText(login.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void initInt() {
        login_username = (EditText) findViewById(R.id.username);
        login_password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_sure);
        register = (Button) findViewById(R.id.login_register);
        forget = (TextView) findViewById(R.id.forget);
        isremember = (CheckBox) findViewById(R.id.checkBox);
    }
}
