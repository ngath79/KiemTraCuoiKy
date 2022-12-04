package com.example.venom;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.venom.PhieuMuon.PhieuMMuonFragment;
import com.example.venom.staff.Staff_Management;

public class ViewManagerAdapter  extends FragmentStatePagerAdapter {
    public ViewManagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new TheLoaiFragment();
            case 2:
                return new PhieuMMuonFragment();
            case 3:
                return new Staff_Management();
            case 4:
                return new MemberFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}


