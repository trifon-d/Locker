package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView newpass,confpass;
    Button butt_change,butt_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Bundle bun = getIntent().getExtras();

        newpass = findViewById(R.id.password_change);
        confpass = findViewById(R.id.editTextPassword);

        butt_change = findViewById(R.id.button_change);
        butt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass1,pass2;
                pass1 = newpass.getText().toString();
                pass2 = confpass.getText().toString();

                if(pass1.isEmpty() || pass2.isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!pass1.equals(pass2)){
                    Toast.makeText(ChangePasswordActivity.this, "Password can't be different", Toast.LENGTH_SHORT).show();
                    return;
                }

                int k = search_in_change(bun.getString("name"),bun.getString("plat"));
                if(k != -1){
                    String str = "key"+k;
                    SharedPreferences sp = getSharedPreferences(str,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("password",pass1);
                    editor.apply();
                    Toast.makeText(ChangePasswordActivity.this, "Password has changed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ChangePasswordActivity.this, "Can't find resources", Toast.LENGTH_SHORT).show();
                }
            }
        });

        butt_exit = findViewById(R.id.buttonExit4);
        butt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangePasswordActivity.this,MainActivity.class));
                finish();
            }
        });

    }

    public int search_in_change(String name, String plat){
        if(MainActivity.SIZE == 0){
            return -1;
        }
        int i;
        for(i=1;i<=MainActivity.SIZE;i++){
            String str = "key" + i;
            SharedPreferences sh = getSharedPreferences(str,MODE_PRIVATE);
            if(Objects.equals(sh.getString("username", ""), name) && Objects.equals(sh.getString("platform", ""), plat)){
                return i;
            }
        }

        return -1;
    }
}