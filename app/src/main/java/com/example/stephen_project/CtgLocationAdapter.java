package com.example.stephen_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CtgLocationAdapter extends BaseAdapter {
    Context context;
    ArrayList<Integer> sImage = new ArrayList<Integer>();
    ArrayList<String> sLocation = new ArrayList<String>();
    ArrayList<String> sStatus = new ArrayList<String>();
    LayoutInflater inflter;

    public CtgLocationAdapter(Context applicationContext, ArrayList<Integer> sImage, ArrayList<String> sLocation, ArrayList<String> sStatus) {
        this.context = context;
        this.sImage = sImage;
        this.sLocation = sLocation;
        this.sStatus = sStatus;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return sLocation.size();
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
        view = inflter.inflate(R.layout.location_listview_layout, null);
        TextView tv_location = (TextView) view.findViewById(R.id.tvLocation);
        TextView tv_status = (TextView) view.findViewById(R.id.tvStatus);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        tv_location.setText(sLocation.get(i));
        tv_status.setText(sStatus.get(i));
        imageView.setImageResource(sImage.get(i));
        return view;
    }
}
