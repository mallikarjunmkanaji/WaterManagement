package com.techkshetrainfo.watermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button regbtn;
    private TextView reglogbtn;
    private EditText username,email,password,conpassword;
   // private EditText et_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar my_toolbar=findViewById(R.id.regtool_id);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Register");
        my_toolbar.setNavigationIcon(R.drawable.ic_tool_back);
        my_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

      //  et_address = (EditText) findViewById(R.id.et_address);
        regbtn=findViewById(R.id.regbtn);
        reglogbtn=findViewById(R.id.reglogbtn);
        username=findViewById(R.id.user_id);
        email=findViewById(R.id.mail_id);
        password=findViewById(R.id.pswd_id);
        conpassword=findViewById(R.id.conpswd_id);
        reglogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruservalidate();
            }
        });

    }

    private void registeruservalidate() {
        if (username.getText().length()==0){
            username.setError(getString(R.string.username_error));
        }
        else if (email.getText().length() == 0) {
            email.setError(getString(R.string.email_error));
        }
        else if (email.getText().length() == 0 || !email.getText().toString().trim().matches(getString(R.string.emailpattern))) {
            email.setError(getString(R.string.email_error));
        }
        else if (password.getText().toString().length()==0|| password.getText().toString().length() < 6) {
            password.setError("Password cannot be less than 6 characters!");
        }
        else if (conpassword.getText().toString().length()==0){
            conpassword.setError("Please Confirm password !");
        }
        else if (!password.getText().toString().equals(conpassword.getText().toString())) {

            conpassword.setError(getString(R.string.confirm_password_error));
        }
        else {
            Toast.makeText(getApplicationContext(),"Registration successful!!",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),ProfileSettingsActivity.class));
        }
    }

}
