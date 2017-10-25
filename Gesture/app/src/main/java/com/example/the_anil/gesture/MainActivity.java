package com.example.the_anil.gesture;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.gesture.Gesture;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements OnGesturePerformedListener {

    private GestureLibrary gLibrary;
    private TextView mtextView;
    private ImageButton mButton;
    private String action;
    private String holder="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextView = (TextView) findViewById(R.id.textView);
        mButton = (ImageButton) findViewById(R.id.button);

        gLibrary =
                GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLibrary.load()) {
            finish();
        }

        GestureOverlayView gOverlay = (GestureOverlayView) findViewById(R.id.gOverlay);
        gOverlay.addOnGesturePerformedListener(this);

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // mtextView.setText(action.substring(0, action.length()-1));

                     mtextView.setText(holder.substring(0, holder.length()-1));
                    holder = holder.substring(0, holder.length()-1);
                }
            });
        }

        public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
            ArrayList<Prediction> predictions = gLibrary.recognize(gesture);

            if (predictions.size() > 0 && predictions.get(0).score > 1.0) {

                action = predictions.get(0).name;

               // Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
                mtextView.append(action);
                holder += action;
                System.out.println(holder);
            }
        }
    }