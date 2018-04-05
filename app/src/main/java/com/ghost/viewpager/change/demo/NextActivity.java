package com.ghost.viewpager.change.demo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends AppCompatActivity {


    private ViewPager viewPager;

    private Button btnTop;

    private int[] imageId = {R.drawable.gril01,
            R.drawable.gril04,
            R.drawable.gril03};

    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        initData();
        initView();
    }

    private void initData() {
        for (int imgId : imageId) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgId);
            imageViews.add(imageView);
        }
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(new MyPagerAdapter(imageViews, NextActivity.this));

        btnTop = findViewById(R.id.btn_top);
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
