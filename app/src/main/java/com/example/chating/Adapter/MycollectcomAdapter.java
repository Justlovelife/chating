package com.example.chating.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chating.Bean.Comunity;
import com.example.chating.Bean.Post;
import com.example.chating.R;
import com.example.chating.activity.Login;
import com.example.chating.activity.Recievecom;
import com.example.chating.activity.Recive;

import java.util.List;

import cn.bmob.v3.BmobUser;

public class MycollectcomAdapter extends   RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Comunity> data;
    private Context context;
    private final int N_TYPE = 0;  //正常
    private final int F_TYPE = 1;  //异常

    private int Max_num = 15;  //预加载的数据  一共15条

    private Boolean isfootview = true;


    public MycollectcomAdapter(List<Comunity> data, Context context) {
        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.mycollect_com, viewGroup, false );
        View footview = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.foot_item, viewGroup, false );
        if (i == F_TYPE) {
            return new MycollectcomAdapter.RecyclerViewHolder( footview, F_TYPE );

        } else {
            return new MycollectcomAdapter.RecyclerViewHolder( view, N_TYPE );

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (isfootview && (getItemViewType( i )) == F_TYPE) {
//底部加载提示
            final MycollectcomAdapter.RecyclerViewHolder recyclerViewHolder = (MycollectcomAdapter.RecyclerViewHolder) viewHolder;
            recyclerViewHolder.Loading.setText( "加载中……" );
            android.os.Handler handler = new Handler();
            handler.postDelayed( new Runnable() {
                @Override
                public void run() {

                    Max_num += 8;   //后面每次加载8条
                    notifyDataSetChanged();
                }
            }, 2000 );


        } else {

//这是mycollect的内容
            final MycollectcomAdapter.RecyclerViewHolder recyclerViewHolder = (MycollectcomAdapter.RecyclerViewHolder) viewHolder;
            final Comunity com = data.get( i );
            recyclerViewHolder.username.setText( com.getName() );
            recyclerViewHolder.info.setText( com.getInfo() );
            recyclerViewHolder.owner.setText( com.getOwner() );
            recyclerViewHolder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = recyclerViewHolder.getAdapterPosition();
                    if (BmobUser.getCurrentUser( BmobUser.class ) != null) {
                        Log.i( "TAG", "点击监听" );
                        Intent in = new Intent( context, Recievecom.class );
                        in.putExtra( "username", com.getName() );
                        in.putExtra( "info", com.getInfo() );
                        in.putExtra( "time", com.getOwner() );
                        in.putExtra( "id", data.get( position ).getObjectId() );
                        context.startActivity( in );
                    } else {
                        Toast.makeText( context, "请登录", Toast.LENGTH_SHORT ).show();
                        context.startActivity( new Intent( context, Login.class ) );
                    }
                }
            } );

        }

    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView username, info, owner;//ord_item的textview
        public TextView Loading;

        public RecyclerViewHolder(View itemview, int view_type) {
            super( itemview );
            if (view_type == N_TYPE) {
                username = itemview.findViewById( R.id.com_name );
                info = itemview.findViewById( R.id.com_info );
                owner = itemview.findViewById( R.id.com_user );

            } else if (view_type == F_TYPE)
                Loading = itemview.findViewById( R.id.footText );
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == Max_num - 1) {
            return F_TYPE;
        } else {
            return N_TYPE;
        }

    }

    @Override
    public int getItemCount() {
        if (data.size() < Max_num) {
            return data.size();
        }
        return Max_num;
    }
}
