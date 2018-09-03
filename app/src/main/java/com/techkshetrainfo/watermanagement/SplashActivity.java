package com.techkshetrainfo.watermanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int i=0;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar=findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork(){
        for(int progress=50; progress<100; progress++){
            try {
                Thread.sleep(100);
                progressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void startApp(){
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
    }
}
