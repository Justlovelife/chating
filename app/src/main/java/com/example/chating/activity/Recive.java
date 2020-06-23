package com.example.chating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class Recive extends AppCompatActivity {

    private TextView username,content,time;
    private ImageView  back;
    private  ImageView rec_collect;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recive);

        initView();
        initData();
        
       getisrelated();

        //监听返回
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        rec_collect.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=getIntent();
                String Id= in.getStringExtra( "id" );
                BmobQuery<Post>bmobQuery=new BmobQuery<>(  );
                bmobQuery.getObject( Id, new QueryListener<Post>() {
                    @Override
                    public void done(Post post, BmobException e) {
                        if(post.getIsrelated().equals( "0" ))
                        {
                            Intent in=getIntent();
                            String Id= in.getStringExtra( "id" );
                            User user=BmobUser.getCurrentUser( User.class );
                            Post po=new Post();
                            po.setObjectId( Id );
                            po.setIsrelated( "1" );
                            BmobRelation relation=new BmobRelation(  );
                            relation.add( user );
                            po.setRelation( relation );
                            po.update( new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if(e==null)
                                    {
                                        rec_collect.setImageResource( R.drawable .shoucang_black);
                                        Toast.makeText( Recive.this,"收藏成功",Toast.LENGTH_SHORT ).show();
                                    }
                                    else
                                    {
                                        Toast.makeText( Recive.this,"收藏失败",Toast.LENGTH_SHORT ).show();
                                    }

                                }
                            } );

                        }
                        else {
                            Intent in=getIntent();
                            String Id= in.getStringExtra( "id" );
                            User user=BmobUser.getCurrentUser( User.class );
                            Post po=new Post();
                            po.setObjectId( Id );
                            po.setIsrelated( "0" );
                            BmobRelation relation=new BmobRelation(  );
                            relation.remove( user );
                            po.setRelation( relation );
                            po.update( new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if(e==null)
                                    {
                                        rec_collect.setImageResource( R.drawable .shoucang_black);
                                        Toast.makeText( Recive.this,"已取消收藏",Toast.LENGTH_SHORT ).show();
                                    }
                                    else
                                    {
                                        Toast.makeText( Recive.this,"取消收藏失败",Toast.LENGTH_SHORT ).show();
                                    }

                                }
                            } );

                        }
                    }
                } );



            }
        } );
    }

    private void getisrelated() {
        Intent in=getIntent();
        String Id= in.getStringExtra( "id" );

        Log.i("TAG","该贴id为"+Id);
        BmobQuery<Post>bmobQuery=new BmobQuery<>(  );
        bmobQuery.getObject( Id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
              if(post.getIsrelated().equals( "1" ))

                {
                    Toast.makeText( Recive.this,"post为空",Toast.LENGTH_SHORT ).show();
                    //已被收藏
                    rec_collect.setImageResource( R.drawable.shoucang_black );
                }
                else
                {
                    //无收藏
                }
            }
        } );
    }

    private void initData() {

        //第二种
        Intent a=getIntent();
        Intent b=getIntent();
        Intent c=getIntent();
        String usernamea=a.getStringExtra( "username" );
        String contenta=a.getStringExtra( "content" );
        String timea=a.getStringExtra( "time" );
        username.setText(usernamea);
        content.setText(contenta);
        time.setText( timea );
/*
        //第一种
        Intent in=getIntent();
        String id=in.getStringExtra( "id" );
      // Post po=new Post();

        BmobQuery<Post>query=new BmobQuery<>(  );
        query.getObject( id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                if(e==null){
                    username.setText(post.getName());
                   content.setText( post.getContent() );
                 time.setText( post.getCreatedAt() );
                }else {
                    Toast.makeText( Recive.this,"获取失败",Toast.LENGTH_SHORT ).show();
                }
            }
        } );
*/
    }

    private void initView() {
        username=findViewById( R.id.username );
        content=findViewById( R.id.content );
        time=findViewById( R.id.time );
        back=findViewById( R.id.back );
        rec_collect=findViewById( R.id.rec_collect );
    }
}

