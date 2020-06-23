package com.example.chating.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chating.Bean.Comunity;
import com.example.chating.Bean.Post;
import com.example.chating.Bean.User;
import com.example.chating.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class MyInfo extends AppCompatActivity {

    private ImageView back;
    private TextView my_name,my_pushnum,my_comunity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_myinfo );

        initView();

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

            getInfo();
    }

    private void getInfo() {
        User  user= BmobUser.getCurrentUser( User.class );
        String Id =user.getObjectId();
        String name =user.getUsername();

        BmobQuery<User> bmobQuery=new BmobQuery<>(  );
        BmobQuery<Comunity>comunityBmobQuery=new BmobQuery<>(  );
        BmobQuery<Post>postBmobQuery=new BmobQuery<>(  );
        bmobQuery.getObject( Id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null)
                {
                    my_name.setText( user.getUsername() );


                    //   nickname.setText( user.getNickname() );

                }else
                {
                    Toast.makeText( MyInfo.this,"加载失败",Toast.LENGTH_SHORT ).show();
                }

            }
        } );
    //查询帖子数
        postBmobQuery.addWhereEqualTo( "name",name );
        postBmobQuery.findObjects( new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if(e==null)
                {
                    my_pushnum.setText( String.valueOf( list.size() ) );
                }
            }
        } );

        //查询论坛数
       comunityBmobQuery.addWhereEqualTo( "owner",name );
    comunityBmobQuery.findObjects( new FindListener<Comunity>() {
            @Override
            public void done(List<Comunity> list, BmobException e) {
                if(e==null)
                {
                    my_comunity.setText( String.valueOf( list.size() ) );
                }
            }
        } );
    }

    private void initView() {
        back =findViewById( R.id.back );
        my_name=findViewById( R.id.my_name );
        my_pushnum=findViewById( R.id.my_pushnum );
        my_comunity=findViewById( R.id.my_communitynum );
    }
}
