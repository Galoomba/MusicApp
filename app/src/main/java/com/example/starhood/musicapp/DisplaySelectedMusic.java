package com.example.starhood.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplaySelectedMusic extends AppCompatActivity {

    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_selected_music);

        TextView textView = (TextView) findViewById(R.id.ts);
        intent = getIntent();
        String From = intent.getStringExtra("From");

        if (From.equals("Albums"))
            fromAlbums();
        else if (From.equals("Artists"))
            fromArtist();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), Play.class);
                intent.putExtra("SongName", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

    }

    //if the intent came from album activity
    void fromAlbums() {
        String albumName = intent.getStringExtra("album");
        listView = (ListView) findViewById(R.id.DisplaySelected_list_View);
        arrayList = new MusicLocation().getAlbumSongs(this, albumName);
    }

    //if the intent came from artist activity
    void fromArtist() {
        String artistName = intent.getStringExtra("artist");
        listView = (ListView) findViewById(R.id.DisplaySelected_list_View);
        arrayList = new MusicLocation().getAllArtistSongs(this, artistName);
    }


}
