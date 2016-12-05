package com.meditab.commonutils.widgets.easyviewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.List;

/**
 * Created by MiteshP on 05/05/16.
 */
public class EasyViewPager extends ViewPager {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public EasyViewPager(Context context) {
        super(context);
    }

    public EasyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initialize(List<Fragment> fragmentList, List<String> titleList, FragmentManager fragmentManager) {
        this.mFragmentList = fragmentList;
        this.mTitleList = titleList;
        setAdapter(new PagerAdapter(fragmentManager));
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


    }
}
