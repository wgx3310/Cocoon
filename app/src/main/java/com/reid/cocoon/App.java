package com.reid.cocoon;

import android.app.Application;

import com.reid.cocoon.common.AppCompat;
import com.reid.cocoon.common.content.Constant;
import com.reid.cocoon.common.utils.ProcessHelper;
import com.reid.cocoon.data.http.PhotoApiClient;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompat.init(getApplicationContext());

        if (ProcessHelper.isMainProcess()){
            PhotoApiClient.getInstance().register(Constant.BASE_URL_UNSPLASH_API);
        }
    }
}
