package com.example.stephen_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<Integer> image = new ArrayList<Integer>();
    LayoutInflater inflter;

    public CartAdapter(Context applicationContext, ArrayList<String> name, ArrayList<String> price, ArrayList<Integer> image) {
        this.context = context;
        this.name = name;
        this.price = price;
        this.image = image;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.cart_listview_layout, null);
        TextView tv_name = (TextView) view.findViewById(R.id.tvName);
        TextView tv_price = (TextView) view.findViewById(R.id.tvPrice);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        tv_name.setText(name.get(i));
        tv_price.setText(price.get(i));
        imageView.setImageResource(image.get(i));
        return view;
    }
}
