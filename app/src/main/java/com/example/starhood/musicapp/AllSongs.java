package com.example.starhood.musicapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllSongs extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST = 1;
    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);

        //checking for permission
        if (ContextCompat.checkSelfPermission(AllSongs.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(AllSongs.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(AllSongs.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
            else{
                ActivityCompat.requestPermissions(AllSongs.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
        }else DisplayMusic();
    }



    //get the array list data and assign it into the list view
    void DisplayMusic(){

        listView=(ListView)findViewById(R.id.allsongs_list_View);
        MusicLocation musicLocation=new MusicLocation();
        arrayList = musicLocation.getAllMusic(this,0);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //slice the song name
                String data=listView.getItemAtPosition(position).toString();
                String buffer[]=data.split(":");
                String buffer2[]=buffer[1].split("\n");
                String songName=buffer2[0];

                Toast.makeText(view.getContext(),songName,Toast.LENGTH_LONG).show();

                //send the song name to music actvity
                Intent intent=new Intent(view.getContext(),Play.class);
                intent.putExtra("SongName",songName);
                startActivity(intent);
            }
        });


    }


    //checking for permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    if(ContextCompat.checkSelfPermission(AllSongs.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
                    {
                        finish();

                    }
                return;
            }
        }
    }}
