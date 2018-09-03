package com.techkshetrainfo.watermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ProfileSettingsActivity extends AppCompatActivity {

    private TextView addressedit,personeltv,deliverytv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        Toolbar my_toolbar=findViewById(R.id.prof_tool);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Profile");
        my_toolbar.setNavigationIcon(R.drawable.ic_tool_back);
        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
                finish();
            }
        });

        personeltv=findViewById(R.id.personel_edit_id);
        deliverytv=findViewById(R.id.delivery_edit_id);
        personeltv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PersonalDetailActivity.class));
            }
        });
        deliverytv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DeliveryDetailsActivity.class));
            }
        });

    }
}
