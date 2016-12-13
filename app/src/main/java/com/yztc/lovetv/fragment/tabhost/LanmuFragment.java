package com.yztc.lovetv.fragment.tabhost;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yztc.lovetv.R;
import com.yztc.lovetv.contant.PicUrl;


/**
 * A simple {@link Fragment} subclass.
 */
public class LanmuFragment extends Fragment {

    public ImageView  lolpic,beauty,heartstone,outdoor,xianfeng,woman;
    public ImageView  wangzhe,qqche,yys,tvgame,street,paopaoche;
    public ImageView cf,mobile,fs,tank,msg,dance;
    public ImageView blizzard,erciyuan,daota2,zhuangjia,dnf,qiuqiu;
    public ImageView webgame,chuanqi,war3,fifa,nba;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lanmu, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        lolpic= (ImageView) v.findViewById(R.id.show1_iv);
        Picasso.with(getActivity()).load(PicUrl.LOLURL).into(lolpic);
        Log.v("kim","url"+PicUrl.LOLURL);
        beauty= (ImageView) v.findViewById(R.id.show2_iv);
        Picasso.with(getActivity()).load(PicUrl.BEAUTYURL).into(beauty);
        heartstone= (ImageView) v.findViewById(R.id.show3_iv);
        Picasso.with(getActivity()).load(PicUrl.HEARTSTONEURL).into(heartstone);
        outdoor= (ImageView)v. findViewById(R.id.show4_iv);
        Picasso.with(getActivity()).load(PicUrl.HUWAIURL).into(outdoor);
        xianfeng= (ImageView) v.findViewById(R.id.show5_iv);
        Picasso.with(getActivity()).load(PicUrl.OVERWATCHURL).into(xianfeng);
        woman= (ImageView) v.findViewById(R.id.show6_iv);
        Picasso.with(getActivity()).load(PicUrl.LOVEURL).into(woman);
        wangzhe= (ImageView) v.findViewById(R.id.show7_iv);
        Picasso.with(getActivity()).load(PicUrl.WANGZHEURL).into(wangzhe);
        qqche= (ImageView) v.findViewById(R.id.show8_iv);
        Picasso.with(getActivity()).load(PicUrl.QQFEICHEURL).into(qqche);
        yys= (ImageView) v.findViewById(R.id.show9_iv);
        Picasso.with(getActivity()).load(PicUrl.YYSURL).into(yys);
        tvgame= (ImageView) v.findViewById(R.id.show10_iv);
        Picasso.with(getActivity()).load(PicUrl.TVGAME).into(tvgame);
        street= (ImageView) v.findViewById(R.id.show11_iv);
        Picasso.with(getActivity()).load(PicUrl.STREETURL).into(street);
        paopaoche= (ImageView) v.findViewById(R.id.show12_iv);
        Picasso.with(getActivity()).load(PicUrl.PAOPAOURL).into(paopaoche);
        cf= (ImageView) v.findViewById(R.id.show13_iv);
        Picasso.with(getActivity()).load(PicUrl.CFPCURL).into(cf);
        mobile= (ImageView)v.findViewById(R.id.show14_iv);
        Picasso.with(getActivity()).load(PicUrl.MOBILEGAMEURL).into(mobile);
        fs= (ImageView) v.findViewById(R.id.show15_iv);
        Picasso.with(getActivity()).load(PicUrl.FSURL).into(fs);
        tank= (ImageView) v.findViewById(R.id.show16_iv);
        Picasso.with(getActivity()).load(PicUrl.TANKURL).into(tank);
        msg= (ImageView) v.findViewById(R.id.show17_iv);
        Picasso.with(getActivity()).load(PicUrl.MSGURL).into(msg);
        dance= (ImageView) v.findViewById(R.id.show18_iv);
        Picasso.with(getActivity()).load(PicUrl.DANCEURL).into(dance);
        blizzard= (ImageView) v.findViewById(R.id.show19_iv);
        Picasso.with(getActivity()).load(PicUrl.BLIZZARDURL).into(blizzard);
        erciyuan= (ImageView) v.findViewById(R.id.show20_iv);
        Picasso.with(getActivity()).load(PicUrl.ERCIYUANURL).into(erciyuan);
        daota2= (ImageView) v.findViewById(R.id.show21_iv);
        Picasso.with(getActivity()).load(PicUrl.DOTA2).into(daota2);
        zhuangjia= (ImageView) v.findViewById(R.id.show22_iv);
        Picasso.with(getActivity()).load(PicUrl.ZHUANGJIAURL).into(zhuangjia);
        dnf= (ImageView) v.findViewById(R.id.show23_iv);
        Picasso.with(getActivity()).load(PicUrl.DNFURL).into(dnf);
        qiuqiu= (ImageView) v.findViewById(R.id.show24_iv);
        Picasso.with(getActivity()).load(PicUrl.QIUQIUURL).into(qiuqiu);
        webgame= (ImageView) v.findViewById(R.id.show25_iv);
        Picasso.with(getActivity()).load(PicUrl.WEBGAMEURL).into(webgame);
        chuanqi= (ImageView) v.findViewById(R.id.show26_iv);
        Picasso.with(getActivity()).load(PicUrl.CHUANQIURL).into(chuanqi);
        war3= (ImageView) v.findViewById(R.id.show27_iv);
        Picasso.with(getActivity()).load(PicUrl.WAR3URL).into(war3);
        fifa= (ImageView) v.findViewById(R.id.show28_iv);
        Picasso.with(getActivity()).load(PicUrl.FIFAURL).into(fifa);
        nba= (ImageView) v.findViewById(R.id.show29_iv);
        Picasso.with(getActivity()).load(PicUrl.NBAURL).into(nba);
    }

}
