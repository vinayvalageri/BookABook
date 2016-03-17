package com.example.vinayakks.stolxnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    protected EditText Usr;
    protected EditText Pass;
    protected EditText Email;
    protected Button Sign;
    protected ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Usr =(EditText)findViewById(R.id.SUser);
        Pass = (EditText)findViewById(R.id.SPass);
        Email = (EditText)findViewById(R.id.SEmail);
        Sign = (Button)findViewById(R.id.SignUpB);

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpActivity.this.pd =  ProgressDialog.show(SignUpActivity.this, null, "SigningIn...");
                String usr = Usr.getText().toString().trim();
                String em = Email.getText().toString().trim();
                String pas = Pass.getText().toString().trim();

                ParseUser user = new ParseUser();
                user.setUsername(usr);
                user.setPassword(pas);
                user.setEmail(em);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        SignUpActivity.this.pd.dismiss();
                        if (e == null) {
                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(SignUpActivity.this, "error in creation", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
