package com.andlp.apps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.andlib.lp.util.AppUtil;
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

/** 717219917@qq.com  2017/3/1 16:01 */
@ContentView(R.layout.activity_welcome)
public class Activity_Welcome extends Activity_Base {
    @ViewInject(R.id.welcome_background) private ImageView welcome_background ;

    Version version =null;
    String  result="",tag="Activity_Welcome";
    int newVersion =0,oldVersion =0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ImageLoader.getInstance().displayImage("assets://"+Constant.welcome,welcome_background);
        getVersion();
    }
    //1.
    private void getVersion(){
        x.task().run(new Runnable() {
            @Override public void run() {
//                RequestParams params = new  RequestParams(Constant.update);
                RequestParams params = new  RequestParams(Constant.kaifa);
                try{
                    result=x.http().getSync(params,String.class);
                    version= JsonUtil.parse(result,Version.class);
                    MyApp.db.saveOrUpdate(version);//save version
                }catch(Throwable t){
                    t.printStackTrace();
                }
                compareVersion();
            }
        });

    }
    //2.
    private void compareVersion(){
        x.task().run(new Runnable() {
            @Override public void run() {
                try{
                    Version db_Version = MyApp.db.selector(Version.class).findFirst();
                    newVersion =Integer.parseInt(db_Version.getVercode());
                    oldVersion = AppUtil.getVersionCode(Activity_Welcome.this);
                    if (newVersion>oldVersion){  update(); return;}
                }catch(Throwable t){
                    t.printStackTrace();
                }
                toMain(); //直接延迟
            }
        });
    }
    //3.
    private void toMain(){
        x.task().postDelayed(new Runnable() {
            @Override public void run() {
                Intent intent  = new Intent(Activity_Welcome.this,Activity_Main.class);
                startActivity(intent);
                Activity_Welcome.this.finish();
            }
        }, 2000);
    }

    private void update (){
        L.i(tag+"update->new:"+newVersion+",old:"+oldVersion);
        toMain();
    }

}
