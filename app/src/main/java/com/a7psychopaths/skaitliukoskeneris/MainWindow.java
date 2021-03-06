package com.a7psychopaths.skaitliukoskeneris;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import org.opencv.android.OpenCVLoader;

import java.util.UUID;

public class MainWindow extends AppCompatActivity {

    static String url = "http://milvada.lt/procesas/getdujos.php";
    public static boolean back = false;

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    private static final String TAG="MainWindow";
    static{
        if(OpenCVLoader.initDebug()){
            Log.d(TAG, "Succesful connection to OpenCV");
        }
        else{
            Log.d(TAG, "Unsuccesful connection to OpenCV");
        }
    }

    public static synchronized String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        return uniqueID;
    }

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);
        d.execute();

        BottomNavigationView bottomNavigationView =  (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent intent = new Intent(getApplicationContext(), ChooseMeterWindow.class);
                        startActivity(intent);
                        MainWindow.this.finish();
                        break;
                    case R.id.action_search:
                        Intent intent2 = new Intent(getApplicationContext(), FilterTypeWindow.class);
                        startActivity(intent2);
                        MainWindow.this.finish();
                        break;
                    case R.id.action_help:
                        Intent intent3 = new Intent(getApplicationContext(), HelpWindow.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });
    }

}
