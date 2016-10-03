package com.myxh.coolshopping.common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.myxh.coolshopping.R;
import com.uuzuche.lib_zxing.ZApplication;
import com.yolanda.nohttp.NoHttp;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by asus on 2016/8/30.
 */
public class CoolApplication extends ZApplication {

    private static CoolApplication appContext;
    private boolean flag = true;
    private List<BDLocation> mLocations = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        //NoHttp初始化
        NoHttp.initialize(this);
        //Fresco初始化
        Fresco.initialize(this);
        //百度地图初始化
        SDKInitializer.initialize(this);
        //Bmob初始化
        Bmob.initialize(this, AppConstant.BMOB_AppID);

        if (flag) {
            flag = false;
            BmobUpdateAgent.initAppVersion();
        }
    }

    public static CoolApplication getAppContext() {
        return appContext;
    }

    public List<BDLocation> getLocations() {
        return mLocations;
    }

    public void setLocations(List<BDLocation> locations) {
        mLocations = locations;
    }

    public String getAppVersion() {
        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(),0);
            String version = info.versionName;
            return this.getString(R.string.current_version) + version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return this.getString(R.string.version_unknown);
        }
    }

    public String getAppPackageName() {
        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(),0);
            String packageName = info.packageName;
            return packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "com.netease.edu.study";
        }
    }
}
