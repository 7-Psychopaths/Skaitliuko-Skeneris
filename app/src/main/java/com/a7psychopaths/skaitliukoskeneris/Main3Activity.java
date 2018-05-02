package com.a7psychopaths.skaitliukoskeneris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final Button button6 = findViewById(R.id.button6);
        final Button button7 = findViewById(R.id.button7);
        final Button button8 = findViewById(R.id.button8);
        final Button button9 = findViewById(R.id.button9);
        final EditText metai = findViewById(R.id.metai);
        final EditText menuo = findViewById(R.id.menuo);


        final Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button6.setVisibility(View.VISIBLE);
                button7.setVisibility(View.VISIBLE);
                button8.setVisibility(View.VISIBLE);
                metai.setVisibility(View.GONE);
                menuo.setVisibility(View.GONE);
                button9.setVisibility(View.GONE);
            }
        });

        final Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button6.setVisibility(View.GONE);
                button7.setVisibility(View.GONE);
                button8.setVisibility(View.GONE);
                metai.setVisibility(View.VISIBLE);
                menuo.setVisibility(View.VISIBLE);
                button9.setVisibility(View.VISIBLE);
            }
        });

        button6.setVisibility(View.GONE);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAactivity4();
            }
        });

        button7.setVisibility(View.GONE);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAactivity4();
            }
        });

        button8.setVisibility(View.GONE);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAactivity4();
            }
        });
        button9.setVisibility(View.GONE);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String var = metai.getText().toString();
                String var2 = menuo.getText().toString();


              /*  if (Integer.parseInt(var2) < 1 || Integer.parseInt(var2) > 12)
                    menuo.setError("Blogai ivesta data");
                else if (var.matches(""))
                {
                    TextUtils
                }*/
                if(TextUtils.isEmpty(var)||TextUtils.isEmpty(var2)) {
                    if (TextUtils.isEmpty(var))
                        metai.setError("Iveskite metus");
                    else if (TextUtils.isEmpty(var2))
                        menuo.setError("Iveskite menesi");

                    return;
                }
                else
                    openAactivity4();
            }
        });


        metai.setVisibility(View.GONE);
        metai.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        menuo.setVisibility(View.GONE);
        menuo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

    }
    public void openAactivity4() {
        Intent intent3 = new Intent(this, Main4Activity.class);
        startActivity(intent3);
    }


}
