package com.techkshetrainfo.watermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar=findViewById(R.id.historytool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("History");
    }
}
