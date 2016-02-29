package com.codepath.apps.restclienttemplate.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codepath.apps.restclienttemplate.fragment.MentionsFragment;
import com.codepath.apps.restclienttemplate.fragment.TimelineFragment;

/**
 * Created by skammila on 2/24/16.
 */
public class TwitterFragmentPagerAdapter  extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Timeline", "Mentions" };
    private Context context;
    public TimelineFragment tlFm;
    public MentionsFragment mnFm;

    public TwitterFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        this.tlFm = TimelineFragment.newInstance("", "");
        mnFm = MentionsFragment.newInstance("", "");
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            if (this.tlFm != null)
                return this.tlFm;
            else {
                this.tlFm = TimelineFragment.newInstance("", "");
                return this.tlFm;
            }
        } else {
            if (mnFm != null) {
                return mnFm;
            } else {
                mnFm = MentionsFragment.newInstance("", "");
                return mnFm;
            }
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}