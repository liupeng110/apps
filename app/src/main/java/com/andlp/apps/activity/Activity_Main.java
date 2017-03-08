package com.andlp.apps.activity;

import android.os.Bundle;

import com.andlib.lp.util.L;
import com.andlib.lp.util.SPUtil;
import com.andlp.apps.R;
import com.andlp.apps.config.Constant;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class Activity_Main extends Activity_Base {
             String tag ="Activity_Main--";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          initView();
    }

    //1.
    private void initView(){
        initData();
    }
    //2.
    private void initData(){
        getCategory();
    }

    //3.category
    private void getCategory(){
        x.task().run(new Runnable() {
            @Override public void run() {
                String result ="";
                RequestParams params = new  RequestParams(Constant.test);
               try{
                    result=x.http().getSync(params,String.class);
                    String[] listArray = result.split("\n");
                    SPUtil.put(Activity_Main.this,"size",listArray.length);
                    for (int i = 0; i < listArray.length; i++) {
                        L.i(tag+i+"line:" + listArray[i]);
                        getCategory_2(listArray[i]);
                        SPUtil.put(Activity_Main.this,i+"",listArray[i]);//save &read  Decoupling
                    }
                } catch(Throwable t) {
                    t.printStackTrace(); result = "request error!";
                }
                L.i(tag+"reque result:"+result);
            }
        });
    }
    //4.category detail
    private void getCategory_2(final String name){
        x.task().run(new Runnable() {
            @Override public void run() {
                String result ="";
                RequestParams params = new  RequestParams(Constant.Server+name+Constant.now);
                try{
                    result=x.http().getSync(params,String.class);
                    String[] listArray = result.split("\n");
                    for (int i = 0; i < listArray.length; i++)
                    {
                        L.i(tag+i+"getCategory_2:" + listArray[i]);
                    }
                } catch(Throwable t) {
                    t.printStackTrace();result = "getCategory_2 request error!";
                }
                L.i(tag+"getCategory_2 result:"+result);
            }
        });
    }
    //
    private void testCategory(){
        int size = (int)SPUtil.get(this,"size",0);
        for(int a=0;a<size;a++){
            String result=(String)SPUtil.get(this,a+"","");
            L.i(tag+a+"spu:" + result);
            getCategory_2(result);
        }
    }

}
