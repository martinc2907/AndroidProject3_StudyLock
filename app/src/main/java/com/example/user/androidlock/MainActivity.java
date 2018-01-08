package com.example.user.androidlock;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 2018-01-06.
 */

public class MainActivity extends Activity {

    private Button lock_btn;
    private EditText timer;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lock_btn = findViewById(R.id.lock_btn);
        timer = findViewById(R.id.timer_input);

        lock_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text = timer.getText().toString();
                timer.setText("");
                int seconds = Integer.valueOf(text);
                Intent intent = new Intent(MainActivity.this, LockScreenActivity.class);
                intent.putExtra("TIME", seconds);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }
}