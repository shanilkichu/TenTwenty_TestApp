package com.myapp.tentwenty_movie.view.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myapp.tentwenty_movie.R;
import com.myapp.tentwenty_movie.databinding.ActivityHomeBinding;
import com.myapp.tentwenty_movie.view.Fragment.HomeFragment;

public class Activity_Home extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView(savedInstanceState);

    }

    private void initView(Bundle savedInstanceState) {

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,

                    new HomeFragment()).commit();

        }

        fragmentFunc();

    }

    private void fragmentFunc() {
         BottomNavigationView.OnNavigationItemSelectedListener navListener =

                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override

                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {

                            case R.id.nav_dashboard:

                                selectedFragment = new HomeFragment();

                                break;

                            case R.id.nav_watch:

                                selectedFragment = new HomeFragment();

                                break;

                            case R.id.nav_medialibrary:

                                selectedFragment = new HomeFragment();

                                break;

                            case R.id.nav_more:

                                selectedFragment = new HomeFragment();

                                break;

                        }

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,

                                selectedFragment).commit();

                        return true;

                    }

                };
    }
}
