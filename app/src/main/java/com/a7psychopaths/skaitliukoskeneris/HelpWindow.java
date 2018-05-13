package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HelpWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_window);
    }

    @Override
    public void onBackPressed() {
        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, MainWindow.class);
        HelpWindow.this.finish();
        startActivity(intent);
    }

}
