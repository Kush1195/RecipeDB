package com.example.admin.recipedb;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class Favourite extends Fragment
{
    private ListView listView;
    private DatabaseHelper databaseHelper;
    ArrayList<Model>arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_favourite, container, false);

        init(view);

        try
        {
            databaseHelper.createDatabase();
            databaseHelper.openDatabase();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        return view;
    }

    @Override
    public void onResume()
    {
        Cursor c = databaseHelper.getFavourite();
        final ArrayList<String>fav = new ArrayList<>();
        arrayList.clear();

        if (c.moveToLast())
        {
            do
            {
                fav.add(c.getString(2));
                arrayList.add(new Model(c.getString(0),c.getInt(1),c.getString(2),c.getString(3),c.getInt(4)));
            }
            while (c.moveToPrevious());
        }

        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(getActivity(),fav);
        listView.setAdapter(favouriteAdapter);
        favouriteAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(getActivity(),DetailMenu.class);
                intent.putExtra("detail",arrayList.get(i).getSubmenu());
                intent.putExtra("position",i);
                intent.putExtra("Array",arrayList);
                startActivity(intent);
            }
        });
        super.onResume();
    }

    private void init(View view)
    {
        listView = view.findViewById(R.id.fav_listview);
        databaseHelper = new DatabaseHelper(getActivity());
    }
}
