package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import java.util.Objects;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    String url="http://milvada.lt/procesas/getdujos.php";

    private static final String TAG="Main2Activity";
//    static{
//        System.loadLibrary("MyOpencvLibs");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Context context =  getApplicationContext();
        final Intent intent2 = new Intent(context, Main7Activity.class);
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH)+1;


        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Filter.setDataType("");
//                String value = gen() + "";
                String date = year + "-" + month;
                String type = "dujos";

                AlertDialog diaBox = AskOption(type);

                copyAsset("classifications.xml");
                copyAsset("images.xml");
                startActivity(intent2);
                Main7Activity.type="dujos";
                Main2Activity.this.finish();

//                if (!Parser.AllSkaitliukai.isEmpty()) {
//                    if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
//                        if (Objects.equals(Parser.AllSkaitliukai.get(1), "Dujos: null")) {
//                            copyAsset("classifications.xml");
//                            copyAsset("images.xml");
//                            startActivity(intent2);
//                            Main7Activity.type="dujos";
//                            Main2Activity.this.finish();
//                        } else {
//                            diaBox.show();
//                        }
//                    } else {
//                        copyAsset("classifications.xml");
//                        copyAsset("images.xml");
//                        startActivity(intent2);
//                        Main7Activity.type="dujos";
//                        Main2Activity.this.finish();
//                    }
//
//                } else {
//                    copyAsset("classifications.xml");
//                    copyAsset("images.xml");
//                    startActivity(intent2);
//                    Main7Activity.type="dujos";
//                    Main2Activity.this.finish();
//
//
//                }



                }

        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filter.setDataType("");
//                String value = gen()+"";
                String date = year+"-"+month;
                String type = "elektra";

                boolean isDateExist=false;
                boolean isTypeExist=false;


                AlertDialog diaBox = AskOption(type);

                if(!Parser.AllSkaitliukai.isEmpty()) {
                    if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
                        if (Objects.equals(Parser.AllSkaitliukai.get(3), "Elektra: null")) {
                            // i SD card issaugo siuos failus
                            copyAsset("classifications.xml");
                            copyAsset("images.xml");
                            ////////////////////////////////////////

                            startActivity(intent2);
                            Main7Activity.type="elektra";
                            Main2Activity.this.finish();
                        }else {
                            diaBox.show();
                        }
                    } else {
                        // i SD card issaugo siuos failus
                        copyAsset("classifications.xml");
                        copyAsset("images.xml");
                        ////////////////////////////////////////

                        startActivity(intent2);
                        Main7Activity.type="elektra";
                        Main2Activity.this.finish();
                    }

                } else {
                    // i SD card issaugo siuos failus
                    copyAsset("classifications.xml");
                    copyAsset("images.xml");
                    ////////////////////////////////////////

                    startActivity(intent2);
                    Main7Activity.type="elektra";
                    Main2Activity.this.finish();
                }

            }

        });

        final Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filter.setDataType("");
//                String value = gen()+"";
                String date = year+"-"+month;
                String type = "vanduo";

                boolean isDateExist=false;
                boolean isTypeExist=false;

                AlertDialog diaBox = AskOption(type);



                if(!Parser.AllSkaitliukai.isEmpty()) {
                    if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
                        if (Objects.equals(Parser.AllSkaitliukai.get(2), "Vanduo: null")) {
                            ////////////////////////////////////////
                            // i SD card issaugo siuos failus
                            copyAsset("classifications.xml");
                            copyAsset("images.xml");
                            ////////////////////////////////////////

                            startActivity(intent2);
                            Main7Activity.type="vanduo";
                            Main2Activity.this.finish();
                        }else {
                            diaBox.show();
                        }
                    } else {
                        ////////////////////////////////////////
                        // i SD card issaugo siuos failus
                        copyAsset("classifications.xml");
                        copyAsset("images.xml");
                        ////////////////////////////////////////

                        startActivity(intent2);
                        Main7Activity.type="vanduo";
                        Main2Activity.this.finish();
                    }

                } else {
                    ////////////////////////////////////////
                    // i SD card issaugo siuos failus
                    copyAsset("classifications.xml");
                    copyAsset("images.xml");
                    ////////////////////////////////////////

                    startActivity(intent2);
                    Main7Activity.type="vanduo";
                    Main2Activity.this.finish();
                }
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
    private AlertDialog AskOption(final String type2)
    {
        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, Main7Activity.class);

        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Update")
                .setMessage("Toks įrašas jau egzistuoja, norite jį pakeisti?")

                .setPositiveButton("Pakeisti", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        ////////////////////////////////////////
                        // i SD card issaugo siuos failus
                        copyAsset("classifications.xml");
                        copyAsset("images.xml");
                        ////////////////////////////////////////

                        startActivity(intent);
                        Main7Activity.type=type2+"2";
                        Main2Activity.this.finish();
                        dialog.dismiss();
                    }

                })



                .setNegativeButton("Atgal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, MainActivity.class);
        Main2Activity.this.finish();
        startActivity(intent);
    }
}
