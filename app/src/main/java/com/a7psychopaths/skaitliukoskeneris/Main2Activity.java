package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH)+1;
        final String TAG = "main2activity";

        String url = "http://milvada.lt/procesas/getdujos.php";

//        final ListView lv= (ListView) findViewById(R.id.lv);
//        final Downloader d=new Downloader(this,url,lv);
//        d.execute();

        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, MainActivity.class);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivity(intent);
//                    String url="http://milvada.lt/procesas/gettypefilter.php?type=Dujos";
                Filter.setDataType("");
                String value = gen() + "";
                String date = year + "-" + month;
                String type = "dujos";

                AlertDialog diaBox = AskOption(type, date, value);

                if (!Parser.AllSkaitliukai.isEmpty()) {
                    if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
                        if (Objects.equals(Parser.AllSkaitliukai.get(1), "Dujos: null")) {
                            backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                            Main2Activity.this.finish();
                            startActivity(intent);
                        } else {
                            diaBox.show();
                        }
                    } else {
                        backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                        Main2Activity.this.finish();
                        startActivity(intent);
                    }

                } else {
                    backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                    Main2Activity.this.finish();
                    startActivity(intent);


                }
            }
        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivity(intent);
                Filter.setDataType("");
                String value = gen()+"";
                String date = year+"-"+month;
                String type = "elektra";

                boolean isDateExist=false;
                boolean isTypeExist=false;


                AlertDialog diaBox = AskOption(type, date, value);

                if(!Parser.AllSkaitliukai.isEmpty()) {
                        if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
                            if (Objects.equals(Parser.AllSkaitliukai.get(3), "Elektra: null")) {
                                backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                                Main2Activity.this.finish();
                                startActivity(intent);
                            }else {
                                diaBox.show();
                            }
                        } else {
                            backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                            Main2Activity.this.finish();
                            startActivity(intent);
                        }

                } else {
                    backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                    Main2Activity.this.finish();
                    startActivity(intent);
                }

            }
        });

        final Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivity(intent);
                Filter.setDataType("");
                String value = gen()+"";
                String date = year+"-"+month;
                String type = "vanduo";

                boolean isDateExist=false;
                boolean isTypeExist=false;

                AlertDialog diaBox = AskOption(type, date, value);



                if(!Parser.AllSkaitliukai.isEmpty()) {
                    if (Objects.equals(Parser.AllSkaitliukai.get(0), date)) {
                        if (Objects.equals(Parser.AllSkaitliukai.get(2), "Vanduo: null")) {
                            backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                            Main2Activity.this.finish();
                            startActivity(intent);
                        }else {
                            diaBox.show();
                        }
                    } else {
                        backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                        Main2Activity.this.finish();
                        startActivity(intent);
                    }

                } else {
                    backgroundWorker.execute(type, value, date, MainActivity.id(getApplicationContext()));
                    Main2Activity.this.finish();
                    startActivity(intent);
                }
            }
        });



    }

    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    private AlertDialog AskOption(final String type, final String date, final String value)
    {
        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, MainActivity.class);

        final BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Update")
                .setMessage("Toks įrašas jau egzistuoja, norite jį pakeisti?")

                .setPositiveButton("Pakeisti", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        backgroundWorker.execute(type+"2", value, date, MainActivity.id(getApplicationContext()));
                        Main2Activity.this.finish();
                        startActivity(intent);
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
