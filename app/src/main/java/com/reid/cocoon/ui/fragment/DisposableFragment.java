package com.reid.cocoon.ui.fragment;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposableFragment extends Fragment {

    protected Handler mMainHandler = new Handler(Looper.getMainLooper());
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addDisposable(Disposable disposable){
        if (disposable == null){
            return;
        }

        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        if (mMainHandler != null){
            mMainHandler.removeCallbacksAndMessages(null);
            mMainHandler = null;
        }
        if (compositeDisposable != null){
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        super.onDestroy();
    }
}
