package com.example.stephen_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    Button btn_back;

    ListView list;
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<String> items = new ArrayList<String>();

    SQLiteDatabase db;
    String sql;

    String pkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        pkg = getIntent().getStringExtra("pkgId");

        db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);
        sql = "Select * From userOrder where userId='"+pkg+"'";
        Cursor c = db.rawQuery(sql,null);
        while (c.moveToNext()) {
            price.add(c.getString(2));
            items.add(c.getString(3));
        }
        list = (ListView) findViewById(R.id.lvListView);
        OrderAdapter adapter = new OrderAdapter(getApplicationContext(), price, items);
        list.setAdapter(adapter);

        btn_back = (Button) findViewById(R.id.btnBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderActivity.this, HomeActivity.class);
                    i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed(){
    }
}