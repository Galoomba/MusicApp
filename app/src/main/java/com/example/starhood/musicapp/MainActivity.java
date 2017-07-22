package com.example.starhood.musicapp;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    RelativeLayout allsongs,artist,albums,radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allsongs=(RelativeLayout)findViewById(R.id.RLallsongs);
        artist=(RelativeLayout)findViewById(R.id.RLartist);
        albums=(RelativeLayout)findViewById(R.id.RLalbums);
        radio=(RelativeLayout)findViewById(R.id.RLradio);

        //transfer to songs activity
        allsongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),AllSongs.class);
                startActivity(intent);

            }
        });

        //transfer to artist activity
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Artists.class);
                startActivity(intent);
            }
        });

        //transfer to album activity
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Albums.class);
                startActivity(intent);
            }
        });

        //transfer to radio activity
        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),OnlineRadio.class);
                startActivity(intent);
            }
        });

    }






}
