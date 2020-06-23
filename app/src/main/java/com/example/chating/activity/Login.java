package com.example.chating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chating.Bean.User;
import com.example.chating.MainActivity;
import com.example.chating.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Login extends AppCompatActivity {

    private EditText username,password;
    private Button login,zhuce;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        username=findViewById( R.id.username );
        password=findViewById( R.id.password );
        login=findViewById( R.id.login );
         zhuce=findViewById( R.id.register );
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setUsername(username.getText().toString().trim());
                user.setPassword( password.getText().toString().trim() );
                user.login( new SaveListener<User>() {
                    @Override
                    public void done(User o, BmobException e) {
                        if(e==null)
                        {
                            Toast.makeText( Login.this,"登陆成功",Toast.LENGTH_SHORT ).show();
                            startActivity( new Intent( Login.this, MainActivity.class ) );
                            finish();
                        }else{
                            Toast.makeText( Login.this,"登陆失败"+e,Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
            }
        } );

//注册模块
        zhuce.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( Login.this,Register.class ) );
            }
        } );
    }
}
