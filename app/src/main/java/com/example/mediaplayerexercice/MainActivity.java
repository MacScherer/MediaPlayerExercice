package com.example.mediaplayerexercice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBarVolume;
    private ImageButton imageButtonPause, imageButtonPlay, imageButtonStop;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarVolume = findViewById(R.id.seekBarVolume);
        imageButtonPause = findViewById(R.id.imageButtonPause);
        imageButtonPlay = findViewById(R.id.imageButtonPlay);
        imageButtonStop = findViewById(R.id.imageButtonStop);

        loadMedia();

        imageButtonPlay.setOnClickListener(v -> {
            playMedia();
        });

        imageButtonPause.setOnClickListener(v -> {
            pauseMedia();
        });
        imageButtonStop.setOnClickListener(v -> {
            stopMedia();
        });
    }

    private void loadMedia() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bach);
    }

    public void pauseMedia(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
   }
   public void playMedia(){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
   }
   public void stopMedia(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            loadMedia();
        }
   }
}