package com.andlp.apps;

import android.os.Environment;

import com.andlib.lp.AndApp;
/**
 * 717219917@qq.com  2017/3/1 13:52
 */
public class App extends AndApp {

    public static String SDcard;

    @Override  public void onCreate() {
        super.onCreate();
        initSDcard();
    }

    //获取sdcard状态
  private void initSDcard(){
      SDcard= Environment.getExternalStorageDirectory()+"/andlp/down/";
  }


}
