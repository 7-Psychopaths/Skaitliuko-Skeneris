package com.a7psychopaths.skaitliukoskeneris;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

        ////////////////////////////////////////
        // i SD card issaugo siuos failus
        copyAsset("classifications.xml");
        copyAsset("images.xml");
        ////////////////////////////////////////


        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivity(intent);

                    String value = gen() + "";
                    String date = year + "-" + month;
                    String type = "dujos";

                ////////////////////////////////////////
                    copyAsset("testt.png");
                // Sie kintamieji naudojami C++
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SkaitliukoSkeneris";
                    Mat mRgba = new Mat(100, 100, CvType.CV_8UC4);
                ////////////////////////////////////////

                    // backgroundWorker.execute(type, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())), date, MainActivity.id(getApplicationContext()));
                    Main2Activity.this.finish();

                Log.d(TAG, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr(), path)));
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

                ////////////////////////////////////////
                copyAsset("testt.png");
                // Sie kintamieji naudojami C++
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SkaitliukoSkeneris";
                Mat mRgba = new Mat(100, 100, CvType.CV_8UC4);
                ////////////////////////////////////////

                //backgroundWorker.execute(type, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())), date, MainActivity.id(getApplicationContext()));
                Main2Activity.this.finish();

                Log.d(TAG, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr(), path)));
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

                ////////////////////////////////////////
                copyAsset("testt.png");
                // Sie kintamieji naudojami C++
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SkaitliukoSkeneris";
                Mat mRgba = new Mat(100, 100, CvType.CV_8UC4);
                ////////////////////////////////////////

                //backgroundWorker.execute(type, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr())), date, MainActivity.id(getApplicationContext()));
                Main2Activity.this.finish();

                Log.d(TAG, String.valueOf(Recognition.getDigits( mRgba.getNativeObjAddr(), path)));
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

    ////////////////////////////////////////////////////
    // tiek copyAsset, tiek copyFile metodai naudojami tam, kad issaugotu i SD card

    private  void copyAsset(String filename){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SkaitliukoSkeneris";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try{
            in = assetManager.open(filename);
            File outFile = new File(dirPath, filename);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(in != null){
                try{
                    in.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(out != null){
                try{
                    out.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
    //////////////////////////////////////////////////////////

}
