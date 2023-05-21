package com.example.quan_ly_thu_chi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quan_ly_thu_chi.fragment.FragmentBaoCao;
import com.example.quan_ly_thu_chi.fragment.FragmentKhac;
import com.example.quan_ly_thu_chi.fragment.FragmentNhapVao;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private int numPage = 3;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new FragmentNhapVao();
            case 1: return new FragmentBaoCao();
            case 2: return new FragmentKhac();
        }
        return new FragmentNhapVao();
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
