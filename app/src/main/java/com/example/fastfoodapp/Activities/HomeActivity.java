package com.example.fastfoodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fastfoodapp.DialogBacktoLogin;
import com.example.fastfoodapp.Fragment.GioHangFragment;
import com.example.fastfoodapp.Fragment.HomeFragment;
import com.example.fastfoodapp.Fragment.UserFragment;
import com.example.fastfoodapp.R;
import com.example.fastfoodapp.Utils.Common;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        if (savedInstanceState == null) {
            chipNavigationBar.setItemSelected(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }
        setFrameLayout();
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        Common.SDTuser = sharedPreferences.getString("sdt",null);
    }

    private void setFrameLayout() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.giohang:
                        fragment = new GioHangFragment();
                        break;
                    case R.id.account:
                        fragment = new UserFragment();
                        break;
                    default:
                        break;
                }
                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }

            }

        });
    }

    private void initUI() {
        chipNavigationBar = findViewById(R.id.bottom_nav);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}