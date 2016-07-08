package com.example.asus.olddancer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class register extends Activity{

    private EditText register_username,register_password;
    private Button register;
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
                if (!checkEdit()){
                    return;
                }

                username = register_username.getText().toString().trim();
                password = register_password.getText().toString().trim();
                final BmobUser user = new BmobUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null){
                            Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Log.e("TAG", "done: ",e );
                        }
                    }
                });
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

