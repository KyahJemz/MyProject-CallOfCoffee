package com.example.stephen_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_next;
    SQLiteDatabase db;
    String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDB();

        btn_next = (Button) findViewById(R.id.btnNext);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    public void createDB() {
        Cursor c;
        db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);
        sql = "create table if not exists accounts(user_Id integer PRIMARY KEY AUTOINCREMENT, firstName text, lastName text, age text, mobileNumber text, email text, username text, password text)";
        db.execSQL(sql);
        sql = "Select * From accounts";
        c = db.rawQuery(sql,null);
        if (c.getCount()>0) {
        } else {
            sql = "INSERT INTO accounts(firstName, lastName, age, mobileNumber, email, username, password) VALUES ('Administrator','Admin','1','1','Admin','admin','admin1234')";
            db.execSQL(sql);
        }


        sql = "create table if not exists menu(item_Id integer PRIMARY KEY AUTOINCREMENT, itemImage text, itemName text, itemPrice text)";

        db.execSQL(sql);

        sql = "Select * From menu";

        Cursor c = db.rawQuery(sql,null);

        if (c.getCount()>0) {

        } else {
            sql = "INSERT INTO menu(itemImage, itemName, itemPrice) VALUES ('"+R.drawable.coffee_cup+"','Regular Coffee','50')";
            db.execSQL(sql);
            sql = "INSERT INTO menu(itemImage, itemName, itemPrice) VALUES ('"+R.drawable.coffee_large+"','Large Coffee','75')";
            db.execSQL(sql);
            sql = "INSERT INTO menu(itemImage, itemName, itemPrice) VALUES ('"+R.drawable.coffee_iced+"','Iced Coffee','60')";
            db.execSQL(sql);
            sql = "INSERT INTO menu(itemImage, itemName, itemPrice) VALUES ('"+R.drawable.coffee_crackers+"','Coffee Crackers','55')";
            db.execSQL(sql);
            sql = "INSERT INTO menu(itemImage, itemName, itemPrice) VALUES ('"+R.drawable.bubble_tea+"','Bubble Tea','100')";
            db.execSQL(sql);
            sql = "INSERT INTO menu(itemImage, itemName, itemPrice) VALUES ('"+R.drawable.coffee_bag+"','Coffee Powder','45')";
            db.execSQL(sql);
        }

        sql = "create table if not exists location(location_Id integer PRIMARY KEY AUTOINCREMENT, locationImage text, locationName text, isOpen text)";
        db.execSQL(sql);
        sql = "Select * From location";
        c = db.rawQuery(sql,null);
        if (c.getCount()>0) {
        } else {
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','Cavite City','Open (6am - 10pm)')";
            db.execSQL(sql);
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','Trece Martires City','Open (6am - 10pm)')";
            db.execSQL(sql);
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','Tanza','Open (7am - 11pm)')";
            db.execSQL(sql);
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','Indang','Open (6am - 10pm)')";
            db.execSQL(sql);
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','City of Bacoor','Open (7am - 11pm)')";
            db.execSQL(sql);
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','Tagaytay City','Open (7am - 11pm)')";
            db.execSQL(sql);
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','Kawit','Open (6am - 10pm)')";
            db.execSQL(sql);
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','Imus','Open (7am - 11pm)')";
            db.execSQL(sql);
            sql = "INSERT INTO location(locationImage, locationName, isOpen) VALUES ('"+R.drawable.coffee_location+"','City of Dasmariñas','Open (7am - 11pm)')";
            db.execSQL(sql);
        }
        sql = "create table if not exists userCart(cart_Id integer PRIMARY KEY AUTOINCREMENT, userId text, itemId text, isOrdered text)";
        db.execSQL(sql);
        sql = "create table if not exists userOrder(order_Id integer PRIMARY KEY AUTOINCREMENT, userId text, totalPrice text, fullOrder text)";
        db.execSQL(sql);
    }

    @Override
    public void onBackPressed(){
    }
}