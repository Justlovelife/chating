package com.example.chating.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chating.Bean.User;
import com.example.chating.R;
import com.example.chating.activity.Changepassword;
import com.example.chating.activity.Login;
import com.example.chating.activity.MyComunity;
import com.example.chating.activity.MyInfo;
import com.example.chating.activity.MyPush;
import com.example.chating.activity.Mycollect;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class FragmentMine extends Fragment {

  //  private TextView nickname;
    private TextView username;
    private Button loginout;

    private LinearLayout myinfo;
    private LinearLayout mypush;
    private LinearLayout mycomunity;
    private LinearLayout mycollect;
    private LinearLayout setting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragmentmine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        initView();
        //加载我的信息
        getMyinfo();

        loginout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 BmobUser bmobUser=BmobUser.getCurrentUser(BmobUser.class);
                 bmobUser.logOut();      //让用户信息不再进入到APP
                startActivity( new Intent( getActivity(), Login.class ) );
                 getActivity().finish(); //APP finish掉
            }
        } );


        myinfo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  跳转到我的信息界面
                startActivity( new Intent( getActivity(), MyInfo.class ) );
            }
        } );

        mypush.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity( new Intent( getActivity() , MyPush.class) );
            }
        } );

        mycomunity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getActivity(), MyComunity.class ) );
            }
        } );

        mycollect.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getActivity(), Mycollect.class ) );
            }
        } );

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getActivity(), Changepassword.class ) );
            }
        });

    }

    private void getMyinfo() {

        //加载个人信息
        BmobUser bu=BmobUser.getCurrentUser( BmobUser.class );
        String Id =bu.getObjectId();
        BmobQuery<User>bmobQuery=new BmobQuery<>(  );
        bmobQuery.getObject( Id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null)
                {
                    username.setText( user.getUsername() );
                 //   nickname.setText( user.getNickname() );

                }else
                {
                    Toast.makeText( getActivity(),"加载失败",Toast.LENGTH_SHORT ).show();
                }

            }
        } );
    }

    private void initView() {
        username=getActivity().findViewById( R.id.username );
     //   nickname=getActivity().findViewById( R.id.nickname );
        loginout = getActivity().findViewById(R.id.loginout);
        myinfo = getActivity().findViewById(R.id.myinfo);
        mypush = getActivity().findViewById(R.id.mypush);
        mycomunity = getActivity().findViewById(R.id.mycomunity);
        mycollect = getActivity().findViewById(R.id.mycollect);
        setting = getActivity().findViewById(R.id.setting);
    }
}
