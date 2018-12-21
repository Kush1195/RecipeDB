package com.example.admin.recipedb;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.IOException;
import java.util.ArrayList;

public class Category extends Fragment
{
    private ListView listView;
    private DatabaseHelper mDBHelper;
    ImageView previous,previous1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_category, container, false);

        init(view);

        try
        {
            mDBHelper.createDatabase();
            mDBHelper.openDatabase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Cursor c = mDBHelper.getCategory();
        final ArrayList<String>allCate = new ArrayList<>();

        if (c.moveToFirst())
        {
            do
            {
                allCate.add(c.getString(0));
            }
            while (c.moveToNext());
        }

        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(),allCate);
        listView.setAdapter(categoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getActivity(),SubMenu.class);
                intent.putExtra("menu",allCate.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    private void init(View view)
    {
        listView = view.findViewById(R.id.category_list);
        previous = view.findViewById(R.id.previous);
        previous1 = view.findViewById(R.id.previous1);
        mDBHelper = new DatabaseHelper(getActivity());
    }
}
