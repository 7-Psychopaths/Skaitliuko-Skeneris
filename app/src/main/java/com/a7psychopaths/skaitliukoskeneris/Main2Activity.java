package com.a7psychopaths.skaitliukoskeneris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    String url="http://milvada.lt/procesas/getdujos.php";

    private static final String TAG="Main2Activity";
    static{
        System.loadLibrary("MyOpencvLibs");
    }

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

                    Mat mRgba = new Mat(100, 100, CvType.CV_8UC4);
                    backgroundWorker.execute(type, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())), date, MainActivity.id(getApplicationContext()));
                    Main2Activity.this.finish();

                Log.d(TAG, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())));
                mRgba.release();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


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
                Mat mRgba = new Mat(100, 100, CvType.CV_8UC4);
                backgroundWorker.execute(type, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())), date, MainActivity.id(getApplicationContext()));
                Main2Activity.this.finish();

                Log.d(TAG, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())));
                mRgba.release();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
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
                Mat mRgba = new Mat(100, 100, CvType.CV_8UC4);
                backgroundWorker.execute(type, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())), date, MainActivity.id(getApplicationContext()));
                Main2Activity.this.finish();

                Log.d(TAG, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())));
                mRgba.release();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
