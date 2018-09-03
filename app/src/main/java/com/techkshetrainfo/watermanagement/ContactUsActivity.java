package com.techkshetrainfo.watermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar my_toolbar=findViewById(R.id.cnct_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Contact Us");
        my_toolbar.setNavigationIcon(R.drawable.ic_tool_back);
        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}
