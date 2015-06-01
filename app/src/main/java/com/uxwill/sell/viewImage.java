package com.uxwill.sell;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class viewImage extends AppCompatActivity implements View.OnClickListener  {

    Button sellButton;
    Button saveButton;
    ImageView img;
    TextView valText;

  //  String url2 = getString(R.string.image2);
    private static final String TAG = "willMessage";
    int value2 = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        sellButton = (Button) findViewById(R.id.sellButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        img = (ImageView)findViewById(R.id.img);

        valText = (TextView) findViewById(R.id.valueText);

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
            intent.putExtra("sellValue", value2);
            setResult(RESULT_OK, intent);
            finish();
        } else if (v == saveButton){

            intent.putExtra("type", 2);
            intent.putExtra("image1", "http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2011/12/14/1323902537523/Samburu-tribal-people-of--013.jpg");
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

                if(contents.equals("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2011/12/14/1323902537523/Samburu-tribal-people-of--013.jpg")){
                    Log.d(TAG, "gotit");

                    valText.setText(Integer.toString(value2));

                }


            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }
}
