package com.ghost.viewpager.change.demo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private Button btnNext;

    private int[] imageId = {R.drawable.girl001,
            R.drawable.girl002,
            R.drawable.girl003,
            R.drawable.girl004,
            R.drawable.girl005};

    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Log.e("MainActivity", "initData: " + imageViews.size());
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);

        viewPager.setPageTransformer(true, new DepthPageTransformer());

        viewPager.setAdapter(new MyPagerAdapter(MainActivity.this, imageViews));

        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NextActivity.class));
            }
        });
    }

    class MyPagerAdapter extends PagerAdapter {

        private Context context;
        private List<ImageView> imageViews;

        public MyPagerAdapter(Context context, List<ImageView> imageViews) {
            this.context = context;
            this.imageViews = imageViews;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
            object = null;
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
