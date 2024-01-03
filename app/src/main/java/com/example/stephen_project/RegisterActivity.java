package com.example.stephen_project;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    SQLiteDatabase db;
    String sql;
    EditText et_firstname;
    EditText et_lastname;
    EditText et_age;
    EditText et_mobilenumber;
    EditText et_email;
    EditText et_username;
    EditText et_password;
    EditText et_confirmpassword;
    Button btn_back, btn_clear, btn_register;
    CheckBox cb_checkbox;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_firstname = (EditText) findViewById(R.id.etFirstName);
        et_lastname = (EditText) findViewById(R.id.etLastName);
        et_age = (EditText) findViewById(R.id.etAge);
        et_mobilenumber = (EditText) findViewById(R.id.etMobileNumber);
        et_email = (EditText) findViewById(R.id.etEmail);
        et_username = (EditText) findViewById(R.id.etUsername);
        et_password = (EditText) findViewById(R.id.etPassword);
        et_confirmpassword = (EditText) findViewById(R.id.etConfirmPassword);
        cb_checkbox = (CheckBox) findViewById(R.id.cbCheckBox);
        btn_back = (Button) findViewById(R.id.btnBack);
        btn_clear = (Button) findViewById(R.id.btnClear);
        btn_register = (Button) findViewById(R.id.btnRegister);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_firstname.setText("");
                et_lastname.setText("");
                et_age.setText("");
                et_mobilenumber.setText("");
                et_email.setText("");
                et_username.setText("");
                et_password.setText("");
                et_confirmpassword.setText("");
                cb_checkbox.setChecked(false);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = et_firstname.getText().toString();
                String lastname = et_lastname.getText().toString();
                String age = et_age.getText().toString();
                String mobilenumber = et_mobilenumber.getText().toString();
                String email = et_email.getText().toString();
                String username = et_username.getText().toString();
                String passowrd = et_password.getText().toString();
                String confirmpassword = et_confirmpassword.getText().toString();

                if (firstname.isEmpty() || lastname.isEmpty() || age.isEmpty() || mobilenumber.isEmpty() || email.isEmpty() || username.isEmpty() || passowrd.isEmpty() || confirmpassword.isEmpty())
                    Toast.makeText(RegisterActivity.this, "! Invalid Input !", Toast.LENGTH_SHORT).show();
                else if (firstname.matches("0") || lastname.matches("0") || age.matches("0") || mobilenumber.matches("0") || email.matches("0") || username.matches("0") || passowrd.matches("0") || confirmpassword.matches("0"))
                    Toast.makeText(RegisterActivity.this, "! Values Cannot be 0 !", Toast.LENGTH_SHORT).show();
                else if (!(passowrd.matches(confirmpassword)))
                    Toast.makeText(RegisterActivity.this, "! Password Does Not Match !", Toast.LENGTH_SHORT).show();
                else if (verifyUsername(username))
                    Toast.makeText(RegisterActivity.this, "! Username Already Taken, Try again !", Toast.LENGTH_SHORT).show();
                else if (cb_checkbox.isChecked()) {
                    if (AddAccount(firstname, lastname, age, mobilenumber, email, username, passowrd)) {
                        Toast.makeText(RegisterActivity.this, "! Registration Success !", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
                            i.putExtra("pkgUser", username);
                        startActivity(i);
                    } else
                        Toast.makeText(RegisterActivity.this, "! Failed, Try Again !", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(RegisterActivity.this, "! You need to Agree !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createDB() {
        db = openOrCreateDatabase("CoffeeDB", MODE_PRIVATE, null);
        sql = "create table if not exists accounts(user_Id integer PRIMARY KEY AUTOINCREMENT, firstName text, lastName text, age text, mobileNumber text, email text, username text, password text)";
        db.execSQL(sql);
    }

    public boolean verifyUsername(String username) {
        createDB();

        sql = "Select * From accounts where username='" + username + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean AddAccount(String firstname, String lastname, String age, String mobilenumber, String email, String username, String password) {
        createDB();

        sql = "Insert Into accounts(firstName, lastName, age, mobileNumber, email, username, password) Values ('" + firstname + "','" + lastname + "','" + age + "','" + mobilenumber + "','" + email + "','" + username + "','" + password + "')";
        db.execSQL(sql);
        return true;
    }

    @Override
    public void onBackPressed(){
    }
}