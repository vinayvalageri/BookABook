package com.example.vinayakks.stolxnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

public class UpdatePost extends AppCompatActivity {
    protected EditText title;
    protected EditText desc,price;
    protected ImageButton button;
    protected Button b1;
    protected ProgressDialog pd1;
    protected  Bitmap bm;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        bm= (Bitmap) data.getExtras().get("data");
        /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();
        ParseFile file = new ParseFile("image.png", image);
        file.saveInBackground();
        ParseQuery query = ParseQuery.getQuery("Post");
        query.whereEqualTo("title", title.getText().toString().trim());
        ParseObject imgupload = new ParseObject("Post");
        imgupload.put("image", file);
        imgupload.saveInBackground();*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);
        title = (EditText)findViewById(R.id.title);
        desc = (EditText)findViewById(R.id.desc);
        price =(EditText)findViewById(R.id.price);
        button = (ImageButton)findViewById(R.id.imageButton);
       b1 =(Button)findViewById(R.id.pic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePost.this.pd1 = ProgressDialog.show(UpdatePost.this, null, "Uploading...");
                ParseObject PostObject = new ParseObject("Post");
                String tl = title.getText().toString().trim();
                String de = desc.getText().toString().trim();
                String pr = price.getText().toString().trim();
                ParseUser user = ParseUser.getCurrentUser();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] image = stream.toByteArray();
                ParseFile file = new ParseFile("image.png", image);
                file.saveInBackground();
                PostObject.put("title", tl);
                PostObject.put("description", de);
                PostObject.put("price", pr);
                PostObject.put("image", file);
                PostObject.put("user",user);
                PostObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        UpdatePost.this.pd1.dismiss();
                        if (e == null) {
                            Toast.makeText(UpdatePost.this, "Succss in post", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(UpdatePost.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(UpdatePost.this, "error in post", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(p, 0);
            }
        });
    }



}
