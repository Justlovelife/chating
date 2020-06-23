package com.example.chating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chating.Bean.Post;
import com.example.chating.Bean.User;
import com.example.chating.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PushContent  extends AppCompatActivity {

    private EditText pushcontent;
    private ImageView back;
    private Button push;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pushcontent );

        initView();



        //发帖按钮
        push.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pushcontent.getText().toString().isEmpty())
                {
                    Toast.makeText( PushContent.this,"请输入内容" ,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    User user= BmobUser.getCurrentUser( User.class );
                    Post po=new Post();
                    po.setName( user.getUsername() );
                    po.setContent( pushcontent.getText().toString() );
                    po.setAuthor( user );
                    po.setIsrelated( "0" );
                    po.save( new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            //  没有异常
                            if(e==null)
                            {
                                //发布成功，编辑框内容清空
                                pushcontent.setText( "" );




                                Toast.makeText( PushContent.this,"发布成功" ,Toast.LENGTH_SHORT).show();
                                finish();

                            }
                            //异常，比如没有网络
                            else
                            {
                                Toast.makeText( PushContent.this,"发布失败",Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
                }
            }
        } );

        //返回的按钮
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void initView() {
        pushcontent=findViewById( R.id.pushcontent );
        push=findViewById( R.id.push );
        back=findViewById( R.id.back );

    }
}
