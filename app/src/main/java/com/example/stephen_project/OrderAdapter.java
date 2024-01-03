package com.example.stephen_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<String> items = new ArrayList<String>();
    LayoutInflater inflter;

    public OrderAdapter(Context applicationContext, ArrayList<String> price, ArrayList<String> items) {
        this.context = context;
        this.price = price;
        this.items = items;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return items.size();
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
        view = inflter.inflate(R.layout.order_listview_layout, null);
        TextView tv_price = (TextView) view.findViewById(R.id.tvPrice);
        TextView tv_item = (TextView) view.findViewById(R.id.tvItem);
        tv_price.setText("Total Price: "+price.get(i)+" Pesos");
        tv_item.setText(items.get(i));
        return view;
    }
}
