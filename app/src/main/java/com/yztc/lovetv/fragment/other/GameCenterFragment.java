package com.yztc.lovetv.fragment.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.apiservice.VpPictureApiService;
import com.yztc.lovetv.bean.CenterGame;
import com.yztc.lovetv.contant.TvUrl;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameCenterFragment extends Fragment {


	private ImageView centergameone_iv;
	private TextView gamename_tv;
	private TextView infoone_tv;
	private TextView download_tv;
	private ImageView centergametwo_iv;
	private TextView gamenametwo_tv;
	private TextView infotwo_tv;
	private TextView downloadtwo_tv;
	private Retrofit rf;
	private VpPictureApiService mApiService;

	public GameCenterFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_game_center, container, false);
		initData();
		initView(view);
		return view;
	}

	private void initData() {
		rf = new Retrofit.Builder()
				.baseUrl(TvUrl.GAMECENTER)
				.build();
		//getCenterGameBeanCall
		mApiService = rf.create(VpPictureApiService.class);
		Call<ResponseBody> call = mApiService.getCenterGameBeanCall();
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				String json = null;
				try {
						json = response.body().string();
						Gson gson = new Gson();
						CenterGame cg = gson.fromJson(json, CenterGame.class);
						Glide.with(getContext()).load(cg.getData().get(0).getPic()).into(centergameone_iv);
						gamename_tv.setText(cg.getData().get(0).getTitle());
						infoone_tv.setText(cg.getData().get(0).getDesc());
						Glide.with(getContext()).load(cg.getData().get(1).getPic()).into(centergametwo_iv);
						gamenametwo_tv.setText(cg.getData().get(1).getTitle());
						infotwo_tv.setText(cg.getData().get(1).getDesc());

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

			}
		});
	}
	private void initView(View view) {
		centergameone_iv = (ImageView) view.findViewById(R.id.centergameone_iv);
		gamename_tv = (TextView) view.findViewById(R.id.gamename_tv);
		infoone_tv = (TextView) view.findViewById(R.id.infoone_tv);
		download_tv = (TextView) view.findViewById(R.id.download_tv);
		centergametwo_iv = (ImageView) view.findViewById(R.id.centergametwo_iv);
		gamenametwo_tv = (TextView) view.findViewById(R.id.gamenametwo_tv);
		infotwo_tv = (TextView) view.findViewById(R.id.infotwo_tv);
		downloadtwo_tv = (TextView) view.findViewById(R.id.downloadtwo_tv);
	}
}
