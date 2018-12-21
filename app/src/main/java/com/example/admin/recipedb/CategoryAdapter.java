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

public class CategoryAdapter extends BaseAdapter
{
    private Context mContext;
    ArrayList<String>modelArrayList;
    ImageView imageView;

    public CategoryAdapter(Context mContext, ArrayList<String> modelArrayList)
    {
        this.mContext = mContext;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount()
    {
        return modelArrayList.size();
    }

    @Override
    public String getItem(int position)
    {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row,parent,false);

        TextView textView = v.findViewById(R.id.textview);
        textView.setText(modelArrayList.get(position));
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
