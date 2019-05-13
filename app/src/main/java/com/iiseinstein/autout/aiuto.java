package com.iiseinstein.autout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
public class aiuto extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiuto);
        //String ArrayCreat
        /*LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
        TextView[] tx = new TextView[10];
        for(int i=0; i<10; i++){
            final TextView rowTextView = new TextView(this);

            // set some properties of rowTextView or something
            rowTextView.setText("This is row #" + i);

            // add the textview to the linearlayout
            layout.addView(rowTextView);

            // save a reference to the textview for later
            tx[i] = rowTextView;
        }*/
    }
    private View.OnClickListener mThisButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(aiuto.this, "Hello !",
                    Toast.LENGTH_LONG).show();
        }
    };
}
