package com.example.asus.olddancer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class register extends Activity{

    private EditText register_username,register_password;
    private Button register;
    private ImageButton register_back;
    private String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initInt();
        inttFocus();
        initClick();
    }

    private void initClick() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = register_username.getText().toString().trim();
                password = register_password.getText().toString().trim();

            }
        });

        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this,login.class);
                startActivity(intent);
            }
        });
    }


    private void inttFocus() {
        register_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(register_username.getText().toString().trim().length() < 4){
                        Toast.makeText(register.this, "用户名不能少于4个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (register_password.getText().toString().trim().length() < 6){
                        Toast.makeText(register.this, "密码不能少于6个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initInt() {
        register_username = (EditText) findViewById(R.id.username);
        register_password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register_sure);
        register_back = (ImageButton) findViewById(R.id.back);
    }

    private boolean checkEdit() {
        if(register_username.getText().toString().trim().equals("")){
            Toast.makeText(register.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if (register_password.getText().toString().trim().equals("")){
            Toast.makeText(register.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else {
            return false;
        }
        return true;
    }
}

