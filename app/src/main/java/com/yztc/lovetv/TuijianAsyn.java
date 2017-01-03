package com.yztc.lovetv;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.yztc.lovetv.myutil.HttpUrlUtils;

import java.io.IOException;

/**
 * Created by My on 2016/12/29.
 */

public class TuijianAsyn extends AsyncTask<Void,Void,String> {
    private String url;
    private GetJson getJson;

    public TuijianAsyn(String url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            String json = HttpUrlUtils.getString(url);
            if (json!=null){
                return json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        if (aVoid != null) {
            getJson.finish(aVoid);
        }
        super.onPostExecute(aVoid);
    }

    public void setGetJson(GetJson getJson) {
        this.getJson = getJson;
    }

    public interface GetJson{
        void finish(String s);
    }
}
