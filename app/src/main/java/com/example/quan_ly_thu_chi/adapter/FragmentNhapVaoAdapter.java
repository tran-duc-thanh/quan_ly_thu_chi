package com.example.quan_ly_thu_chi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quan_ly_thu_chi.fragment.FragmentBaoCao;
import com.example.quan_ly_thu_chi.fragment.FragmentChi;
import com.example.quan_ly_thu_chi.fragment.FragmentKhac;
import com.example.quan_ly_thu_chi.fragment.FragmentNhapVao;
import com.example.quan_ly_thu_chi.fragment.FragmentThu;

public class FragmentNhapVaoAdapter extends FragmentStatePagerAdapter {

    private int numPage = 2;

    public FragmentNhapVaoAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new FragmentChi();
            case 1: return new FragmentThu();
        }
        return new FragmentChi();
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
