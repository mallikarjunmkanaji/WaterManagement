package com.techkshetrainfo.watermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class PersonalDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);

        Toolbar toolbar=findViewById(R.id.personeltool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Personel details");
    }
}
