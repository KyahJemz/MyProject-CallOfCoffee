package com.example.stephen_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CtgMenuActivity extends AppCompatActivity {

    Button btn_back;
    ImageView iv_CartList;
    String pkg;
    SQLiteDatabase db;
    String sql;
    Button btn1, btn2, btn3, btn4, btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctg_menu);

        pkg = getIntent().getStringExtra("pkgId");

        btn_back = (Button) findViewById(R.id.btnBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CtgMenuActivity.this, HomeActivity.class);
                i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });

        btn1 = (Button) findViewById(R.id.btnItem1) ;
        btn2 = (Button) findViewById(R.id.btnItem2) ;
        btn3 = (Button) findViewById(R.id.btnItem3) ;
        btn4 = (Button) findViewById(R.id.btnItem4) ;
        btn5 = (Button) findViewById(R.id.btnItem5) ;
        btn6 = (Button) findViewById(R.id.btnItem6) ;

        db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);

        iv_CartList = (ImageView) findViewById(R.id.ivCartList);
        iv_CartList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CtgMenuActivity.this, CartActivity.class);
                i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sql = "INSERT INTO userCart(userId, itemId, isOrdered) VALUES ('"+pkg+"','1','0')";
                db.execSQL(sql);
                Toast.makeText(CtgMenuActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sql = "INSERT INTO userCart(userId, itemId, isOrdered) VALUES ('"+pkg+"','2','0')";
                db.execSQL(sql);
                Toast.makeText(CtgMenuActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sql = "INSERT INTO userCart(userId, itemId, isOrdered) VALUES ('"+pkg+"','3','0')";
                db.execSQL(sql);
                Toast.makeText(CtgMenuActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sql = "INSERT INTO userCart(userId, itemId, isOrdered) VALUES ('"+pkg+"','4','0')";
                db.execSQL(sql);
                Toast.makeText(CtgMenuActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sql = "INSERT INTO userCart(userId, itemId, isOrdered) VALUES ('"+pkg+"','5','0')";
                db.execSQL(sql);
                Toast.makeText(CtgMenuActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sql = "INSERT INTO userCart(userId, itemId, isOrdered) VALUES ('"+pkg+"','6','0')";
                db.execSQL(sql);
                Toast.makeText(CtgMenuActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
    }
}