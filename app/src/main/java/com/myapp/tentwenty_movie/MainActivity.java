package com.myapp.tentwenty_movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;

import com.myapp.tentwenty_movie.databinding.ActivityMainBinding;
import com.myapp.tentwenty_movie.view.Activity.Activity_Home;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        String simple = "tentwenty";
        String colored = "*";

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();

        builder.setSpan(new ForegroundColorSpan(Color.WHITE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.tvAppname.setText(builder);
        intentFunc();
    }

    private void intentFunc() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
                Intent openHomeActivity = new Intent(MainActivity.this, Activity_Home.class);
                startActivity(openHomeActivity);
                finish();
        }, 2000);

    }
}