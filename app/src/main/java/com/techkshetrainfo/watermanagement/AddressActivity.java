package com.techkshetrainfo.watermanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Toolbar toolbar=findViewById(R.id.addr_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Address");
    }

}
