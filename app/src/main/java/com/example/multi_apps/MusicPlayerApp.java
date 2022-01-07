package com.example.multi_apps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MusicPlayerApp extends AppCompatActivity {
    ImageView play, prev, next, imageView;
    TextView songTitle;
    SeekBar mSeekBarTime, mSeekBarVol;
    static MediaPlayer mMediaPlayer;
    private Runnable runnable;
    private AudioManager mAudioManager;
    int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_app);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
// initializing views
        play = (ImageView)findViewById(R.id.play);
        prev = (ImageView)findViewById(R.id.prev);
        next = (ImageView)findViewById(R.id.next);
        songTitle = (TextView)findViewById(R.id.songTitle);
        imageView = (ImageView)findViewById(R.id.imageView);
        mSeekBarTime = (SeekBar)findViewById(R.id.seekBarTime);
        mSeekBarVol = (SeekBar)findViewById(R.id.seekBarVol);
// creating an ArrayList to store our songs
        final ArrayList<Integer> songs = new ArrayList<>();
        songs.add(0, R.raw.udja_kale_kawan);
        songs.add(1, R.raw.imtheone);
        songs.add(2, R.raw.musire_mabbula);
        songs.add(3, R.raw.surma_sarela);
        songs.add(4, R.raw.put_your_hearts_up);

// intializing mediaplayer
        mMediaPlayer = MediaPlayer.create(getApplicationContext(),
                songs.get(currentIndex));

// seekbar volume
        int maxV = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curV = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mSeekBarVol.setMax(maxV);
        mSeekBarVol.setProgress(curV);
        mSeekBarVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean
                    fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,
                        0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
//above seekbar volume
//
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBarTime.setMax(mMediaPlayer.getDuration());
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    play.setImageResource(R.drawable.play_btn);
                } else {
                    mMediaPlayer.start();
                    play.setImageResource(R.drawable.pause_btn);
                }

                songNames();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    play.setImageResource(R.drawable.pause_btn);
                }
                if (currentIndex < songs.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
                mMediaPlayer = MediaPlayer.create(getApplicationContext(),
                        songs.get(currentIndex));
                mMediaPlayer.start();
                songNames();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    play.setImageResource(R.drawable.pause_btn);
                }
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songs.size() - 1;
                }
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
                mMediaPlayer = MediaPlayer.create(getApplicationContext(),
                        songs.get(currentIndex));
                mMediaPlayer.start();
                songNames();

            }
        });
    }
    private void songNames() {
        if (currentIndex == 0) {
            songTitle.setText("Udja Kaale Kaavan");
            imageView.setImageResource(R.drawable.udja_kale_kaavan);
        }
        if (currentIndex == 1) {
            songTitle.setText("I'm the One");
            imageView.setImageResource(R.drawable.im_the_one);
        }
        if (currentIndex == 2) {
            songTitle.setText("Musire Mabula");
            imageView.setImageResource(R.drawable.headphone);
        }
        if (currentIndex == 3) {
            songTitle.setText("Surma Sarela");
            imageView.setImageResource(R.drawable.headphone);
        }
        if (currentIndex == 4) {
            songTitle.setText("Put your hearts Up");
            imageView.setImageResource(R.drawable.put_your_hearts_up);
        }

// seekbar duration
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mSeekBarTime.setMax(mMediaPlayer.getDuration());
                mMediaPlayer.start();
            }
        });
        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean
                    fromUser) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                    mSeekBarTime.setProgress(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mMediaPlayer != null) {
                    try {
                        if (mMediaPlayer.isPlaying()) {
                            Message message = new Message();
                            message.what = mMediaPlayer.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    @SuppressLint("Handler Leak") Handler handler = new Handler () {
        @Override
        public void handleMessage (Message msg) {
            mSeekBarTime.setProgress(msg.what);
        }
    };
}