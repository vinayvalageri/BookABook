package com.example.vinayakks.stolxnew;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by vinayak ks on 2/5/2016.
 */
public class CustomAdapter extends ArrayAdapter<ParseObject> {
    protected  ParseObject i;
    protected Context context;
    protected String em;
    public CustomAdapter(Context context, List<ParseObject> list) {
        super(context,R.layout.row,list);
        this.context=context;
    }
    @Override
    public View getView(int paramInt, final View paramView, ViewGroup paramViewGroup){
        View row = paramView;
        LayoutInflater vininflate = LayoutInflater.from(getContext());
        final View custom = vininflate.inflate(R.layout.row, paramViewGroup, false);
        TextView vintext = (TextView)custom.findViewById(R.id.text);
        /*TextView price = (TextView)custom.findViewById(R.id.text2);
        TextView desc = (TextView)custom.findViewById(R.id.text1);*/
        final ImageView vinimage = (ImageView)custom.findViewById(R.id.image);
         i = (ParseObject)getItem(paramInt);

        ParseFile fileobj = (ParseFile)i.get("image");
        fileobj.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                if (e == null) {
                    Bitmap bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    vinimage.setImageBitmap(bit);
                } else {
                    Log.v("byte", "Error: " + e.getMessage());
                }
            }
        });


        vintext.setText(i.getString("title"));
        //em=i.getParseObject("user").getString("email");
custom.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(custom.getContext());
        builder.setTitle("Do you want buy");
        builder.setMessage("Desc:" + i.getString("description") + "\nPrice:" + i.getString("price"));
        builder.setCancelable(false);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(custom.getContext(), "ok||", Toast.LENGTH_LONG).show();
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse(i.getParseObject("user").getString("email")));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{i.getParseObject("user").getString("email")});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Intrested to buy");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This message is from user with above mail interested in buying your product :-)");
                context.startActivity(sendIntent);

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
});
       // vinimage.setImageResource(R.drawable.a);
        return  custom;

    }

}
