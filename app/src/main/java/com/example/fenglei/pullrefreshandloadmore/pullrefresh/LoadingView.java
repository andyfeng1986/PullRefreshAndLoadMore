package com.example.fenglei.pullrefreshandloadmore.pullrefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.fenglei.pullrefreshandloadmore.R;

/**
 * Created by xianrongchen on 2016/3/30.
 */
public class LoadingView extends ImageView {

    private static final int MSG_HIDE_LOADING = 1;
    private static final int LOADING_TIME = 900;

    private Context mContext;

    private Handler mHandle = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case MSG_HIDE_LOADING:
                    hide();
                    break;
                default:
                    break;
            }
        }
    };

    public LoadingView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    private void init() {
        setBackgroundResource(R.drawable.loading);
//        mHandle.sendEmptyMessageDelayed(MSG_HIDE_LOADING, LOADING_TIME);

        AnimationDrawable animationDrawable = (AnimationDrawable) getBackground();
        animationDrawable.start();
    }

}
