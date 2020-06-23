package com.example.chating.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chating.Bean.User;
import com.example.chating.MainActivity;
import com.example.chating.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
//延时操作
        Timer timer = new Timer();
        timer.schedule( timetask, 4000 );
        Bmob.initialize( this,"cd473015f750ae3623c318c168e31d57" );
    }
        TimerTask  timetask=new TimerTask() {
            @Override
            public void run() {
                //startActivity( new Intent( splash.this, MainActivity.class ) );
                //如果已登录，跳转到主界面，没登陆的话，跳转到登录界面

                BmobUser bmobUser=BmobUser.getCurrentUser( User.class );
                if (bmobUser!=null){
                    startActivity( new Intent( splash.this,MainActivity.class ) );
                    finish();
                }else {
                      //没有登录
                    startActivity( new Intent( splash.this,Login.class ) );
                    finish();
                }
            }
        };


}
