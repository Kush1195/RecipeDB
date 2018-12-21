package com.example.admin.recipedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FavouriteAdapter extends BaseAdapter
{
    private Context mContext;
    ArrayList<String> modelArrayList;
    ImageView imageView;

    public FavouriteAdapter(Context mContext, ArrayList<String> modelArrayList) {
        this.mContext = mContext;
        this.modelArrayList = modelArrayList;

    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row, viewGroup,false);

        TextView textView = v.findViewById(R.id.textview);
        textView.setText(modelArrayList.get(i));

        imageView = v.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.batatavada);
        
        Resize();
        return v;
    }
    private void Resize()
    {
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int height = mContext.getResources().getDisplayMetrics().heightPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width * 150 / 720, height * 150 / 1280);

        imageView.setLayoutParams(params);
    }
}
