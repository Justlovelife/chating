package com.example.chating.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chating.Bean.User;
import com.example.chating.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {

   private EditText  username,password,nickname;
   private Button register;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        username = findViewById( R.id.username );
        password=findViewById( R.id.password );
        nickname=findViewById( R.id.nickname );

        register=findViewById( R.id.register);
  //对注册监听
        register.setOnClickListener( new OnClickListener (){
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setUsername( username.getText().toString().trim() );
                user.setPassword( password.getText().toString().trim() );
                user.setNickname( nickname.getText().toString().trim() );

                if(username.getText().toString().equals( "" ))
                {
                    Toast.makeText( Register.this ,"用户名没有输入",Toast.LENGTH_SHORT).show();
                }else  if (password.getText().toString().equals( "" ))
                {
                    Toast.makeText( Register.this,"密码没有输入",Toast.LENGTH_SHORT ).show();
                }
                else {
                    user.signUp( new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e==null){
                                Toast.makeText( Register.this,"注册成功",Toast.LENGTH_SHORT ).show();
                                 finish();
                              }else {
                                Toast.makeText( Register.this,"注册失败",Toast.LENGTH_SHORT ).show();

                            }

                        }
                    } );
                }


            }
        } );
    }

}
