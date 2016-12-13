package com.yztc.lovetv.myutil;

import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlUtils {

	public static String getString(String strUrl) throws IOException {
		String result = null;
		URL url = new URL(strUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setReadTimeout(5000);
		urlConnection.setRequestMethod("GET");
		if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			InputStream is = urlConnection.getInputStream();
			byte[] bytes = new byte[1024];
			int length;
			while ((length = is.read(bytes)) != -1) {
				bos.write(bytes, 0, length);
				bos.flush();
			}
			result = bos.toString();
			is.close();
			return result;
		}
		return null;
	}

	public static byte[] getByetArray(String strUrl) throws IOException {

		URL url = new URL(strUrl);
		byte[] result;
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setReadTimeout(5000);
		urlConnection.setRequestMethod("GET");
		if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			InputStream is = urlConnection.getInputStream();
			byte[] bytes = new byte[1024];
			int length;
			while ((length = is.read(bytes)) != -1) {
				bos.write(bytes, 0, length);
				bos.flush();
			}
			result = bos.toByteArray();
			is.close();
			bos.close();
			return result;
		}

		return null;
	}

	public static void DownloaderFile(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		byte[] result;
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setReadTimeout(5000);
		urlConnection.setRequestMethod("GET");
		if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

			String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/";
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdir();
			}
			int index = (strUrl.lastIndexOf("/") + 1);
			String filename = strUrl.substring(index);
			File file = new File("download/" + filename);
			if (file.exists()) {
				file.createNewFile();
				OutputStream os = new FileOutputStream(file);
				InputStream is = urlConnection.getInputStream();
				byte[] bytes = new byte[1024];
				int length;
				while ((length = is.read(bytes)) != -1) {
					// 把当前进度发布到onProgressUpdate方法中
					os.write(bytes, 0, length);
					os.flush();
					Log.v("data", "文件在下载");
				}
				is.close();
				os.close();
			}
		}
	}
}
