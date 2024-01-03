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

public class CtgLocationActivity extends AppCompatActivity {

    Button btn_back;
    SQLiteDatabase db;
    String sql;
    String pkg;
    ListView list;
    ArrayList<String> sLocation = new ArrayList<String>();
    ArrayList<String> sStatus = new ArrayList<String>();
    ArrayList<Integer> sImage = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctg_location);

        pkg = getIntent().getStringExtra("pkgId");

        db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);
        sql = "Select * From location";
        Cursor c = db.rawQuery(sql,null);

        while (c.moveToNext()){
            sImage.add(Integer.parseInt(c.getString(1)));
            sLocation.add(c.getString(2));
            sStatus.add(c.getString(3));
        }

        list = (ListView) findViewById(R.id.lvListView);
        CtgLocationAdapter adapter = new CtgLocationAdapter(getApplicationContext(), sImage, sLocation, sStatus);
        list.setAdapter(adapter);

        btn_back = (Button) findViewById(R.id.btnBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CtgLocationActivity.this, HomeActivity.class);
                    i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed(){
    }
}