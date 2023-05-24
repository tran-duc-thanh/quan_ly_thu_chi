package com.example.quan_ly_thu_chi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quan_ly_thu_chi.adapter.FragmentAdapter;
import com.example.quan_ly_thu_chi.data.SQLiteHelper;
import com.example.quan_ly_thu_chi.ui.login.LoginActivity;
import com.example.quan_ly_thu_chi.utils.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentAdapter adapter;
    private ViewPager viewPager;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteHelper(getApplicationContext());
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPage);
        adapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        bottomNavigationView = findViewById(R.id.navigation);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.bt_nhap_vao).setCheckable(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.bt_bao_cao).setCheckable(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.bt_khac).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bt_nhap_vao:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.bt_bao_cao:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.bt_khac:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opsion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.exit:
                System.exit(0);
                break;
            case R.id.logout:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        db.getAllMenuByStatus(Constants.STATUS.CHI).forEach(rs -> getResources().getDrawable(rs.getIcon()).setTint(getResources().getColor(rs.getColor())));
        db.getAllMenuByStatus(Constants.STATUS.THU).forEach(rs -> getResources().getDrawable(rs.getIcon()).setTint(getResources().getColor(rs.getColor())));
        super.onResume();
    }
}