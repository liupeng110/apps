package com.andlp.apps.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.andlp.apps.App;

import org.xutils.x;

/**
 * 717219917@qq.com  2017/3/1 15:53
 */

public class Activity_Base extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        App.addActivity(this);
        hidTitlebar();
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

}
