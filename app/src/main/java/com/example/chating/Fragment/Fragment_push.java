package com.example.chating.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.chating.Adapter.MycollectpushAdapter;
import com.example.chating.Bean.Post;
import com.example.chating.Bean.User;
import com.example.chating.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Fragment_push extends Fragment {

    private SwipeRefreshLayout  swipeRefreshLayout;
    private RecyclerView recyclerView1;
    private  List<Post>data;
    private TextView error_push;
    private MycollectpushAdapter mycollectpushAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate( R.layout.fragment_push,container,false );
        
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        initView();

        Refresh();

        swipeRefreshLayout.setColorSchemeResources( android.R.color.holo_green_light,android.R.color .holo_red_light,android.R.color.holo_blue_light);

        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }
        } );
    }

    private void Refresh() {

        User user= BmobUser.getCurrentUser( User.class );
        BmobQuery<Post>bmobQuery=new BmobQuery<>(  );
        bmobQuery.addWhereEqualTo( "relation",user );
        bmobQuery.findObjects( new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                swipeRefreshLayout.setRefreshing( false );
                if(e==null)
                {
                    if(list.size()>0)
                    {
                        swipeRefreshLayout.setVisibility( View.VISIBLE );
                        data=list;
                        mycollectpushAdapter=new MycollectpushAdapter(  data,getActivity());
                        recyclerView1.setLayoutManager( new LinearLayoutManager( getActivity() ) );
                        recyclerView1.setAdapter( mycollectpushAdapter );
                    }
                    else
                    {
                        error_push.setVisibility( View.VISIBLE );
                        swipeRefreshLayout.setVisibility( View.GONE );
                    }

                }
                else
                {
                    swipeRefreshLayout.setRefreshing( false );
                    Toast.makeText( getActivity(),"获取失败",Toast.LENGTH_SHORT ).show();
                }
            }
        } );


    }

    private void initView() {
        swipeRefreshLayout=getActivity().findViewById( R.id. swipe_push);
        recyclerView1=getActivity().findViewById( R.id.rv_push );
        error_push=getActivity().findViewById( R.id.error_push );

    }
}
