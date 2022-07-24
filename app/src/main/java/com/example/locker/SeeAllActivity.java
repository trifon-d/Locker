package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.Objects;

public class SeeAllActivity extends AppCompatActivity {

    TextView printText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        printText = findViewById(R.id.scrollText);

        SpannableStringBuilder main_text = new SpannableStringBuilder();

        if(MainActivity.SIZE == 0){
            SpannableString str1 = new SpannableString("Nothing Saved");
            str1.setSpan(new ForegroundColorSpan(Color.BLUE),0,str1.length(),0);
            main_text.append(str1);
        }
        else{
            for(int i=1;i<=MainActivity.SIZE;i++){
                if(i<10) {
                    String str = "key" + i, usr = ". Username : ", plt = "     Platform    : ";
                    String num = "";
                    num += i;
                    SharedPreferences sh = getSharedPreferences(str, MODE_PRIVATE);
                    SpannableString strNum = new SpannableString(num), strUsr = new SpannableString(usr), strPlt = new SpannableString(plt);
                    strNum.setSpan(new ForegroundColorSpan(Color.CYAN), 0, strNum.length(), 0);
                    strUsr.setSpan(new ForegroundColorSpan(Color.CYAN), 0, strUsr.length(), 0);
                    strPlt.setSpan(new ForegroundColorSpan(Color.CYAN), 0, strPlt.length(), 0);

                    main_text.append(" ").append(strNum).append(strUsr).append(sh.getString("username", "")).append("\n");
                    main_text.append(strPlt).append(sh.getString("platform", "")).append("\n\n");
                }
                else{
                    String str = "key" + i, usr = ".Username : ", plt = "      Platform    : ";
                    String num = "";
                    num += i;
                    SharedPreferences sh = getSharedPreferences(str, MODE_PRIVATE);
                    SpannableString strNum = new SpannableString(num), strUsr = new SpannableString(usr), strPlt = new SpannableString(plt);
                    strNum.setSpan(new ForegroundColorSpan(Color.CYAN), 0, strNum.length(), 0);
                    strUsr.setSpan(new ForegroundColorSpan(Color.CYAN), 0, strUsr.length(), 0);
                    strPlt.setSpan(new ForegroundColorSpan(Color.CYAN), 0, strPlt.length(), 0);

                    main_text.append(strNum).append(strUsr).append(sh.getString("username", "")).append("\n");
                    main_text.append(strPlt).append(sh.getString("platform", "")).append("\n\n");
                }
            }
        }

        printText.setText(main_text);
    }
}