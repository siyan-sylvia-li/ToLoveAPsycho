package com.example.sylviali.toloveapsycho;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Sylvia Li on 2017/9/20.
 */

public class ChoicesLayout extends RelativeLayout {
    private ImageView blood_splash;
    private TextView index;
    private TextView choice_description;
    private int linkIndex;

    public ChoicesLayout(Context context) {
        super(context);
        initView(context);
    }

    public ChoicesLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ChoicesLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public void initView(Context context) {
        /*
        LayoutInflater.from(context).inflate(R.layout.view_customtitle, this, true);
        iv_titlebar_left= (ImageView) findViewById(R.id.iv_titlebar_left);
        iv_titlebar_right= (ImageView) findViewById(R.id.iv_titlebar_right);
        tv_titlebar_title= (TextView) findViewById(R.id.tv_titlebar_title);
        layout_titlebar_rootlayout= (RelativeLayout) findViewById(R.id.layout_titlebar_rootlayout);
        //设置背景颜色
        layout_titlebar_rootlayout.setBackgroundColor(mColor);
        //设置标题文字颜色
        tv_titlebar_title.setTextColor(mTextColor);
         */
        LayoutInflater.from(context).inflate(R.layout.choices, this, true);
        blood_splash = (ImageView)findViewById(R.id.bloodsplash);
        index = (TextView)findViewById(R.id.index);
        choice_description = (TextView)findViewById(R.id.choice_description);
    }

    public void setDescription(String description) {
        this.choice_description.setText(description);
    }

    public void setIndex(String index) {
        this.index.setText(index);
    }

    public void setTypeFace(Typeface tf) {
        this.index.setTypeface(tf);
        this.choice_description.setTypeface(tf);
    }

    public void setPage(int p) {
        this.linkIndex = p;
    }

    public int getPage() {
        return this.linkIndex;
    }

    public String choiceDes() {
        return this.choice_description.getText().toString();
    }

}
