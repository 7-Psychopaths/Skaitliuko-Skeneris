package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    @Override
    public void onBackPressed() {
        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, MainActivity.class);
        Main5Activity.this.finish();
        startActivity(intent);
    }

}
