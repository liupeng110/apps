package com.andlp.apps.activity;

import android.app.Activity;
import android.os.Bundle;

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

    }



}
