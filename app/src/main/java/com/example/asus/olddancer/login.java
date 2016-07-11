package com.example.asus.olddancer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

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
                username = login_username.getText().toString().trim();
                password = login_password.getText().toString().trim();

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
        BmobUser userInfo = new BmobUser();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.login(com.example.asus.olddancer.login.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(login.this,"登陆成功!",Toast.LENGTH_SHORT).show();
                saveInfo();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(login.this,"账号或密码错误,请重新登录!",Toast.LENGTH_SHORT).show();
                Log.i("info",s+"");
            }
        });
    }

    private void saveInfo() {
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
