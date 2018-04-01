package com.reid.cocoon.common.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class DoubleTapHelper {

    private static final int DOUBLE_TAP_TIMEOUT = 300;
    private static final int DOUBLE_TAP_MIN_TIME = 40;

    private View mView;
    private int mDoubleTapSlopSquare;
    private MotionEvent mCurrentDownEvent;
    private MotionEvent mPreviousUpEvent;

    private OnDoubleTapListener mListener;

    public DoubleTapHelper listener(OnDoubleTapListener listener){
        mListener = listener;
        return this;
    }

    public interface OnDoubleTapListener{
        void onDoubleTap(View v, MotionEvent e);
    }

    public static DoubleTapHelper attach(View view){
        return new DoubleTapHelper(view);
    }

    private DoubleTapHelper(View view){
        mView = view;

        if (mView == null) return;

        ViewConfiguration configuration = ViewConfiguration.get(view.getContext());
        int scaledDoubleTapSlop = configuration.getScaledDoubleTapSlop();
        mDoubleTapSlopSquare = scaledDoubleTapSlop * scaledDoubleTapSlop;


        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(isConsideredDoubleTap(mCurrentDownEvent,mPreviousUpEvent,event)){
                            if (mListener != null){
                                mListener.onDoubleTap(v, event);
                            }
                        }
                        if (mCurrentDownEvent != null) {
                            mCurrentDownEvent.recycle();
                        }
                        mCurrentDownEvent = MotionEvent.obtain(event);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mPreviousUpEvent != null) {
                            mPreviousUpEvent.recycle();
                        }
                        mPreviousUpEvent = MotionEvent.obtain(event);
                        break;
                }
                return false;
            }
        });

    }

    private boolean isConsideredDoubleTap(MotionEvent firstDown,MotionEvent firstUp,MotionEvent secondDown) {
        if(firstDown == null || secondDown == null || firstUp == null){
            return false;
        }

        final long deltaTime = secondDown.getEventTime() - firstUp.getEventTime();
        if (deltaTime > DOUBLE_TAP_TIMEOUT || deltaTime < DOUBLE_TAP_MIN_TIME) {
            return false;
        }

        int deltaX = (int) firstDown.getX() - (int) secondDown.getX();
        int deltaY = (int) firstDown.getY() - (int) secondDown.getY();
        return (deltaX * deltaX + deltaY * deltaY < mDoubleTapSlopSquare);
    }
}
