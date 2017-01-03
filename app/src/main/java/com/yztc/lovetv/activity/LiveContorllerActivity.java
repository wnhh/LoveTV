package com.yztc.lovetv.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.yztc.lovetv.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LiveContorllerActivity extends MediaController {

    private VideoView videoView;
    private Activity activity;
    private Context context;
    private ImageView action;
    private ImageView gift;
    private ImageView play;
    private ImageView fullScreen;
    private int controllerWidth = 0;


    public LiveContorllerActivity(Context context, VideoView videoView, Activity activity) {
        super(context);
        this.context = context;
        this.videoView = videoView;
        this.activity = activity;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        controllerWidth = wm.getDefaultDisplay().getHeight();
    }

    @Override
    protected View makeControllerView() {
        //自定义布局文件名称
        View v=((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
                inflate(getResources().getIdentifier("activity_live_contorller","layout",getContext().getPackageName()),this);
        v.setMinimumHeight(controllerWidth);//设置controller显示
        action= (ImageView) v.findViewById(getResources().getIdentifier("choose_iv","id",context.getPackageName()));
        gift= (ImageView) v.findViewById(getResources().getIdentifier("gift_iv","id",context.getPackageName()));
        play= (ImageView) v.findViewById(getResources().getIdentifier("play_iv","id",context.getPackageName()));
        fullScreen= (ImageView) v.findViewById(getResources().getIdentifier("watch_iv","id",context.getPackageName()));
        initClick(v);
        return v;
    }

    private void initClick(View v) {
        play= (ImageView) v.findViewById(R.id.play_iv);
        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playOrPause();
                if (videoView.isPlaying()){
                    play.setImageResource(R.mipmap.btn_live_pause);
                }else {
                    play.setImageResource(R.mipmap.btn_live_play);
                }
                Log.e("zhang","--------------------");
            }
        });
    }
    private void playOrPause(){
        if (play!=null){
            if (videoView.isPlaying()){
                videoView.pause();
                Toast.makeText(getContext(),"暂停",Toast.LENGTH_SHORT).show();
            }else {
                videoView.start();
                Toast.makeText(getContext(),"开始",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
