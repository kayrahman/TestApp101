package com.nkr.testapp101.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nkr.testapp101.R;

/**
 * Created by neel on 18/08/2016.
 */
public class ViewPagerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_viewpager,container,false);

        final LoginFragment loginFragment=new LoginFragment();
        final SignUpFragment signUpFragment=new SignUpFragment();


        ViewPager viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return loginFragment;

                    case 1:
                        return signUpFragment;

                    default:
                        return null;

                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Login";

                    case 1:
                        return "SignUp";

                    default:
                        return "";

                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });


        TabLayout tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);



        return view;
    }
}
