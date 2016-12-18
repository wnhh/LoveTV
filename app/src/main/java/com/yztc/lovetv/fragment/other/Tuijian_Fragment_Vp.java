package com.yztc.lovetv.fragment.other;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.JingCaiTJAdapter;
import com.yztc.lovetv.adapter.MyPagerAdapter;
import com.yztc.lovetv.adapter.SectionAdapter;
import com.yztc.lovetv.apiservice.LitchiapiService;
import com.yztc.lovetv.apiservice.VpPictureApiService;
import com.yztc.lovetv.bean.FirstPagerBean;
import com.yztc.lovetv.bean.LunBoPictureBean;
import com.yztc.lovetv.bean.TuiJianItem;
import com.yztc.lovetv.bean.TuijianStringitem;
import com.yztc.lovetv.bean.tuijian;
import com.yztc.lovetv.contant.BaseUrl;
import com.yztc.lovetv.contant.PicUrl;
import com.yztc.lovetv.contant.TvUrl;
import com.yztc.lovetv.myutil.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tuijian_Fragment_Vp extends Fragment {


    private Retrofit rfit, vpfit;
    private LitchiapiService litchias;
    private VpPictureApiService vpService;
    private Call<ResponseBody> callRv;


    private RecyclerView tuijianitem_rv;
    private RollPagerView mViewPager;
    //数据源
    List<String> liststr;
    List<TuijianStringitem> listTj;
    List<LunBoPictureBean> lunBoPictureBeans;


    //adapter
    private JingCaiTJAdapter jcAdapter;
    private SectionAdapter sectionAdapter;
    private VpPictureApiService mApiService;
    private LinearLayout ll_vp;

    private static final String TAG = "data";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tuijian_vp, container, false);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_vp, container, false);
        ll_vp = (LinearLayout) inflate.findViewById(R.id.ll_vp);
        initViewPager(v);
        initRecyclerView(v);
        initData();
        return v;
    }

    private void initRecyclerView(View v) {
        tuijianitem_rv = (RecyclerView) v.findViewById(R.id.tuijianitem_rv);

        tuijianitem_rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TuijianStringitem ts = listTj.get(i);
                if (ts.isHeader) {
                    //陈雨晗你可以写跳转了！
                }
            }
        });
        tuijianitem_rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rfit = new Retrofit.Builder()
                .baseUrl(TvUrl.TUIAJIN)
                .build();
    }

    private void initViewPager(View v) {
        Log.e("data", "-------------------------12345 " );
        lunBoPictureBeans = new ArrayList<LunBoPictureBean>();
        LunBoPictureBean lunBoPictureBean1 = new LunBoPictureBean();
        lunBoPictureBean1.setPicture(PicUrl.PICTURE1);
        lunBoPictureBean1.setTitle("全民星盛典开赛");
        LunBoPictureBean lunBoPictureBean2 = new LunBoPictureBean();
        lunBoPictureBean2.setPicture(PicUrl.PICTURE2);
        lunBoPictureBean2.setTitle("TDT2016腾讯斗地主锦标赛");
        LunBoPictureBean lunBoPictureBean3 = new LunBoPictureBean();
        lunBoPictureBean3.setPicture(PicUrl.PICTURE3);
        lunBoPictureBean3.setTitle("一脸懵逼");
        LunBoPictureBean lunBoPictureBean4 = new LunBoPictureBean();
        lunBoPictureBean4.setPicture(PicUrl.PICTURE4);
        lunBoPictureBean4.setTitle("大号已前百");
        LunBoPictureBean lunBoPictureBean5 = new LunBoPictureBean();
        lunBoPictureBean5.setPicture(PicUrl.PICTURE5);
        lunBoPictureBean5.setTitle("治愈系歌声诗诗");

      /*  //实例化Retro
        initRetrofit();
        mApiService.getAdTypeBeanCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FirstPagerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FirstPagerBean firstPagerBean) {
                        Log.e("data", "-------------------" );
                        Log.e("data", "firstPagerBean"+firstPagerBean.getApp_focus().size());
                        for (int i = 0; i < firstPagerBean.getApp_focus().size(); i++) {
                            LunBoPictureBean lunBoPictureBean = new LunBoPictureBean();
                            //图片地址
                            lunBoPictureBean.setPicture(firstPagerBean.getApp_focus().get(i).getThumb());
                            Log.e("data", "firstPagerBean.getApp_focus().get(i).getThumb()"+firstPagerBean.getApp_focus().get(i).getThumb() );
                            //文字
                            lunBoPictureBean.setTitle(firstPagerBean.getApp_focus().get(i).getTitle());
                            Log.e("data", "firstPagerBean.getApp_focus().get(i).getTitle()"+firstPagerBean.getApp_focus().get(i).getTitle());
                            lunBoPictureBeans.add(lunBoPictureBean);
                        }

                    }
                });
*/

        mViewPager = (RollPagerView) v.findViewById(R.id.tuijian_vp);
        mViewPager.setHintView(new ColorPointHintView(getContext(), Color.WHITE, Color.GRAY));
        //ViewPager适配器
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getContext(), lunBoPictureBeans);
        //set适配器
        mViewPager.setAdapter(myPagerAdapter);

    }



/*    private void initRetrofit() {
        vpfit = new Retrofit.Builder()
                .baseUrl(BaseUrl.VpPicture)
                .client(OkHttpUtils.newOkHttpClient(getContext()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //完成网络请求
        mApiService = vpfit.create(VpPictureApiService.class);
    }*/


    private void initData() {
        liststr = new ArrayList<>();
        listTj = new ArrayList<>();
        TuijianStringitem ttitem = new TuijianStringitem(true, "精彩推荐", true);
        listTj.add(ttitem);
        //获得接口对象
        litchias = rfit.create(LitchiapiService.class);
        callRv = litchias.getLitchCall();
        callRv.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Gson gson = new Gson();
                    tuijian tuijian = gson.fromJson(response.body().string(), tuijian.class);
                    for (int i = 0; i < tuijian.getRoom().get(0).getList().size(); i++) {
                        if (i == 6) {
                            break;
                        }
                        TuiJianItem ti = new TuiJianItem();
                        ti.setBigPicUrl(tuijian.getRoom().get(0).getList().get(i).getThumb());
                        ti.setPersonalPicUrl(tuijian.getRoom().get(0).getList().get(i).getAvatar());
                        ti.setIntroduce(tuijian.getRoom().get(0).getList().get(i).getTitle());
                        ti.setName(tuijian.getRoom().get(0).getList().get(i).getNick());
                        TuijianStringitem tti = new TuijianStringitem(ti);
                        tti.setMore(true);
                        listTj.add(tti);
                    }
                    TuijianStringitem ttitem2 = new TuijianStringitem(true, "颜值控", true);
                    TuijianStringitem ttitem3 = new TuijianStringitem(true, "英雄联盟", true);
                    listTj.add(ttitem2);
                    listTj.add(ttitem3);
                    sectionAdapter = new SectionAdapter(getContext(), listTj);
                    sectionAdapter.addHeaderView(ll_vp);
                    tuijianitem_rv.setAdapter(sectionAdapter);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
