package com.andlp.apps.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.andlp.apps.R;
import com.andlp.apps.config.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 717219917@qq.com  2017/3/1 16:01
 */
@ContentView(R.layout.activity_welcome)
public class Activity_Welcome extends Activity_Base {

   @ViewInject(R.id.welcome_background) private ImageView welcome_background ;
   @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoader.getInstance().displayImage(Constant.Server+Constant.welcome,welcome_background);

    }



}
