package com.pruebatecnica.leonardojpr_beetrack.main.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pruebatecnica.leonardojpr_beetrack.R;
import com.pruebatecnica.leonardojpr_beetrack.news.ui.fragment.NewsFragment;
import com.pruebatecnica.leonardojpr_beetrack.util.Commons;
import com.pruebatecnica.leonardojpr_beetrack.util.Constants;
/**
 * Created by Leonardojpr on 06/05/18.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    String[] titles = {Commons.getString(R.string.article), Commons.getString(R.string.favorite)};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewsFragment.getInstance(Constants.NewsSection.ARTICLE);
            case 1:
                return NewsFragment.getInstance(Constants.NewsSection.FAVORITE);
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
