package com.example.admin.recipedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
{
    ImageView btn_start,btn_fav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.start_btn);
        btn_fav = findViewById(R.id.fav_btn);

        btn_start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                btn_start.setImageResource(R.drawable.start_pressed);
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        btn_fav.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                btn_fav.setImageResource(R.drawable.favourite_pressed);
                Intent intent = new Intent(MainActivity.this, Favourite.class);
                startActivity(intent);
            }
        });

        Resize();
    }

    @Override
    protected void onPostResume()
    {
        btn_start.setImageResource(R.drawable.start_unpressed);
        btn_fav.setImageResource(R.drawable.favourite_unpressed);
        super.onPostResume();
    }

    private void Resize()
    {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width * 329 / 720, height * 86 / 1280);

        btn_start.setLayoutParams(params);
        params = new LinearLayout.LayoutParams(width * 329 / 720, height * 86 / 1280);
        params.topMargin = height * 40 / 1280;
        btn_fav.setLayoutParams(params);
    }
}
