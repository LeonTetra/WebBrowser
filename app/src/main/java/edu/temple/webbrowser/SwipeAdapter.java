package edu.temple.webbrowser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SwipeAdapter extends FragmentStatePagerAdapter {
    ArrayList<BrowserFragment> browserFragments;
    public SwipeAdapter(FragmentManager fm){
        super(fm);
        browserFragments = new ArrayList<BrowserFragment>();
        BrowserFragment first = BrowserFragment.newInstance();
        browserFragments.add(first);
    }
    @Override
    public Fragment getItem(int i) {
//        Fragment browser = new BrowserFragment();
//        Bundle args = new Bundle();
//        browser.setArguments(args);
        if(i > browserFragments.size()-1){
            BrowserFragment frag = BrowserFragment.newInstance();
            browserFragments.add(i, frag);

        }


        return browserFragments.get(i);
    }

    @Override
    public int getCount() {
        return browserFragments.size();
    }
}
