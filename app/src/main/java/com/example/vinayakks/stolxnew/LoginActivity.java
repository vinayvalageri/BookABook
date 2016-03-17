package com.example.vinayakks.stolxnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    protected EditText usrname;
    protected EditText pass;
    protected Button mlogin;
    protected Button mSign;
    protected  ProgressDialog pdiag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usrname = (EditText)findViewById(R.id.usr);
        pass = (EditText)findViewById(R.id.pass);
        mlogin = (Button)findViewById(R.id.lb);
        mSign = (Button)findViewById(R.id.sb);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.pdiag =  ProgressDialog.show(LoginActivity.this, null, "LoggingIn...");
                String us = usrname.getText().toString().trim();
                String pa = pass.getText().toString().trim();
                ParseUser.logInInBackground(us, pa, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        LoginActivity.this.pdiag.dismiss();
                        if(e == null){
                            Toast.makeText(LoginActivity.this, " login succes", Toast.LENGTH_LONG).show();
                            Intent j;
                            j = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(j);
                            finish();

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "error in login", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
        mSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });

    }
    public void onDestroy()
    {
        super.onDestroy();

    }



}
