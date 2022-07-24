package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    TextView input_name,input_plat;
    Button search_butt,exit_butt;
    public static Activity SearchAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchAct = this;

        input_name = findViewById(R.id.username_search);
        input_plat = findViewById(R.id.platform_search);

        search_butt = findViewById(R.id.button_search_search);
        search_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int k=0;
                Account acc = search(input_name.getText().toString(),input_plat.getText().toString());
                if(acc == null){
                    Toast.makeText(SearchActivity.this, "Account doesn't exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(SearchActivity.this,SeeAnAccountActivity.class);
                    Bundle bund = new Bundle();
                    String name1,pass1,plat1;

                    name1 = "User name: ";
                    pass1 = "Password: ";
                    plat1 = "Platform: ";

                    name1 += acc.getUserName();
                    pass1 += acc.getPassword();
                    plat1 += acc.getPlatform();

                    bund.putString("username",name1);
                    bund.putString("password",pass1);
                    bund.putString("platform",plat1);
                    intent.putExtras(bund);
                    startActivity(intent);
                    finish();
                }
            }
        });

        exit_butt = findViewById(R.id.buttonExit3);
        exit_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    public Account search(String name, String plat){
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