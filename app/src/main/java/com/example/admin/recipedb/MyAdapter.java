package com.example.admin.recipedb;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


class MyAdapter extends ArrayAdapter {

    String NAMES = "App name";
    Context context;

    public MyAdapter(@NonNull Context context) {
        super(context, 0);
        this.context = context;
    }

    public int getCount() {
        return 24;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.other_app_layout, parent, false);

            TextView textView = view.findViewById(R.id.textView2);
            ImageView imageView = view.findViewById(R.id.imageView);

            textView.setText(NAMES);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(210,210);
            imageView.setLayoutParams(layoutParams);

            imageView.setImageResource(R.drawable.tp);

        }

        return view;
    }
}
