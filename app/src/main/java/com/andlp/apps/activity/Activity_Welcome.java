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
    String  result="";
    int newVersion =0,oldVersion =0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoader.getInstance().displayImage("assets://"+Constant.welcome,welcome_background);
        getVersion();
    }
    //1.
    private void getVersion(){
        x.task().run(new Runnable() {
            @Override public void run() {
//                RequestParams params = new  RequestParams(Constant.update+Constant.now);
                RequestParams params = new  RequestParams(Constant.test+Constant.now);

                try{
                    result=x.http().getSync(params,String.class);
//                    version= JsonUtil.parse(result,Version.class);
//                    L.i("version-->"+version.getWay());
//                    L.i("version-->"+version.getTxt());
//                    L.i("version-->"+version.getVercode());
//                    L.i("version-->"+version.getVername());
//                    MyApp.db.saveOrUpdate(version);//save version
                    String[] listArray = result.split("\n");//
                    for (int i = 0; i < listArray.length; i++) {
                        L.i(i + "line:" + listArray[i]);
                    }
                }catch(Throwable t){ t.printStackTrace();
                    result = "request error!";
                }
                L.i("version--> reque result:"+result);
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
                    if (newVersion>oldVersion){
                        L.i("version----need update->new:"+newVersion+",old:"+oldVersion);
                    }else{
                        L.i("version----No need update->new:"+newVersion+",old:"+oldVersion);
                    }
                }catch(Throwable t){
                    t.printStackTrace();
                }
                L.i("version----Last>new:"+newVersion+",old:"+oldVersion);
                toMain(); //进行跳转
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
        }, 3000);
    }



}
