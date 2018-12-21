package com.example.admin.recipedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;

public class DetailMenu extends AppCompatActivity {
    Button next, previous, fav, share;
    DatabaseHelper helper;
    ImageView imageView, previous_act;
    TextView textView, title;
    Bundle b;

    String detailMenu;
    int submenu1;
    ArrayList<Model> modelArrayList = new ArrayList<>();

    String[] images;
    int limit = 0;
    String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_menu);
        init();

        try {
            helper.createDatabase();
            helper.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            images = getAssets().list("images");
        } catch (IOException e) {
            e.printStackTrace();
        }
        b = getIntent().getExtras();

        detailMenu = b.getString("detail");
        submenu1 = b.getInt("position", 0);
        modelArrayList = (ArrayList<Model>) b.getSerializable("Array");
        limit = modelArrayList.size();

        detail(submenu1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submenu1++;
                if (submenu1 >= limit) {
                    submenu1 = 0;
                }
                detail(submenu1);
            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submenu1--;
                if (submenu1 < 0) {
                    submenu1 = limit - 1;
                }
                detail(submenu1);
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model model = modelArrayList.get(submenu1);
                int fav_id = model.getFav();

                if (fav_id == 0) {
                    fav_id = 1;
                } else {
                    fav_id = 0;
                }
                model.setFav(fav_id);
                helper.Update(fav_id, detailMenu);
                detail(submenu1);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share.setBackgroundResource(R.drawable.share_press);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        previous_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailMenu.this.finish();
            }
        });

        Resize();
    }

    private void detail(int position) {
        Model model = modelArrayList.get(position);

        textView.setText(model.getDetail());

        title.setText(model.getSubmenu());

        imagePath = "file:///android_asset/images/" + model.getSubmenu() + ".jpg";
        Glide.with(DetailMenu.this).load(imagePath).into(imageView);
        int fav_id = model.getFav();

        if (fav_id == 0) {
            fav.setBackgroundResource(R.drawable.favorite_unpress);
        } else {
            fav.setBackgroundResource(R.drawable.favorite_press);
        }

    }

    private void init() {
        next = findViewById(R.id.button_next);
        previous = findViewById(R.id.button_prev);
        fav = findViewById(R.id.button_fav);
        imageView = findViewById(R.id.detail_imageView);
        previous_act = findViewById(R.id.previous);
        textView = findViewById(R.id.detail_textview2);
        title = findViewById(R.id.textview);
        textView.setMovementMethod(new ScrollingMovementMethod());
        helper = new DatabaseHelper(this);

        share = findViewById(R.id.button_share);
    }

    private void Resize() {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width * 102 / 720, height * 102 / 1280);
        fav.setLayoutParams(params);
        share.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(width * 200 / 720, height * 200 / 1280);
        imageView.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(width * 90 / 720, height * 90 / 1280);
        previous.setLayoutParams(params);
        next.setLayoutParams(params);
        previous_act.setLayoutParams(params);
    }

    @Override
    protected void onResume() {
        share.setBackgroundResource(R.drawable.share_unpress);
        super.onResume();
    }
}
