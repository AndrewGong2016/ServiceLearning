package com.example.servicelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String TAG ="guantbbMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE
        };

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        long playid = 0;
        while(cursor!=null && cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            Log.d(TAG, "onCreate: id == "+ id+",title is "+title);

            if ("Shape of You".equals(title)){
                playid = id;
                break;
            }

        }
        Uri uri= Uri.parse(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI + "/" + playid);

        String path = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI + "/" + playid;
        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(this,uri);
//            mediaPlayer.setDataSource(path);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
