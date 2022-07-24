package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SeeAnAccountActivity extends AppCompatActivity {

    TextView username_text,password_text,platform_text;
    Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_an_account);

        username_text = findViewById(R.id.username_text);
        password_text = findViewById(R.id.password_text);
        platform_text = findViewById(R.id.platform_text);

        Bundle bun = getIntent().getExtras();
        if(bun != null){
            username_text.setText(bun.getString("username"));
            password_text.setText(bun.getString("password"));
            platform_text.setText((bun.getString("platform")));
        }

        buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeeAnAccountActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}