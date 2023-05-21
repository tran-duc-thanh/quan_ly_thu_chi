package com.example.quan_ly_thu_chi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.quan_ly_thu_chi.R;
import com.example.quan_ly_thu_chi.adapter.FragmentNhapVaoAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentNhapVao extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentNhapVaoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhap_vao, container, false);
        viewPager = view.findViewById(R.id.viewPageThuChi);
        tabLayout = view.findViewById(R.id.tabThuChi);
        adapter = new FragmentNhapVaoAdapter(getChildFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Chi");
        tabLayout.getTabAt(1).setText("Thu");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
