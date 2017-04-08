package com.andlp.apps.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.andlp.apps.MyApp;

import org.xutils.x;

/**
 * 717219917@qq.com  2017/3/1 15:53
 */

public class Activity_Base extends FragmentActivity {
    String tag ="Activity_Base";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hidTitlebar();
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        MyApp.addActivity(this);

    }

   private void hidTitlebar(){
       getWindow().requestFeature(Window.FEATURE_NO_TITLE);
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           Window window = getWindow();
           window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                   | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
           window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                   | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
           window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
           window.setStatusBarColor(Color.TRANSPARENT);
           window.setNavigationBarColor(Color.TRANSPARENT);
       }
   }

    private void start(Activity activity){
        Intent intent2=new  Intent();
        intent2.setClassName(this,activity.getLocalClassName());
        startActivity(intent2);
    }


}
