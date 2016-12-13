package com.yztc.lovetv.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by bodhixu on 2016/11/28.
 */

public class BaseFragment extends Fragment{


    private Toast toast;

    //显示Toast
    protected void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

}
