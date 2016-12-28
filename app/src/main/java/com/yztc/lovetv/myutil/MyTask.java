package com.yztc.lovetv.myutil;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by My on 2016/11/30.
 */

public class MyTask extends AsyncTask<String,Void,String> {
	private String str;
	private OnGetValueListener onGetValueListener;

	public void setOnGetValueListener(OnGetValueListener onGetValueListener) {
		this.onGetValueListener = onGetValueListener;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			str= HttpUrlUtils.getString(params[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);
		if(onGetValueListener!=null)
		{
			onGetValueListener.finish(s);
		}
	}



	public interface OnGetValueListener
	{
		void finish(String result);
	}
}
