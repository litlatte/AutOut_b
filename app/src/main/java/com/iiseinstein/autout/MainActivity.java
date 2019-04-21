package com.iiseinstein.autout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.*;
import android.graphics.Point;


public class MainActivity extends AppCompatActivity {
    ImageView mappa_b;
    ImageView aiuto_b;
    ImageView info_b;
    ImageView impostazioni_b;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;
        mappa_b=(ImageView)findViewById(R.id.Mappa);
        aiuto_b=(ImageView)findViewById(R.id.Aiuto);
        info_b=(ImageView)findViewById(R.id.Info);
        impostazioni_b=(ImageView)findViewById(R.id.Impostazioni);

        mappa_b.getLayoutParams().height =(height/2)-60;
        mappa_b.getLayoutParams().width = width/2;
        mappa_b.setX(0);
        mappa_b.setY(0);

        aiuto_b.getLayoutParams().height =(height/2)-60;
        aiuto_b.getLayoutParams().width = width/2;
        aiuto_b.setX(width/2);
        aiuto_b.setY(-((height/2) -60));

        info_b.getLayoutParams().height =(height/2)-60;
        info_b.getLayoutParams().width = width/2;
        info_b.setX(0);
        info_b.setY(-((height/2)-60));

        impostazioni_b.getLayoutParams().height =(height/2)-60;
        impostazioni_b.getLayoutParams().width = width/2;
        impostazioni_b.setX(width/2);
        impostazioni_b.setY(2*(-((height/2) -60)));

    }

}
