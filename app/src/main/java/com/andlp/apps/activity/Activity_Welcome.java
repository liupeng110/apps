package com.andlp.apps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.andlib.lp.util.JsonUtil;
import com.andlib.lp.util.L;
import com.andlp.apps.MyApp;
import com.andlp.apps.R;
import com.andlp.apps.bean.Version;
import com.andlp.apps.config.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 717219917@qq.com  2017/3/1 16:01
 */
@ContentView(R.layout.activity_welcome)
public class Activity_Welcome extends Activity_Base {

   @ViewInject(R.id.welcome_background) private ImageView welcome_background ;
   @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoader.getInstance().displayImage(Constant.Server+Constant.welcome,welcome_background);
       getVersion();
    }



    private void getVersion(){
           x.task().run(new Runnable() {
               @Override
               public void run() {
                   String result="";
                   Version version =null;
                   RequestParams params = new  RequestParams(Constant.update+Constant.now);
                   try{
                       result=x.http().getSync(params,String.class);
                       version= JsonUtil.parse(result, Version.class);
                       L.i("version-->"+version.getWay());
                       L.i("version-->"+version.getTxt());
                       L.i("version-->"+version.getVercode());
                       L.i("version-->"+version.getVername());
                       MyApp.db.saveOrUpdate(version);//保存最新版本号
                   }catch(Throwable t){ t.printStackTrace();
                       result = "request err!";
                   }
                   L.i("version-->"+result);
                   toMain(); //进行跳转

               }
           });

    }


    private void toMain(){
        x.task().postDelayed(new Runnable() {
            @Override public void run() {
                Intent intent  = new Intent(Activity_Welcome.this,Activity_Main.class);
                startActivity(intent);
                Activity_Welcome.this.finish();
            }
        }, 3000);
    }//延迟



}
