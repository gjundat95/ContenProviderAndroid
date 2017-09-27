package com.jundat95.contactandroid.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jundat95.contactandroid.R;
import com.jundat95.contactandroid.ui.contact.ContactActivity;
import com.jundat95.contactandroid.ui.music.MusicActivity;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout contact, music, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        contact = (RelativeLayout) findViewById(R.id.contact);
        music = (RelativeLayout) findViewById(R.id.music);
        img = (RelativeLayout) findViewById(R.id.img);


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
    }
}
