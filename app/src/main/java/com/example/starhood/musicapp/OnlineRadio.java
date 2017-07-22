package com.example.starhood.musicapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class OnlineRadio extends AppCompatActivity {

    ImageButton playNpause;
    MediaPlayer mediaPlayer;
    TextView loading;
    boolean Playing =true;
    String stream ="http://stream.radioreklama.bg:80/city.ogg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_radio);

        loading=(TextView)findViewById(R.id.now_play_radio_tv);
        playNpause=(ImageButton)findViewById(R.id.play_pause_Radio);

        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        playNpause.setEnabled(false);

        new doInBackGround().execute(stream);


        playNpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Playing) {
                    mediaPlayer.pause();
                    playNpause.setBackgroundResource(R.drawable.play);
                    Playing=false;
                }
                else {

                    mediaPlayer.start();
                    playNpause.setBackgroundResource(R.drawable.pause);
                    Playing=true;

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

    private class doInBackGround extends AsyncTask <String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            try {
                mediaPlayer.setDataSource(params[0]);
                mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mediaPlayer.start();
            playNpause.setEnabled(true);
            loading.setText("Online Radio ON.. Enjoy");
        }
    }
}
