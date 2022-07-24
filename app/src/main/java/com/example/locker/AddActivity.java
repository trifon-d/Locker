package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class AddActivity extends AppCompatActivity {

    TextView username,password,platform;
    Button buttonSave,buttonExit2;
    SharedPreferences sh;
    Activity AddAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        AddAct = this;

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account acc = new Account(username.getText().toString(),password.getText().toString(),platform.getText().toString());
                if(acc.getUserName().isEmpty() || acc.getPassword().isEmpty() || acc.getPlatform().isEmpty()){
                    Toast.makeText(AddActivity.this,"Fields can't be empty!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Add(acc);
            }
        });
        buttonExit2 = findViewById(R.id.buttonExit2);
        buttonExit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this,MainActivity.class));
                finish();
            }
        });

        username = findViewById(R.id.username);
        username.setMaxLines(1);
        username.setMaxWidth(username.getWidth());
        password = findViewById(R.id.password);
        password.setMaxLines(1);
        password.setMaxWidth(password.getMaxWidth());
        platform = findViewById(R.id.platform);
        platform.setMaxLines(1);
        platform.setMaxWidth(platform.getMaxWidth());
    }

    public void Add(Account ac){
        Account temp = search_in_add(ac.getUserName(),ac.getPlatform());
        if(temp != null){
            if(Objects.equals(ac.getPassword(), temp.getPassword())) {
                Toast.makeText(AddAct, "Already Exists!", Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddAct,2);
                builder1.setMessage("These elements exist but with a different password.Are you sure want to change it?");
                builder1.setTitle("Change Password?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(AddActivity.this, ChangePasswordActivity.class);
                                Bundle bun = new Bundle();
                                String name,plat;

                                name = ac.getUserName();
                                plat = ac.getPlatform();

                                bun.putString("name",name);
                                bun.putString("plat",plat);
                                intent.putExtras(bun);
                                startActivity(intent);
                                finish();

                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
            return;
        }

        String str = "key" + (MainActivity.SIZE+1);

        SharedPreferences addpref = getSharedPreferences(str,MODE_PRIVATE);
        SharedPreferences.Editor edit = addpref.edit();
        edit.putString("username",ac.getUserName());
        edit.putString("password",ac.getPassword());
        edit.putString("platform",ac.getPlatform());
        edit.apply();

        MainActivity.incSIZE();

        SharedPreferences sp = getSharedPreferences("Size",MODE_PRIVATE);
        SharedPreferences.Editor ditor = sp.edit();
        ditor.putInt("sizeOfAccounts",MainActivity.SIZE);
        ditor.apply();

        Toast.makeText(AddActivity.this,"Saved Successfully!",Toast.LENGTH_SHORT).show();
    }

    public Account search_in_add(String name, String plat){
        if(MainActivity.SIZE == 0){
            return null;
        }
        int i;
        for(i=1;i<=MainActivity.SIZE;i++){
            String str = "key" + i;
            SharedPreferences sh = getSharedPreferences(str,MODE_PRIVATE);
            if(Objects.equals(sh.getString("username", ""), name) && Objects.equals(sh.getString("platform", ""), plat)){
                return new Account(name,sh.getString("password",""),plat);
            }
        }

        return null;
    }
}