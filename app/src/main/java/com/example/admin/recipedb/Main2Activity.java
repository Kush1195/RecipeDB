package com.example.admin.recipedb;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codemybrainsout.ratingdialog.RatingDialog;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Main2Activity extends AppCompatActivity {

    ViewPager viewPager;
    ImageView imageView_all,imageView_fav,imageView_other,drawer_icon,image;
    ImageView home_press,rate_us,more_app,share_app;
    TextView textView_all,textView_fav,textView_other,title_textview;
    LinearLayout linear_layout;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        init();

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        textView_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);

            }
        });

        textView_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        textView_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i)
                {
                    case 0:
                        imageView_all.setVisibility(View.VISIBLE);
                        title_textview.setText("Category");
                        imageView_other.setVisibility(View.INVISIBLE);
                        imageView_fav.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        title_textview.setText("Racipe");
                        imageView_all.setVisibility(View.INVISIBLE);
                        imageView_fav.setVisibility(View.VISIBLE);
                        imageView_other.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        title_textview.setText("Racipe");
                        imageView_fav.setVisibility(View.INVISIBLE);
                        imageView_all.setVisibility(View.INVISIBLE);
                        imageView_other.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        drawer_icon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        home_press.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        rate_us.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               showDialog();
            }
        });

        more_app.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               drawerLayout.closeDrawer(Gravity.START, false);
               viewPager.setCurrentItem(2);
            }
        });

        share_app.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                StringBuilder sb = new StringBuilder();
                sb.append("Hi, I am using the Recipe App for various new dishes. I like this and I want you to check it out.");
                sb.append("https://play.google.com/store/apps/details?id=" + Main2Activity.this.getPackageName());
                sharingIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Test");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                startActivity(Intent.createChooser(sharingIntent, "Test"));
            }
        });

        Resize();
    }
    private void showDialog() {

        final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                .session(1)
                .threshold(1)
                .ratingBarColor(R.color.colorAccent)
                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                    @Override
                    public void onFormSubmitted(String feedback) {
                        //Log.i(TAG,"Feedback:" + feedback);
                    }
                })
                .build();

        ratingDialog.show();
    }
    private void Resize()
    {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width * 558 / 720, height * 1280 / 1280);
        linear_layout.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(width * 549 / 720, height * 447 / 1280);
        image.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(width * 464 / 720, height * 100 / 1280);
        home_press.setLayoutParams(params);
        rate_us.setLayoutParams(params);
        more_app.setLayoutParams(params);
        share_app.setLayoutParams(params);
    }

    private void init()
    {
        viewPager = findViewById(R.id.ViewPager);

        imageView_all = findViewById(R.id.All_image);
        imageView_fav = findViewById(R.id.Fav_image);
        imageView_other = findViewById(R.id.Other_image);

        textView_all = findViewById(R.id.text_all);
        textView_fav = findViewById(R.id.text_fav);
        textView_other = findViewById(R.id.text_other);

        title_textview = findViewById(R.id.title_text);
        linear_layout = findViewById(R.id.drawer_layout);

        drawer_icon = findViewById(R.id.drawer_icon);
        image = findViewById(R.id.image);

        home_press = findViewById(R.id.home_press);
        rate_us = findViewById(R.id.rate_us);
        more_app = findViewById(R.id.more_app);
        share_app = findViewById(R.id.share_app);

        drawerLayout = findViewById(R.id.drawer);
    }
}
