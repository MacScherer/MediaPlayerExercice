package com.example.mediaplayerexercice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBarVolume;
    private ImageButton imageButtonPause, imageButtonPlay, imageButtonStop;
    private MediaPlayer mediaPlayer;
    private AudioManager myAudioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarVolume = findViewById(R.id.seekBarVolume);
        imageButtonPause = findViewById(R.id.imageButtonPause);
        imageButtonPlay = findViewById(R.id.imageButtonPlay);
        imageButtonStop = findViewById(R.id.imageButtonStop);
        
        initializeSeekBar()
;        loadMedia();

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


    private void initializeSeekBar() {
        // configure audio manager
        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //getVolume
        int maxVolume = myAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int actualVolume = myAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBarVolume.setMax(maxVolume);
        seekBarVolume.setProgress(actualVolume);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
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
        if ((mediaPlayer.isPlaying()) || (!mediaPlayer.isPlaying())){
            mediaPlayer.stop();
            loadMedia();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        //    pauseMedia();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}