package com.example.starhood.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

public class Play extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageButton playNpause;
    TextView songNametv;
    boolean nowPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        playNpause=(ImageButton)findViewById(R.id.play_pause);
        songNametv=(TextView)findViewById(R.id.now_play_tv);
        Intent intent=getIntent();
        String songName=intent.getStringExtra("SongName");

        songNametv.setText(songName);

        if(null!=songName) {
            MusicLocation musicLocation=new MusicLocation();
            if (null != mediaPlayer)
                mediaPlayer.release();

            mediaPlayer = MediaPlayer.create(this, Uri.parse(new File(musicLocation.getSongData(this,songName)).toString()));
            mediaPlayer.start();
        }


        // play and pause button
        playNpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int paused=mediaPlayer.getCurrentPosition();
                if(nowPlaying) {
                    mediaPlayer.pause();
                    playNpause.setBackgroundResource(R.drawable.play);
                    nowPlaying=false;
                }
                else {
                    mediaPlayer.seekTo(paused);
                    mediaPlayer.start();
                    playNpause.setBackgroundResource(R.drawable.pause);
                    nowPlaying=true;

                }
            }
        });
    }

    //release player when press back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
