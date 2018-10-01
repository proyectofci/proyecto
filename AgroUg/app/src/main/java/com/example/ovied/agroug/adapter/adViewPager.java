
    package com.example.ovied.agroug.adapter;

    //region Import
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentPagerAdapter;
    import android.support.v4.app.FragmentStatePagerAdapter;

    import java.util.ArrayList;
    import java.util.List;
    //endregion

public class adViewPager extends FragmentPagerAdapter
{
    //region Variables
    private List<Fragment> lsFragment= new ArrayList<>();
    private List<String> lsTitle = new ArrayList<>();
    //endregion

    //region Events

    public adViewPager(FragmentManager frManager)
    {
        super(frManager);
    }



    @Override
    public Fragment getItem(int position)
    {
        return lsFragment.get(position);
    }

    public void addFragment(Fragment myFragment, String title)
    {
        lsFragment.add(myFragment);
        lsTitle.add(title);
    }

    @Override
    public int getCount()
    {
        return lsFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int positions)
    {
        return lsTitle.get(positions);
    }
    //endregion
}
