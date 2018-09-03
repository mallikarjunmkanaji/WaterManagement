package com.techkshetrainfo.watermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    private Button otpsentbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        Toolbar my_toolbar=findViewById(R.id.frg_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Forgot Password");
        my_toolbar.setNavigationIcon(R.drawable.ic_tool_back);
        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        otpsentbtn=findViewById(R.id.fgsend);

        otpsentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Verification code sent to registered mail!! Please check ur mail", Toast.LENGTH_LONG).show();
                final Intent intent = new Intent(ForgotActivity.this, VerificationActivity.class);

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000); // As I am using LENGTH_LONG in Toast
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });
    }
}
