package com.example.starhood.musicapp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by Starhood on 4/28/17.
 */

public class MusicLocation {

    public MusicLocation(){

    }

    //return all songs data
    ArrayList<String> getAllMusic(Context context,int flag){

        ArrayList<String> arrayList=new ArrayList<>();
        ContentResolver contentResolver=context.getContentResolver();
        Uri songs = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songsCursor = contentResolver.query(songs,null,null,null,null);

        if(null != songsCursor && songsCursor.moveToFirst()){

            int currentSongTitleIndex =songsCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int CurrentArtistNameIndex=songsCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int CurrentAlbumNameIndex=songsCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int Data=songsCursor.getColumnIndex(MediaStore.Audio.Media.DATA);


            do{
                String songTitle= songsCursor.getString(currentSongTitleIndex);
                String songArtist= songsCursor.getString(CurrentArtistNameIndex);
                String songAlbum= songsCursor.getString(CurrentAlbumNameIndex);
                String songData= songsCursor.getString(Data);

                //to return all the data
                if(flag==0)
                    arrayList.add("Song :"+songTitle+"\nArtist :"+songArtist+"\nAlbum :"+songAlbum);
                // to return only the artist names
                else if(flag==1) {
                    if(!arrayList.contains(songArtist))
                        arrayList.add(songArtist);
                }
                // to return onlu album names
                else if (flag==2) {
                    if(!arrayList.contains(songAlbum))
                        arrayList.add(songAlbum);
                }
            }while (songsCursor.moveToNext());
        }
        return arrayList;
    }

    //Return song location as string
    String getSongData(Context context,String songName) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri songs = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songsCursor = contentResolver.query(songs, null, null, null, null);
        String retURi=null;

        if (null != songsCursor && songsCursor.moveToFirst()) {

            int currentSongTitleIndex =songsCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int Data = songsCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do {
                String songTitle= songsCursor.getString(currentSongTitleIndex);
                String songData= songsCursor.getString(Data);
                if(songTitle.equals(songName))
                    retURi=songData;

            } while (songsCursor.moveToNext());

        }
        return retURi;
    }

    //return songs album
    ArrayList<String> getAlbumSongs(Context context,String albumName)
    {
        ArrayList<String> arrayList=new ArrayList<>();
        ContentResolver contentResolver=context.getContentResolver();
        Uri songs = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songsCursor = contentResolver.query(songs,null,null,null,null);

        if(null != songsCursor && songsCursor.moveToFirst()){

            int currentSongTitleIndex =songsCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int CurrentAlbumNameIndex=songsCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            do{
                String songTitle= songsCursor.getString(currentSongTitleIndex);
                String songAlbum= songsCursor.getString(CurrentAlbumNameIndex);

                //to return album songs
                if(songAlbum.equals(albumName))
                    arrayList.add(songTitle);

            }while (songsCursor.moveToNext());
        }
        return arrayList;
    }

    //return  artist songs
    ArrayList<String> getAllArtistSongs(Context context,String artist){

        ArrayList<String> arrayList=new ArrayList<>();
        ContentResolver contentResolver=context.getContentResolver();
        Uri songs = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songsCursor = contentResolver.query(songs,null,null,null,null);

        if(null != songsCursor && songsCursor.moveToFirst()){

            int currentSongTitleIndex =songsCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int CurrentArtistNameIndex=songsCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do{
                String songTitle= songsCursor.getString(currentSongTitleIndex);
                String songArtist= songsCursor.getString(CurrentArtistNameIndex);

                //to return all the data
                if(songArtist.equals(artist))
                    arrayList.add(songTitle);

            }while (songsCursor.moveToNext());
        }
        return arrayList;
    }
}
