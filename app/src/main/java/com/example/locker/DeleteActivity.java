package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class DeleteActivity extends AppCompatActivity {

    Button butt_exit,butt_delete;
    TextView in_name,in_pass,in_plat;
    Activity DelAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        DelAct = this;

        in_name = findViewById(R.id.username_delete);
        in_pass = findViewById(R.id.password_delete);
        in_plat = findViewById(R.id.platform_delete);

        butt_delete = findViewById(R.id.buttonDelete);
        butt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account acca = new Account(in_name.getText().toString(),in_pass.getText().toString(),in_plat.getText().toString());
                if(acca.getUserName().isEmpty() || acca.getPassword().isEmpty() || acca.getPlatform().isEmpty()){
                    Toast.makeText(DeleteActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                    return;
                }

                int k = search_in_delete(acca.getUserName(),acca.getPlatform(),acca.getPassword());
                if(k == -1){
                    Toast.makeText(DelAct, "This account doesn't exists.", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder2 = new AlertDialog.Builder(DelAct,2);
                builder2.setMessage("Are you sure you want to delete this account?");
                builder2.setTitle("Delete Account?");
                builder2.setCancelable(true);

                builder2.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Delete(acca);
                                dialog.cancel();
                            }
                        });

                builder2.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog ask = builder2.create();
                ask.show();
            }
        });

        butt_exit = findViewById(R.id.buttonExit_delete);
        butt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    private void Delete(Account ac){
        int k = search_in_delete(ac.getUserName(),ac.getPlatform(),ac.getPassword());

        if(k == -1){
            Toast.makeText(this, "This account doesn't exists.", Toast.LENGTH_SHORT).show();
            return;
        }

        String val1 = "key" + k, val2 = "key" + MainActivity.SIZE;
        SharedPreferences sp1 = getSharedPreferences(val1,MODE_PRIVATE),sp2 = getSharedPreferences(val2,MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp1.edit();

        if(Objects.equals(sp1.getString("username", ""), ac.getUserName())
                && Objects.equals(sp1.getString("password", ""), ac.getPassword())
                && Objects.equals(sp1.getString("platform", ""), ac.getPlatform())) {

            editor1.putString("username", sp2.getString("username", ""));
            editor1.putString("password", sp2.getString("password", ""));
            editor1.putString("platform", sp2.getString("platform", ""));
            editor1.apply();

            sp2.edit().clear().apply();
            MainActivity.decSIZE();

            SharedPreferences sp = getSharedPreferences("Size", MODE_PRIVATE);
            SharedPreferences.Editor ditor = sp.edit();
            ditor.putInt("sizeOfAccounts", MainActivity.SIZE);
            ditor.apply();

            Toast.makeText(this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(DelAct, "This account doesn't exists.", Toast.LENGTH_SHORT).show();
        }
    }

    private int search_in_delete(String name, String plat,String pass){
        if(MainActivity.SIZE == 0){
            return -1;
        }
        int i;
        for(i=1;i<=MainActivity.SIZE;i++){
            String str = "key" + i;
            SharedPreferences sh = getSharedPreferences(str,MODE_PRIVATE);
            if(Objects.equals(sh.getString("username", ""), name)
                    && Objects.equals(sh.getString("platform", ""), plat)
                    && Objects.equals(sh.getString("password", ""), pass)){
                return i;
            }
        }

        return -1;
    }
}