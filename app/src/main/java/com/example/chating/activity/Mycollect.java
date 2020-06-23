package com.example.chating.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.chating.R;
import com.example.chating.Fragment.Fragment_comunity;
import com.example.chating.Fragment.Fragment_push;
import com.het.smarttab.SmartTabLayout;
import com.het.smarttab.v4.FragmentPagerItem;
import com.het.smarttab.v4.FragmentPagerItems;
import com.het.smarttab.v4.FragmentStatePagerItemAdapter;


public class Mycollect extends AppCompatActivity {

    private SmartTabLayout smartTabLayout;
    private ViewPager   viewPager;
    private ImageView back;
    private FragmentStatePagerItemAdapter fragadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_mycollet );

        initView();
        viewPager.setOffscreenPageLimit(3);
        initTab();
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );


    }

    private void initTab() {
        String[] tabs=new String[]{"帖子","论坛"};
        FragmentPagerItems pages=new FragmentPagerItems(Mycollect.this);
        for(int i=0;i<tabs.length;i++){
            Bundle args=new Bundle(  );
            args.putString( "name", tabs[i]);
        }

        pages.add( FragmentPagerItem.of( tabs[0] , Fragment_push .class));
        pages.add( FragmentPagerItem.of( tabs[1] , Fragment_comunity.class));

        viewPager.removeAllViews();
        fragadapter=new FragmentStatePagerItemAdapter(  getSupportFragmentManager(),pages);
        viewPager.setAdapter( fragadapter );
        smartTabLayout.setViewPager( viewPager );
    }

    private void initView() {

        smartTabLayout=findViewById( R.id.mycollecttab );
        viewPager=findViewById( R.id.mycollectvp);
        back =findViewById( R.id.back );
    }
}
