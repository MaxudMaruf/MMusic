package com.example.masud.mmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class Player extends AppCompatActivity implements View.OnClickListener {
    static  MediaPlayer mp;
    ArrayList<File>mysongs;
    int position;
    Uri u;
    Thread updateSeekBar;
    SeekBar sb;
   // private DrawerLayout mDrawerLayout;
    //private ActionBarDrawerToggle mToogle;
    ImageButton btPlay,btFf,btFb,btNxt,btPv;;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

       btPlay=(ImageButton)findViewById(R.id.btPlay);
        btFb=(ImageButton) findViewById(R.id.btFb);
        btFf=(ImageButton) findViewById(R.id.btFf);
        btNxt=(ImageButton) findViewById(R.id.btNxt);
        btPv=(ImageButton) findViewById(R.id.btPv);

        btPlay.setOnClickListener(this);
        btFb.setOnClickListener(this);
        btFf.setOnClickListener(this);
        btNxt.setOnClickListener(this);
        btPv.setOnClickListener(this);
        sb=(SeekBar) findViewById(R.id.seekBar);
        updateSeekBar=new Thread(){
            @Override
            public void run() {

                int totalDuration=mp.getDuration();
                int currentPosition=0;

                while (currentPosition<totalDuration){
                    try {
                        sleep(500);
                        currentPosition=mp.getCurrentPosition();
                        sb.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
               // super.run();
            }
        };



        if(mp!=null){
            mp.stop();
            mp.release();
        }



        Intent i=getIntent();
        Bundle b=i.getExtras();
        mysongs=(ArrayList) b.getParcelableArrayList("songlist");
        position=b.getInt("pos",0);
         u=Uri.parse(mysongs.get(position).toString());
        mp= MediaPlayer.create(getApplicationContext(),u );
        mp.start();
        sb.setMax(mp.getDuration());
        updateSeekBar.start();
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.btPlay:
                if(mp.isPlaying()){
                   // btPlay.setText(">");
                    mp.pause();

                }
                else mp.start();
            case R.id.btFf:
                mp.seekTo(mp.getCurrentPosition()+5000);
                break;
            case R.id.btFb:
                mp.seekTo(mp.getCurrentPosition()-5000);
                break;
            case R.id.btNxt:
                mp.stop();
                mp.release();
                //if(position+1>mysongs.size()){
                //    position=(position+1)%mysongs.size();
                //}else{
                //    position=position+1;
                //}

                position=(position+1)%mysongs.size();
                u=Uri.parse(mysongs.get(position).toString());
                mp= MediaPlayer.create(getApplicationContext(),u );
                mp.start();
                sb.setMax(mp.getDuration());
                break;
            case R.id.btPv:
                mp.stop();
                mp.release();
                //if(position-1>0){

                  //  position=mysongs.size()-1;
               // }else{
                //    position=position-1;
                //}
                position=(position-1<0)? mysongs.size()-1:position-1;

                u=Uri.parse(mysongs.get(position).toString());
                mp= MediaPlayer.create(getApplicationContext(),u );
                mp.start();
                sb.setMax(mp.getDuration());
                break;

        }
    }
}
