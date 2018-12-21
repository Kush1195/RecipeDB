package com.example.admin.recipedb;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class SubMenu extends AppCompatActivity {
    ListView listView;
    ImageView previous_activity, previous1;
    DatabaseHelper databaseHelper;
    Bundle b;
    String mainCategory;
    ArrayList<Model> modelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sub_menu);
        init();

        databaseHelper = new DatabaseHelper(this);

        try {
            databaseHelper.createDatabase();
            databaseHelper.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        b = getIntent().getExtras();
        mainCategory = b.getString("menu");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SubMenu.this, DetailMenu.class);
                intent.putExtra("detail", modelArrayList.get(position).getSubmenu());
                intent.putExtra("position", modelArrayList.get(position).getId());
                intent.putExtra("Array", modelArrayList);
                startActivity(intent);
            }
        });
        previous_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubMenu.this.finish();
            }
        });
        Resize();
    }

    @Override
    protected void onResume() {
        super.onResume();

        modelArrayList.clear();
        Cursor c = databaseHelper.CategoryData(mainCategory);
        if (c.moveToFirst()) {
            do {
                String menu = c.getString(0);
                int id = c.getInt(1);
                String submenu = c.getString(2);
                String detail = c.getString(3);
                int fav = c.getInt(4);

                modelArrayList.add(new Model(menu, id, submenu, detail, fav));
            }
            while (c.moveToNext());
        }

        final SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(this, modelArrayList);
        listView.setAdapter(subCategoryAdapter);
    }

    private void Resize() {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width * 90 / 720, height * 90 / 1280);
        previous_activity.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(width * 60 / 720, height * 90 / 1280);
        previous1.setLayoutParams(params);
    }


    private void init() {
        listView = findViewById(R.id.submenu_listview);
        previous_activity = findViewById(R.id.previous);
        previous1 = findViewById(R.id.previous1);
    }
}
