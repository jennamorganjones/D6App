package com.example.d6_hannah_java;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements MediaPlayer.OnCompletionListener {

    VideoView vw;
    ArrayList<Integer> videolist = new ArrayList<>();
    int currvideo = 0;
    Boolean isOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vw = (VideoView)findViewById(R.id.videoView);
        vw.setMediaController(new MediaController(this));
        vw.setOnCompletionListener(this);

        // video name should be in lower case alphabet.
        videolist.add(R.raw.video);
        setVideo(videolist.get(0));

        //Read More Button
        Button readMoreButton = (Button) findViewById(R.id.button2);
        readMoreButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
       });
        //React Button
        FloatingActionButton reactButton = (FloatingActionButton)findViewById(R.id.floatingActionButton4);
        reactButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        //AR Button
        FloatingActionButton aRButton = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        aRButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });

        MediaPlayer music = MediaPlayer.create(MainActivity.this, R.raw.soundbite);
        //Audio Button
        FloatingActionButton audioButton = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        audioButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                vw.pause();
                music.start();
                FloatingActionButton pauseButton = (FloatingActionButton) findViewById(R.id.floatingActionButton5);
                audioButton.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
            }
        });

        FloatingActionButton playButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        playButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                music.start();
                FloatingActionButton pauseButton = (FloatingActionButton) findViewById(R.id.floatingActionButton5);
                playButton.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.VISIBLE);

            }
        });

        FloatingActionButton pauseButton = (FloatingActionButton) findViewById(R.id.floatingActionButton5);
        pauseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                music.pause();
                FloatingActionButton playButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
                pauseButton.setVisibility(View.INVISIBLE);
                playButton.setVisibility(View.VISIBLE);

            }
        });


        //AR Button
        FloatingActionButton tourButton = (FloatingActionButton) findViewById(R.id.floatingActionButton1);
        tourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });


    }

    public void setVideo(int id)
    {
        String uriPath
                = "android.resource://"
                + getPackageName() + "/" + id;
        Uri uri = Uri.parse(uriPath);
        vw.setVideoURI(uri);
        vw.start();
    }

    public void onCompletion(MediaPlayer mediaplayer)
    {
        vw.start();
    }

    class MyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which)
        {
            if (which == -1) {
                vw.seekTo(0);
                vw.start();
            }
            else {
                ++currvideo;
                if (currvideo == videolist.size())
                    currvideo = 0;
                setVideo(videolist.get(currvideo));
            }
        }
    }
}