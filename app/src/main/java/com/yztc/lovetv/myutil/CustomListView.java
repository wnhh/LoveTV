package com.yztc.lovetv.myutil;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by My on 2017/1/1.
 */

public class CustomListView extends ListView{
    public CustomListView(Context context) {
        this(context, null);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
