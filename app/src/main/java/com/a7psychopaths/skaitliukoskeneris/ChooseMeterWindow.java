package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Objects;

public class ChooseMeterWindow extends AppCompatActivity {

    static{
        System.loadLibrary("MyOpencvLibs");
    }

    @Override
    /*!
      \fn protected void onCreate(Bundle savedInstanceState)
      \pram Bundle savedInstanceState
      \var int year
      \var int month
      \var intent intent2
      \var Calendar c
      \var Context context
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_meter_window);

        final Context context =  getApplicationContext();
        final Intent intent2 = new Intent(context, CameraWindow.class);
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH)+1;

        // save these files to phone storage
        copyAsset("classifications.xml");
        copyAsset("images.xml");

        /*!
        \var final Button dujosButton
        \fn public void OnClickListener(View v)
        \param View v
        */
        final Button dujosButton = findViewById(R.id.button);
        dujosButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Filter.setDataType("");
                /*!
                \var String date
                \var String type
                */
                String date = year + "-" + month;
                String type = "dujos";
                /*!
                \var AlertDialog diaBox
                */
                AlertDialog diaBox = AskOption(type);

                if (!Parser.AllSkaitliukai.isEmpty()) {
                    if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
                        if (Objects.equals(Parser.AllSkaitliukai.get(1), "Dujos: null")) {
                            startActivity(intent2);
                            CameraWindow.type="dujos";
                            ChooseMeterWindow.this.finish();
                        } else {
                            diaBox.show();
                        }
                    } else {
                        startActivity(intent2);
                        CameraWindow.type="dujos";
                        ChooseMeterWindow.this.finish();
                    }

                } else {
                    startActivity(intent2);
                    CameraWindow.type="dujos";
                    ChooseMeterWindow.this.finish();


                }



                }

        });
          /*!
          \var final Button elektraButton
          */
        final Button elektraButton = findViewById(R.id.button2);
        elektraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filter.setDataType("");
                /*!
                \var String date
                \var String type
                */
                String date = year+"-"+month;
                String type = "elektra";
                /*!
                \var AlertDialog diaBox
                */
                AlertDialog diaBox = AskOption(type);

                if(!Parser.AllSkaitliukai.isEmpty()) {
                    if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
                        if (Objects.equals(Parser.AllSkaitliukai.get(3), "Elektra: null")) {

                            startActivity(intent2);
                            CameraWindow.type="elektra";
                            ChooseMeterWindow.this.finish();
                        }else {
                            diaBox.show();
                        }
                    } else {

                        startActivity(intent2);
                        CameraWindow.type="elektra";
                        ChooseMeterWindow.this.finish();
                    }

                } else {

                    startActivity(intent2);
                    CameraWindow.type="elektra";
                    ChooseMeterWindow.this.finish();
                }

            }

        });
        /*!
        \var final Button vanduoButton
        */
        final Button vanduoButton = findViewById(R.id.button3);
        vanduoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filter.setDataType("");
                /*!
                \var String date
                \var String type
                */
                String date = year+"-"+month;
                String type = "vanduo";
                /*!
                \var AlertDialog diaBox
                */
                AlertDialog diaBox = AskOption(type);

                if(!Parser.AllSkaitliukai.isEmpty()) {
                    if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
                        if (Objects.equals(Parser.AllSkaitliukai.get(2), "Vanduo: null")) {

                            startActivity(intent2);
                            CameraWindow.type="vanduo";
                            ChooseMeterWindow.this.finish();
                        }else {
                            diaBox.show();
                        }
                    } else {
                        startActivity(intent2);
                        CameraWindow.type="vanduo";
                        ChooseMeterWindow.this.finish();
                    }

                } else {

                    startActivity(intent2);
                    CameraWindow.type="vanduo";
                    ChooseMeterWindow.this.finish();
                }
            }
        });



    }


    // copyAsset, copyFile methods are used to save files into phone storage
    /*!
    \fn private  void copyAsset(String filename)
    \param String filename
    \var String dirPath
    \var File dir
    */
    private  void copyAsset(String filename){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SkaitliukoSkeneris";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        /*!
        \var InputStream in
        \var OutputStream out
        */
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try{
          /*!
          \var File outFile
          */
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
    /*!
    \fn private void copyFile(InputStream in, OutputStream out)
    \param InputStream in
    \param OutputStream out
    \var byte[] buffer
    \var int read
    */
    private void copyFile(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
    //////////////////////////////////////////////////////////
    /*!
    \fn private AlertDialog AskOption(final String type2)
    \param final String type2
    \var final Context context
    \var final Intent intent
    */
    private AlertDialog AskOption(final String type2)
    {
        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, CameraWindow.class);

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Update")
                .setMessage("Toks įrašas jau egzistuoja, norite jį pakeisti?")

                .setPositiveButton("Pakeisti", new DialogInterface.OnClickListener() {
                  /*!
                  \fn public void onClick(DialogInterface dialog, int whichButton)
                  \param DialogInterface dialog
                  \param int whichButton
                  \var final Context context
                  \var final Intent intent
                  */
                    public void onClick(DialogInterface dialog, int whichButton) {
                        startActivity(intent);
                        CameraWindow.type=type2+"2";
                        ChooseMeterWindow.this.finish();
                        dialog.dismiss();
                    }

                })

                .setNegativeButton("Atgal", new DialogInterface.OnClickListener() {
                  /*!
                  \fn public void onClick(DialogInterface dialog, int whichButton)
                  \param DialogInterface dialog
                  \param int which
                  */
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    @Override
    /*!
    \fn public void onBackPressed()
    \var final Context context
    \var final Intent intent
    */
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, MainWindow.class);
        ChooseMeterWindow.this.finish();
        startActivity(intent);
    }
}
