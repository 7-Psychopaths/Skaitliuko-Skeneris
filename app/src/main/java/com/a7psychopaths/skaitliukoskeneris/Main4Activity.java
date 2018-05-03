package com.a7psychopaths.skaitliukoskeneris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class Main4Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,Filter.getUrl(),lv);
        d.execute();
    }

}
