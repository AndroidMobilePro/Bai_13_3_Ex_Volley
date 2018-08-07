package com.proteam.bai_13_3_ex_volley;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button btnParseJsonVolley, btnParseJsonGsonVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParseJsonVolley = (Button) findViewById(R.id.btnParseJsonVolley);
        btnParseJsonGsonVolley = (Button) findViewById(R.id.btnParseJsonGsonVolley);


        btnParseJsonVolley.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ParseJsonVolleyActivity.class));
            }
        });

        btnParseJsonGsonVolley.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ParseJsonGsonVolleyActivity.class));
            }
        });

    }
}
