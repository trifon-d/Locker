package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd,buttonDelete,buttonSearch,buttonSeeAll,buttonExit;
    public static int SIZE;
    Activity MainAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainAct = this;

        SharedPreferences sizeShPr = getSharedPreferences("Size", MODE_PRIVATE);
        SharedPreferences.Editor size_edit;
        if(!sizeShPr.contains("sizeOfAccounts")){
            size_edit = sizeShPr.edit();
            size_edit.putInt("sizeOfAccounts",0);
            size_edit.apply();
            SIZE = 0;
        }
        else{
            SIZE = sizeShPr.getInt("sizeOfAccounts",0);
        }

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSeeAll = findViewById(R.id.buttonSeeAll);
        buttonExit = findViewById(R.id.buttonExit);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
                finish();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DeleteActivity.class));
                finish();
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
                finish();
            }
        });
        buttonSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SeeAllActivity.class));
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    public static void incSIZE(){
        SIZE++;
    }

    public static void decSIZE() { SIZE --;}
}