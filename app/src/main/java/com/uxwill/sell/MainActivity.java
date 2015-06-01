package com.uxwill.sell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button scanButton;
    EditText levelText;
    EditText sellText;
    EditText saveText;

    private ImageView tv;
    private TextView t;
    private String url = "";

    int counter = 5;
    int saveCount = 0;
    int sellCount = 0;

    public static final int REQUEST_SCAN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scanButton = (Button) findViewById(R.id.scanButton);
        levelText = (EditText) findViewById(R.id.levelCount);
        sellText = (EditText) findViewById(R.id.SellCountText);
        saveText = (EditText) findViewById(R.id.SaveCountText);


        int sellCounter = getIntent().getIntExtra("sellCount", 0);
        sellText.setText(Integer.toString(sellCounter));

        int saveCounter = getIntent().getIntExtra("saveCount", 0);
        saveText.setText(Integer.toString(saveCounter));

        scanButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == scanButton) {
            System.out.println("horray");
            //counter++;
            levelText.setText(Integer.toString(counter));
            Intent intent = new Intent (this, viewImage.class);
            startActivityForResult(intent, REQUEST_SCAN);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == REQUEST_SCAN)
            {
                int type = data.getIntExtra("type", 0);
                switch (type)
                {
                    case 1: // sell
                    {
                        sellCount += 1;
                        break;
                    }
                    case 2: // save
                    {
                        saveCount += 1;
                        break;
                    }
                }
                counter -= 1;

                sellText.setText(""+sellCount);
                saveText.setText(""+saveCount);
                levelText.setText(""+counter);
            }
        }
    }

}
