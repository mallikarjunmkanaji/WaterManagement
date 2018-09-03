package com.techkshetrainfo.watermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button loginbtn;
    private TextView regbtn;
    private TextView frgtv;
    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      /*  Toolbar my_toolbar=findViewById(R.id.log_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle(" "); */
        loginbtn=findViewById(R.id.logbtn);
        regbtn=findViewById(R.id.logregbtn);
        frgtv=findViewById(R.id.fglogtv);
        username=findViewById(R.id.username_edt);
        password=findViewById(R.id.logpswrd);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginuserwithvalidate();
            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        frgtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotActivity.class));
            }
        });
    }

    private void loginuserwithvalidate() {

        if (username.getText().length()==0){
            username.setError(getString(R.string.username_error));
        } else if (password.getText().length()==0){
            password.setError("Enter Password first");
        }  else {
            Toast.makeText(getApplicationContext(),"Log in successful!!",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
        }
    }
}
