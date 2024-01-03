package com.example.stephen_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    SQLiteDatabase db;
    String sql;
    ArrayList<Accounts> accountsList;
    ListView lv_AccRecords;
    AlertDialog dialog;
    String pkg = "1";
    Button btn_back, btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        lv_AccRecords = (ListView) findViewById(R.id.AccountLIst);
        openDB();
        populateRecord();

        lv_AccRecords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                displayMenu(position);
                return false;
            }
        });

        btn_back = (Button) findViewById(R.id.btnBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DatabaseActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btn_home = (Button) findViewById(R.id.btnHome);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DatabaseActivity.this, HomeActivity.class);
                i.putExtra("pkgId",pkg);
                startActivity(i);
            }
        });
    }

    public void displayMenu(int position) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Action Menu");
        ad.setMessage("Account Details: \n"+"User ID: "+accountsList.get(position).getUser_Id()+"\nFirst Name: "+accountsList.get(position).getFirstName()+"\nLast Name: "+accountsList.get(position).getLastName()
                + "\nAge: "+accountsList.get(position).getAge()+"\nMobile Number: "+accountsList.get(position).getMobileNumber()+"\n"+"Email: "+accountsList.get(position).getEmail()
                +"\nUsername: "+accountsList.get(position).getUsername()+"\n"+"Password: "+accountsList.get(position).getPassword());
        ad.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteRecord(position);
            }
        });

        ad.setNegativeButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DatabaseActivity.this, "For Edit", Toast.LENGTH_SHORT).show();
                modifyRecord(position);
            }
        });

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        ad.show();
    }

    @SuppressLint("MissingInflatedId")
    public void modifyRecord(int position){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Modify Account");
        View v = getLayoutInflater().inflate(R.layout.modify_account, null);
        ad.setView(v);
        EditText et_Fname, et_Lname, et_Age, et_MobileNumber, et_Email, et_Username, et_Password;
        Button btn_save, btn_cancel;
        btn_cancel = (Button)v.findViewById(R.id.btnCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        et_Fname = (EditText)v.findViewById(R.id.edBoxFname);
        et_Lname = (EditText)v.findViewById(R.id.edBoxLname);
        et_Age = (EditText)v.findViewById(R.id.edBoxAge);
        et_MobileNumber = (EditText)v.findViewById(R.id.edBoxmobileNumber);
        et_Email = (EditText)v.findViewById(R.id.edBoxemail);
        et_Username = (EditText)v.findViewById(R.id.edBoxusername);
        et_Password = (EditText)v.findViewById(R.id.edBoxPassword);

        et_Fname.setText(accountsList.get(position).getFirstName());
        et_Lname.setText(accountsList.get(position).getLastName());
        et_Age.setText(accountsList.get(position).getAge());
        et_MobileNumber.setText(accountsList.get(position).getMobileNumber());
        et_Email.setText(accountsList.get(position).getEmail());
        et_Username.setText(accountsList.get(position).getUsername());
        et_Password.setText(accountsList.get(position).getPassword());

        btn_save = (Button)v.findViewById(R.id.btnSave);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDB();
                sql = "update accounts set firstName='"+et_Fname.getText()+"',lastName='"+et_Lname.getText()+"',age='"+et_Age.getText()+"',mobileNumber='"+et_MobileNumber.getText()+"',email='"+et_Email.getText()+"',username='"+et_Username.getText()+"',password='"+et_Password.getText()+"' where user_Id='"+accountsList.get(position).getUser_Id()+"'";
                db.execSQL(sql);
                Toast.makeText(DatabaseActivity.this, "Record Has Been Update", Toast.LENGTH_SHORT).show();
                populateRecord();
                dialog.dismiss();
            }
        });

        dialog = ad.create();
        dialog.show();
    }

    public void deleteRecord(int position){
        openDB();
        sql = "delete from accounts where user_Id = '"+accountsList.get(position).getUser_Id()+"'";
        db.execSQL(sql);
        Toast.makeText(this, "Record Has Been Deleted.", Toast.LENGTH_SHORT).show();
        populateRecord();
    }

    private void openDB() {
        db = openOrCreateDatabase("CoffeeDB", MODE_PRIVATE, null );
        sql = "create table if not exists accounts(user_Id integer PRIMARY KEY AUTOINCREMENT, firstName text, lastName text, age text, mobileNumber text, email text, username text, password text)";
        db.execSQL(sql);
    }

    public void populateRecord() {
        sql = "select * from accounts";
        Cursor c = db.rawQuery(sql, null);
        int user_Id;
        String firstName, lastName, age, mobileNumber, email, username, password;

        ArrayList<String> record_list = new ArrayList<String>();
        accountsList = new ArrayList<>();

        while(c.moveToNext())
        {
            user_Id = Integer.parseInt(c.getString(0));
            firstName = c.getString(1);
            lastName = c.getString(2);
            age = c.getString(3);
            mobileNumber = c.getString(4);
            email = c.getString(5);
            username = c.getString(6);
            password = c.getString(7);
            record_list.add(user_Id+"-"+firstName +","+lastName + ","+age+","+mobileNumber+","+email+","+username+","+password);
            accountsList.add(new Accounts(user_Id,firstName,lastName,age,mobileNumber,email,username,password));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(DatabaseActivity.this, android.R.layout.simple_list_item_1,record_list);
        lv_AccRecords.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
    }
}