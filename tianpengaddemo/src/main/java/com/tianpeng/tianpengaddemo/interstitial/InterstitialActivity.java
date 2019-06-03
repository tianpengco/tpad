package com.tianpeng.tianpengaddemo.interstitial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tianpeng.tianpengaddemo.R;
import com.tianpeng.tp_adsdk.tpadmobsdk.ad.insert.ADMobGenInsertView;
import com.tianpeng.tp_adsdk.tpadmobsdk.ad.listener.ADMobGenInsertitailAdListener;

/**
 * Created by YuHong on 2019/1/25 0025.
 */
public class InterstitialActivity extends AppCompatActivity {
    private ADMobGenInsertView adMobGenInsertView;
    private static final String TAG = "ADMob_Log";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

    }

    public void getAd(View v){
        showProgressDialog();
        adMobGenInsertView = new ADMobGenInsertView(InterstitialActivity.this);
        adMobGenInsertView.setListener(new ADMobGenInsertitailAdListener() {
            @Override
            public void onADExposure() {
                Log.e(TAG, "广告展示曝光回调，但不一定是曝光成功了，比如一些网络问题导致上报失败 ::::: ");
            }

            @Override
            public void onADOpen() {
                Log.e(TAG, "广告打开成功了 ::::: ");
            }

            @Override
            public void onADLeftApplication() {
                Log.e(TAG, "广告onADLeftApplication ::::: ");
            }

            @Override
            public void onADFailed(String s) {
                dismissProgressDialog();
                Log.e(TAG, "广告获取失败了 ::::: " + s);
            }

            @Override
            public void onADReceiv() {
                dismissProgressDialog();
                Log.e(TAG, "广告获取成功了 ::::: ");
            }

            @Override
            public void onADClick() {
                Log.e(TAG, "广告被点击了 ::::: ");
            }

            @Override
            public void onAdClose() {
                Log.e(TAG, "广告被关闭了，改回调不一定会有 ::::: ");
            }
        });
        adMobGenInsertView.loadAd();
    }
    public void showAd(View v){
        adMobGenInsertView.show();
    }


    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在...");
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }
    @Override
    protected void onDestroy() {
        // 释放广告资源
        if (adMobGenInsertView != null) {
            adMobGenInsertView.destroy();
        }
        super.onDestroy();
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, InterstitialActivity.class));
    }
}
