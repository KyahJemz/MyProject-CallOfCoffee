package com.example.stephen_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase db;
    String sql;
    Button btn_signin;
    Button btn_register;
    EditText et_username;
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.etUsername);
        et_password = (EditText) findViewById(R.id.etPassword);
        btn_signin = (Button) findViewById(R.id.bntSignIn);
        btn_register = (Button) findViewById(R.id.btnRegister);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = et_username.getText().toString();
                String pass = et_password.getText().toString();

                if (user.matches("admin")&& pass.matches("admin1234")){
                Intent i = new Intent(LoginActivity.this, DatabaseActivity.class);
                startActivity(i);

                }else{
                    if (verifyLogin(user,pass))
                    {
                        db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        sql = "Select user_Id From accounts where username='"+user+"' and password='"+pass+"'";
                        Cursor c = db.rawQuery(sql,null);
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        while (c.moveToNext()) {

                            i.putExtra("pkgId",c.getString(0));
                        }
                        startActivity(i);
                    }
                    else if (user.isEmpty() && pass.isEmpty())
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Wrong Login Info, Pls Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void createDB() {
        db = openOrCreateDatabase("CoffeeDB" , MODE_PRIVATE, null);
        sql = "create table if not exists accounts(user_Id integer PRIMARY KEY AUTOINCREMENT, firstName text, lastName text, age text, mobileNumber text, email text, username text, password text)";
        db.execSQL(sql);
    }

    public boolean verifyLogin(String username, String password) {
        createDB();
        String user = username.toString();
        String pass = password.toString();

        sql = "Select * From accounts where username='"+user+"' and password='"+pass+"'";
        Cursor c = db.rawQuery(sql,null);

        if (c.getCount()>0)
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(LoginActivity.this, "You Must Login", Toast.LENGTH_SHORT).show();
    }
}