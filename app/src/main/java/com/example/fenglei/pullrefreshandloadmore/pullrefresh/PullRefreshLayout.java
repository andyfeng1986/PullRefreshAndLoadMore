package com.example.fenglei.pullrefreshandloadmore.pullrefresh;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fenglei on 17-7-11.
 */

public class PullRefreshLayout extends ViewGroup {

    private static final int LOADING_VIEW_HEIGHT = 140;
    private LoadingView mLoadingView;
    private View mTargetView;

    public PullRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mLoadingView = new LoadingView(context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LOADING_VIEW_HEIGHT);
        addView(mLoadingView, layoutParams);
    }

    private float mLastTouchX;
    private float mLastTouchY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mLastTouchX = ev.getX();
        mLastTouchY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if(!canChildScrollUp()) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float currentTouchX = ev.getX();
        float currentTouchY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = currentTouchY - mLastTouchY;
                scrollBy(0, -(int)dy);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastTouchX = ev.getX();
        mLastTouchY = ev.getY();
        return super.onTouchEvent(ev);
    }

    public boolean canChildScrollUp() {
        return ViewCompat.canScrollVertically(mTargetView, -1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(heightMeasureSpec);

        int loadingViewWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(),
                mLoadingView.getLayoutParams().width);
        int loadingViewHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(),
                mLoadingView.getLayoutParams().height);
        mLoadingView.measure(loadingViewWidthMeasureSpec, loadingViewHeightMeasureSpec);

        ensureTargetView();

        int targetViewWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(),
                mTargetView.getLayoutParams().width);
        int targetViewHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(),
                mTargetView.getLayoutParams().height);
        mTargetView.measure(targetViewWidthMeasureSpec, targetViewHeightMeasureSpec);

        int width = Math.max(mLoadingView.getMeasuredWidth(), mTargetView.getMeasuredWidth());
        int height = mLoadingView.getMeasuredHeight() + mTargetView.getMeasuredHeight();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLoadingView.layout(getPaddingLeft(), -mLoadingView.getMeasuredHeight(),
                mLoadingView.getMeasuredWidth() + getPaddingLeft(), 0);
        mTargetView.layout(getPaddingLeft(), 0, mTargetView.getMeasuredHeight() + getPaddingLeft(), getMeasuredHeight());
    }

    private void ensureTargetView() {
        if(mTargetView == null) {
            for(int i = 0; i < getChildCount(); i++) {
                if(!getChildAt(i).equals(mLoadingView)) {
                    mTargetView = getChildAt(i);
                    break;
                }
            }
        }
    }

}
