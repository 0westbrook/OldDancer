package com.example.asus.olddancer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class login extends Activity {

    private EditText login_username,login_password;
    private TextView forget;
    private Button register,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initInt();

    }

    private void initInt() {
        login_username = (EditText) findViewById(R.id.username);
        login_password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_sure);
        register = (Button) findViewById(R.id.login_register);
        forget = (TextView) findViewById(R.id.forget);
    }
}
