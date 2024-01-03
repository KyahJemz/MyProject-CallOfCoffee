package com.example.stephen_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button btn_ctg1, btn_ctg2, btn_back;
    ImageView iv_cartlist, iv_orderlist;
    TextView tv_userWelcome;
    String pkg;
    SQLiteDatabase db;
    String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pkg = getIntent().getStringExtra("pkgId");
        db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);
        sql = "Select firstName From accounts where user_Id='"+pkg+"'";
        Cursor c = db.rawQuery(sql,null);

        btn_ctg1 = (Button) findViewById(R.id.btnCtg1);
        btn_ctg2 = (Button) findViewById(R.id.btnCtg2);
        btn_back = (Button) findViewById(R.id.btnBack) ;
        tv_userWelcome = (TextView) findViewById(R.id.tvUserWelcome);
        iv_cartlist = (ImageView) findViewById(R.id.ivCartList);
        iv_orderlist = (ImageView) findViewById(R.id.ivOrderList);

        if(c != null) {
            while(c.moveToNext()) {
                tv_userWelcome.setText("Hi, " + c.getString(0));
            }
        }

        iv_orderlist.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, OrderActivity.class);
                    i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        }));

        iv_cartlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, CartActivity.class);
                    i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });

        btn_ctg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, CtgMenuActivity.class);
                    i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });
        btn_ctg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, CtgLocationActivity.class);
                    i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                    i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed(){
    }
}