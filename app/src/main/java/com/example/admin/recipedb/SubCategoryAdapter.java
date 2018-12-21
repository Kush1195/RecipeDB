package com.example.admin.recipedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SubCategoryAdapter extends BaseAdapter
{
    private Context mContext;
    ArrayList<Model>modelArrayList;

    public SubCategoryAdapter(Context mContext, ArrayList<Model> modelArrayList)
    {
        this.mContext = mContext;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row,parent,false);

        Model model = modelArrayList.get(position);
        TextView textView = v.findViewById(R.id.textview);
        textView.setText(model.getSubmenu());
        return v;
    }
}
