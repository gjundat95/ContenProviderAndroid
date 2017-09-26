package com.jundat95.contactandroid.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jundat95.contactandroid.R;
import com.jundat95.contactandroid.ui.contact.ContactActivity;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout contact, mp3, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        contact = (RelativeLayout) findViewById(R.id.contact);
        mp3 = (RelativeLayout) findViewById(R.id.mp3);
        img = (RelativeLayout) findViewById(R.id.img);


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}
