package com.example.stephen_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CtgMenuAdapter extends BaseAdapter {
    Context context;
    ArrayList<Integer> id;
    ArrayList<String> name;
    ArrayList<String> price;
    ArrayList<Integer> image;
    LayoutInflater inflter;

    public CtgMenuAdapter(Context applicationContext, ArrayList<Integer> id, ArrayList<String> name, ArrayList<String> price, ArrayList<Integer> image) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.menu_gridview_layout, null);
        TextView tv_name = (TextView) view.findViewById(R.id.tvName);
        TextView tv_price = (TextView) view.findViewById(R.id.tvPrice);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Button btn_btn = (Button) view.findViewById(R.id.btnItem);

        btn_btn.setTag(id.get(i));
        tv_name.setText(name.get(i));
        tv_price.setText(price.get(i));
        imageView.setImageResource(image.get(i));

        btn_btn.setOnClickListener(v -> {
            v.setClickable(false);
        });


        return view;
    }
}
