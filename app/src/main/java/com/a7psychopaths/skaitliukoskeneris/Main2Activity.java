package com.a7psychopaths.skaitliukoskeneris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    String url="http://milvada.lt/procesas/getdujos.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH)+1;


        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivity(intent);

                    String value = gen() + "";
                    String date = year + "-" + month;
                    String type = "dujos";
                    backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                    Main2Activity.this.finish();


                }

        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivity(intent);

                String value = gen()+"";
                String date = year+"-"+month;
                String type = "elektra";
                backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                Main2Activity.this.finish();
            }
        });

        final Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivity(intent);

                String value = gen()+"";
                String date = year+"-"+month;
                String type = "vanduo";
                backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                Main2Activity.this.finish();
            }
        });



    }

    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
