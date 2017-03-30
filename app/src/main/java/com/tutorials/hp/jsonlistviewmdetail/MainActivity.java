package com.tutorials.hp.jsonlistviewmdetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.tutorials.hp.jsonlistviewmdetail.m_JSON.JSONDownloader;

public class MainActivity extends AppCompatActivity {

    String jsonURL="http://webhose.io/search?token=9c55cbb1-2f1c-4700-9c1e-67e685152506&format=json&q=Indian%20Startups%20(Startups%20OR%20Entrepreneur)%20language%3A(english)%20thread.country%3AIN";
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.lv);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONDownloader(MainActivity.this,jsonURL, lv).execute();

            }
        });
    }
}
