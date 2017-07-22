package com.example.starhood.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Albums extends AppCompatActivity {
    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        DisplayAlbums();
    }


    void DisplayAlbums(){

        listView=(ListView)findViewById(R.id.albums_list_View);
        MusicLocation musicLocation=new MusicLocation();
        arrayList = musicLocation.getAllMusic(this,2);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        //send the album Name to the DisplayMusic activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(view.getContext(),DisplaySelectedMusic.class);
                String data=listView.getItemAtPosition(position).toString();
                intent.putExtra("album",data);
                intent.putExtra("From","Albums");
                Toast.makeText(view.getContext(),listView.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        });

    }
}
