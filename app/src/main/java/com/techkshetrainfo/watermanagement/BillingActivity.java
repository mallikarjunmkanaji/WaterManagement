package com.techkshetrainfo.watermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class BillingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        Toolbar my_toolbar=findViewById(R.id.billtool);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Billing info");
    }
}
