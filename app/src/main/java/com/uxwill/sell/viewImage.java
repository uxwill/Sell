package com.uxwill.sell;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class viewImage extends AppCompatActivity implements View.OnClickListener  {

    Button sellButton;
    Button saveButton;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        sellButton = (Button) findViewById(R.id.sellButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        img = (ImageView)findViewById(R.id.img);

        sellButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        Intent data = new Intent("com.google.zxing.client.android.SCAN");
        data.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(data, 0);

    }

    @Override
    public void onClick(View v) {

        int sellCount = 0;
        int saveCount = 0;
        Intent intent = new Intent();

        if (v == sellButton) {

            intent.putExtra("type", 1);
            setResult(RESULT_OK, intent);
            finish();
        } else if (v == saveButton){

            intent.putExtra("type", 2);
            setResult(RESULT_OK, intent);

            finish();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                String contents = data.getStringExtra("SCAN_RESULT");
                String format = data.getStringExtra("SCAN_RESULT_FORMAT");

                Picasso.with(this).load(contents).into(img);


            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }
}
