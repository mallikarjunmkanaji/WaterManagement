package com.techkshetrainfo.watermanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Change_PassActivity extends AppCompatActivity {
    private Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__pass);

        Toolbar my_toolbar=findViewById(R.id.change_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Set New Password");
        my_toolbar.setNavigationIcon(R.drawable.ic_tool_back);
        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        savebtn=findViewById(R.id.save_pas_id);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Password Changed Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Change_PassActivity.this,LoginActivity.class));
            }
        });
    }
}
