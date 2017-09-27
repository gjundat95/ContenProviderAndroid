package com.jundat95.contactandroid.ui.music;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.jundat95.contactandroid.R;
import com.jundat95.contactandroid.repository.local.model.MusicModel;
import com.jundat95.contactandroid.shared.util.BlurBuilder;

import java.util.ArrayList;
import java.util.List;


public class MusicActivity extends AppCompatActivity {

    private final Uri uriMediaStore = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    private final String[] projection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
    };
    private final String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
    private final String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

    private ContentResolver contentResolver;

    private List<MusicModel> musicModels = new ArrayList<>();

    private RecyclerView rcvMusic;
    private RelativeLayout layout;
    private RelativeLayout description, play;
    private MusicAdapter musicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        init();
        setBackground();
        //setStatusBarTranslucent(true);
    }

    private void init() {
        contentResolver = this.getContentResolver();
        rcvMusic = (RecyclerView) findViewById(R.id.rcvMusic);
        layout = (RelativeLayout) findViewById(R.id.layout_music);
        description = (RelativeLayout) findViewById(R.id.description);
        play = (RelativeLayout) findViewById(R.id.layout_play);

        getAllMusic();

        musicAdapter = new MusicAdapter(this, musicModels);
        rcvMusic.setAdapter(musicAdapter);
        rcvMusic.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setBackground() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.duck);
        layout.setBackground(new BitmapDrawable(getResources(), BlurBuilder.blur(this, bitmap)));


    }

    public void getAllMusic() {

        Cursor cursor = contentResolver.query(
                uriMediaStore,
                null,
                selection,
                null,
                sortOrder
                );

        while (cursor.moveToNext()) {
            String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String composer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.COMPOSER));
            String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//            Toast.makeText(this, ""+data+" - "+title + composer + duration + artist, Toast.LENGTH_SHORT).show();
            musicModels.add(new MusicModel(
                    title,
                    data,
                    artist,
                    duration
            ));
        }

    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicAdapter.stopPlaying();

    }
}
