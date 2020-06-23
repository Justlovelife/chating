package com.example.chating.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.chating.Adapter.ChatAdapter;
import com.example.chating.Bean.Comunity;
import com.example.chating.R;
import com.example.chating.activity.PushComunity;
import com.example.chating.activity.PushContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class FragmentChat extends Fragment{

    private RecyclerView rv;
    private SwipeRefreshLayout srlayout;
    List<Comunity>data;
    private RelativeLayout rvlayout;
    private FloatingActionButton  add,addcontent,addcomunity;
    private ChatAdapter chatAdapter;
    private PopupWindow pop;
    private View view;

       //论坛界面

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate( R.layout.fragmentchat,container,false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        view=getLayoutInflater().inflate( R.layout.pop_item ,null);

        initView();

        Bmob.initialize(getActivity(),"cd473015f750ae3623c318c168e31d57");


        //初始刷新
        Refresh();

        srlayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                 Refresh();
            }
        } );
         //对flaotbutton监听
        add.setOnClickListener( new View.OnClickListener(){
         //点击+将另外两个显示出来，再点击一次就收起
            @Override
            public void onClick(View v) {
                pop=new PopupWindow( view,250,700,true);
                pop.setOutsideTouchable( true );
                pop.setFocusable( true );
                pop.showAtLocation( rvlayout, Gravity.CENTER,500,300);

            }
        } );

        addcontent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getActivity(), PushContent.class ) );

            }
        } );


        addcomunity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getActivity(), PushComunity.class) );
            }
        } );
    }

        private void Refresh() {

        BmobQuery<Comunity>com=new BmobQuery<>(  );
        com.setLimit( 1000 );
        com.order( "-createdAt" );
        com.findObjects( new FindListener<Comunity>() {
            @Override
            public void done(List<Comunity> list, BmobException e) {
                srlayout.setRefreshing( false );
                if(e==null){
                    data=list;
                    chatAdapter =new ChatAdapter(getActivity(),data);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()) );
                    rv.setAdapter( chatAdapter );
                }
            }
        } );
    }

    private void initView() {
        rv=getActivity().findViewById( R.id.rv1 );
        srlayout=getActivity().findViewById( R.id.swipe1 );
        add=getActivity().findViewById( R.id.add );
        addcontent=view.findViewById( R.id.addcontent );
        addcomunity=view.findViewById( R.id.addcomunity);
        rvlayout=getActivity().findViewById( R.id.rvlayout );
    }
}
