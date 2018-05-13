package com.a7psychopaths.skaitliukoskeneris;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FilterTypeWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_type_window);

        final Button button6 = findViewById(R.id.button6);
        final Button button7 = findViewById(R.id.button7);
        final Button button8 = findViewById(R.id.button8);
        final Button button9 = findViewById(R.id.button9);

        final Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button6.setVisibility(View.VISIBLE);
                button7.setVisibility(View.VISIBLE);
                button8.setVisibility(View.VISIBLE);
                button9.setVisibility(View.VISIBLE);
            }
        });

        button6.setVisibility(View.GONE);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filter.setDataType("Dujos");
                openAactivity4();
                FilterTypeWindow.this.finish();
            }
        });

        button7.setVisibility(View.GONE);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filter.setDataType("Vanduo");
                openAactivity4();
                FilterTypeWindow.this.finish();
            }
        });

        button8.setVisibility(View.GONE);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filter.setDataType("Elektra");
                openAactivity4();
                FilterTypeWindow.this.finish();
            }
        });

        button9.setVisibility(View.GONE);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Filter.setDataType("");
                openAactivity4();
                FilterTypeWindow.this.finish();
            }
        });

    }
    public void openAactivity4() {
        Intent intent3 = new Intent(this, MainWindow.class);
        startActivity(intent3);
    }

    @Override
    public void onBackPressed() {
        final Context context =  getApplicationContext();
        final Intent intent = new Intent(context, MainWindow.class);
        FilterTypeWindow.this.finish();
        startActivity(intent);
    }

}
