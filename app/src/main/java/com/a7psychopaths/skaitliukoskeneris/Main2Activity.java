package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
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

        final Context context =  getApplicationContext();
        final Intent intent2 = new Intent(context, Main7Activity.class);


        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copyAsset("classifications.xml");
                copyAsset("images.xml");
                startActivity(intent2);
                Main7Activity.type="dujos";
                Main2Activity.this.finish();

                }

        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ////////////////////////////////////////
                // i SD card issaugo siuos failus
                copyAsset("classifications.xml");
                copyAsset("images.xml");
                ////////////////////////////////////////

                startActivity(intent2);
                Main7Activity.type="elektra";
                Main2Activity.this.finish();
            }
        });

        final Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ////////////////////////////////////////
                // i SD card issaugo siuos failus
                copyAsset("classifications.xml");
                copyAsset("images.xml");
                ////////////////////////////////////////

                startActivity(intent2);
                Main7Activity.type="vanduo";
                Main2Activity.this.finish();
            }
        });



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
        } catch (IOException e){
            e.printStackTrace();
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
