package com.example.chating;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.chating.R;
import com.example.chating.Adapter.SectionPagerAdapter;
import com.example.chating.Fragment.FragmentChat;
import com.example.chating.Fragment.FragmentHome;
import com.example.chating.Fragment.FragmentMine;
import com.example.chating.Bean.User;
import com.example.chating.activity.MyPush;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnAdapterChangeListener {


    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;

    private List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        viewPager=findViewById( R.id.viewpager );
        bottomNavigationBar=findViewById( R.id.bottom );


        initView();

    }

    private void initView() {

        initViewPager();
        initBottomNagationBar();

    }
    private  void  initBottomNagationBar(){
        bottomNavigationBar.setTabSelectedListener( this );
        bottomNavigationBar.clearAll();;
        bottomNavigationBar.setMode( BottomNavigationBar.MODE_FIXED );//自适应大小
        bottomNavigationBar.setBackgroundStyle( BottomNavigationBar.BACKGROUND_STYLE_DEFAULT );
        bottomNavigationBar.setBarBackgroundColor( R.color.white )
                .setActiveColor( R.color.colorBase1 )
                .setActiveColor( R.color.black );
        bottomNavigationBar.addItem( new BottomNavigationItem( R.drawable.homepage_fill,"首页" ).setInactiveIconResource( R.drawable.homepage ) )
                .addItem( new BottomNavigationItem( R.drawable.mobilephone_fill,"论坛" ).setInactiveIconResource( R.drawable.mobilephone ) )
                .addItem( new BottomNavigationItem( R.drawable.mine_fill,"我的" ).setInactiveIconResource( R.drawable.mine  ) )
                .setFirstSelectedPosition( 0 )
                .initialise();

    }

    private void initViewPager() {
        //配置ViewPager
        viewPager.setOffscreenPageLimit( 3 );
        fragments=new ArrayList<Fragment>() ;
        fragments.add(new FragmentHome());
        fragments.add(new FragmentChat());
        fragments.add(new FragmentMine());

        viewPager.setAdapter( new SectionPagerAdapter( getSupportFragmentManager(),fragments ) );
        viewPager.addOnAdapterChangeListener(this );
        viewPager.setCurrentItem( 0 );
    }

    public  void onPageSelected(int i)
    {
        bottomNavigationBar.selectTab( i );
    }
    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem( position );

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {

    }


}
//        BmobUser user=BmobUser.getCurrentUser(User.class  );
//        String id=user.getObjectId();
//        BmobQuery<User> myuser=new BmobQuery<User>();
//        myuser.getObject( id, new QueryListener<User>() {
//            @Override
//            public void done(User user, BmobException e) {
//                if (e==null){
//                    username.setText( user.getUsername() );
//                    nickname.setText( user.getNickname() );
//                }else{
//                    Toast.makeText(MainActivity.this,"查询失败",Toast.LENGTH_SHORT).show();
//                }
//            }
//        } );