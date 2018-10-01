package com.pruebatecnica.leonardojpr_beetrack.main.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.pruebatecnica.leonardojpr_beetrack.R;
import com.pruebatecnica.leonardojpr_beetrack.main.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Leonardojpr on 06/05/18.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content_tab)
    TabLayout tabLayout;
    @BindView(R.id.content_viewpager)
    ViewPager viewPager;

    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager = findViewById(R.id.content_viewpager);
        tabLayout = findViewById(R.id.content_tab);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
