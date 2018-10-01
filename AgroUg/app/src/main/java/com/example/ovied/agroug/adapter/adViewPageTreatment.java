package com.example.ovied.agroug.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class adViewPageTreatment  extends FragmentPagerAdapter
{

    //region Variables
    private List<Fragment> lsFragment1= new ArrayList<>();
    private List<String> lsTitle1 = new ArrayList<>();
    //endregion

    //region Events

    public adViewPageTreatment(FragmentManager frManager)
    {
        super(frManager);
    }



    @Override
    public Fragment getItem(int position)
    {
        return lsFragment1.get(position);
    }

    public void addFragment(Fragment myFragment, String title)
    {
        lsFragment1.add(myFragment);
        lsTitle1.add(title);
    }

    @Override
    public int getCount()
    {
        return lsFragment1.size();
    }

    @Override
    public CharSequence getPageTitle(int positions)
    {
        return lsTitle1.get(positions);
    }
    //endregion

}
