package com.example.sylviali.toloveapsycho;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import java.util.logging.LogManager;

/**
 * Created by Sylvia Li on 2017/10/5.
 */

public class MaxScrollView extends ScrollView {
    public static int WITHOUT_MAX_HEIGHT_VALUE = -1;

    private int maxHeight = WITHOUT_MAX_HEIGHT_VALUE;


    public MaxScrollView(Context context) {
        super(context);
    }

    public MaxScrollView(Context context, AttributeSet attr) {
        super(context,attr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

}
