package com.example.stephen_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    Button btn_back, btnOrderNow;

    SQLiteDatabase db;
    String sql;

    int total = 0;
    String items = "";

    String pkg;

    ListView list;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> price = new ArrayList<>();
    ArrayList<Integer> image = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        pkg = getIntent().getStringExtra("pkgId");


        refresher();

        list = (ListView) findViewById(R.id.lvListView);
        CartAdapter adapter = new CartAdapter(getApplicationContext(), name, price, image);
        list.setAdapter(adapter);

        btn_back = (Button) findViewById(R.id.btnBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, HomeActivity.class);
                    i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });

        btnOrderNow = (Button) findViewById(R.id.btnOrderNow);
        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (total == 0){
                    Toast.makeText(CartActivity.this, "Bumili ka muna, Eyy", Toast.LENGTH_SHORT).show();
                } else {
                    sql = "Insert Into userOrder(userId, totalPrice, fullOrder) Values('"+pkg+"', '"+total+"','"+items+"') ";
                    db.execSQL(sql);
                    btnOrderNow.setText("Buy Now");

                    db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);
                    sql = "Select * From userCart where userId='"+pkg+"' and isOrdered='0'";
                    Cursor c = db.rawQuery(sql,null);
                    while (c.moveToNext()) {
                        String id = c.getString(2);
                        String sql1 = "Select * From menu where item_Id="+id;
                        Cursor c2 = db.rawQuery(sql1,null);
                        while (c2.moveToNext()){
                            sql = "Update userCart set isOrdered='1' where userid = '"+pkg+"'";
                            db.execSQL(sql);
                        }
                    }
                    name.clear();
                    price.clear();
                    image.clear();
                    adapter.notifyDataSetChanged();
                    Intent i = new Intent(CartActivity.this, OrderActivity.class);
                    i.putExtra("pkgId",pkg);
                    startActivity(i);
                }
            }
        });
        String text = "Total: "+total+" (Buy Now)";
        btnOrderNow.setText(text);
    }

    public void refresher (){
        db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);
        sql = "Select * From userCart where userId='"+pkg+"' and isOrdered='0'";
        Cursor c = db.rawQuery(sql,null);
        int num;



        while (c.moveToNext()) {
            String id = c.getString(2);
            String sql1 = "Select * From menu where item_Id="+id;
            Cursor c2 = db.rawQuery(sql1,null);


            while (c2.moveToNext()){
                name.add(c2.getString(2));
                price.add(c2.getString(3));
                image.add(Integer.parseInt(c2.getString(1)));
                num = 12;

                total = total + num;
                if(items.matches("")){
                    items = c2.getString(2);
                } else {
                    items = items + "\n" + c2.getString(2);
                }
            }
        }


        list = (ListView) findViewById(R.id.lvListView);
        CartAdapter adapter = new CartAdapter(getApplicationContext(), name, price, image);
        list.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
    }
}