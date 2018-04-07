package com.reid.cocoon;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.reid.cocoon.common.AppCompat;
import com.reid.cocoon.common.content.Constant;
import com.reid.cocoon.common.utils.ProcessHelper;
import com.reid.cocoon.data.http.PhotoApiClient;
import com.reid.cocoon.data.io.OkHttpConnection;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompat.init(getApplicationContext());

        if (ProcessHelper.isMainProcess()){
            PhotoApiClient.getInstance().register(Constant.BASE_URL_UNSPLASH_API);
            DownloadMgrInitialParams.InitCustomMaker initCustomMaker = FileDownloader.setupOnApplicationOnCreate(this);
            initCustomMaker.connectionCreator(new OkHttpConnection.Creator());
        }
    }
}
