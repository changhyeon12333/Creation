package com.example.musicalbum;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AudioImage extends AppCompatActivity {
    MediaPlayer mp = new MediaPlayer();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_image);
        setTitle("노래 재생");
        Intent it = getIntent();    // 호출한 인텐트 인식
        String tag = it.getStringExtra("it_tag");   // 태그 값 추출

        TextView title = (TextView)findViewById(R.id.title);
        ImageView song_image = (ImageView)findViewById(R.id.song_image);
        TextView lyrics = (TextView)findViewById(R.id.lyrics);

        Resources res = getResources();

        int stringId;
        String myKey;

        // 제목 출력
        stringId = res.getIdentifier("title" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        title.setText(myKey);


        // 노래 이미지 출력
        stringId = res.getIdentifier("song_image" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        int id_image = res.getIdentifier(myKey, "drawable", getPackageName());
        song_image.setImageResource(id_image);

        // 노래 가사 출력
        stringId = res.getIdentifier("lyrics" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        lyrics.setText(myKey);

        // 오디오 재생
        stringId = res.getIdentifier("audio" + tag, "string", getPackageName());
        myKey = res.getString(stringId);
        int id_audio = res.getIdentifier(myKey, "raw", getPackageName());
        mp = MediaPlayer.create(this, id_audio);
        mp.setLooping(false);
        mp.start();
    }

    public void goBack(View v){
        if(mp.isPlaying()){
            mp.stop();
            mp.release();
        }
        finish();

    }

}
