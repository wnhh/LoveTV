package com.yztc.lovetv.fragment.show;


import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.LiveContorllerActivity;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {

    private String BaseUrl="http://flv.quanmin.tv/live/";
    private String Suffix=".flv";
    private String Url;
    private Uri uri;
    private VideoView mVideoView;
    //初始化加载动画
    private LinearLayout mLinearLayout;
    private ImageView mLoadingImg;
    private ObjectAnimator mObjectAnimator;
    //
    private MediaController mMediaController;
    private LiveContorllerActivity mLiveContorllerActivity;

    /**
     *当前进度
     */
    private Long currentPosition=(long) 0;

    public ShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_show, container, false);
        //注册EventBus
        Bundle bundle = getArguments();
        int zhang = bundle.getInt("zhang");
        Url=BaseUrl+zhang+Suffix;
        Log.e("kim","传过来的值"+zhang);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=getActivity().getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //必须写这个，初始化加载库文件
        Vitamio.initialize(getContext());
        //设置视频解码监听
        initView(v);
        initData();
        if(!LibsChecker.checkVitamioLibs(getActivity())){
            return v;
        }
        return v;

    }

    private void initView(View v) {
        mVideoView= (VideoView) v.findViewById(R.id.buffer);
        mLinearLayout= (LinearLayout) v.findViewById(R.id.loading_layout);
        mLoadingImg= (ImageView) v.findViewById(R.id.loading_image);
        mMediaController= new MediaController(getContext());
        mLiveContorllerActivity=new LiveContorllerActivity(getContext(),mVideoView,getActivity());
//        mMediaController=new MediaController(getContext());
//        mLiveContorllerActivity=new LiveContorllerActivity(getContext(),mVideoView,getActivity());
    }

    private void initData() {
        uri=Uri.parse(Url);
        Log.e("kim","-----------------"+uri);
        mVideoView.setMediaController(mLiveContorllerActivity);//重要
        mVideoView.setVideoURI(uri);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
            }
        });
    }

    public void onResume(){
        super.onResume();
        preparePlayVideo();
    }

    private void preparePlayVideo() {

        startLoadingAnimator();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                stopLoadingAnimator();

                if (currentPosition>0){
                    mVideoView.seekTo(currentPosition);
                }else {
                    mp.setPlaybackSpeed(1.0f);
                }
                startPlay();
            }
        });
    }

    private void stopLoadingAnimator() {
        mLinearLayout.setVisibility(View.GONE);
        mObjectAnimator.cancel();
    }

    private void startPlay(){
        mVideoView.start();
    }

    private void startLoadingAnimator() {
        if (mObjectAnimator==null){
            mObjectAnimator=ObjectAnimator.ofFloat(mLoadingImg, "rotation", 0f, 360f);
        }
        mLinearLayout.setVisibility(View.VISIBLE);
        mObjectAnimator.setDuration(3000);
        mObjectAnimator.setRepeatCount(-1);
        mObjectAnimator.start();
    }
}
