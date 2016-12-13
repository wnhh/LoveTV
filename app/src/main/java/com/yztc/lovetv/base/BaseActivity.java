package com.yztc.lovetv.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by bodhixu on 2016/11/28.
 * Activity的基类
 */

public class BaseActivity extends AppCompatActivity{

    private Toast toast;

    //显示Toast
    protected void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

}
