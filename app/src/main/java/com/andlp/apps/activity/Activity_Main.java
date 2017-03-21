package com.andlp.apps.activity;

import android.content.Context;
import android.os.Bundle;

import com.andlib.lp.util.L;
import com.andlib.lp.util.SPUtil;
import com.andlp.apps.R;
import com.andlp.apps.adapter.Activity_Main__adapter;
import com.andlp.apps.bean.MyFile;
import com.andlp.apps.config.Constant;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.List;


@ContentView(R.layout.activity_main)
public class Activity_Main extends Activity_Base {
             String tag ="Activity_Main";

    Activity_Main__adapter mMain_adapter;
    List<MyFile>mList=null;
    Context mContext;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;

    }

    @Override protected void onResume() {
        super.onResume();
        initView();
    }

    //1.
    private void initView(){
        initData();
    }
    //2.
    private void initData(){

    }

    private void loadData(){

        x.task().run(new Runnable() {
            @Override public void run() {
                String result ="";
                RequestParams params = new  RequestParams(Constant.fenlei);
                try{
                    result=x.http().getSync(params,String.class);
                      
//                    String[] listArray = result.split("\n");
//                    SPUtil.put(Activity_Main.this,"size",listArray.length);
//                    for (int i = 0; i < listArray.length; i++) {
//                        L.i(tag+i+"line:" + listArray[i]);
////                        getCategory_2(listArray[i]);
//                        SPUtil.put(Activity_Main.this,i+"",listArray[i]);//save &read  Decoupling
//                    }
                } catch(Throwable t) {
                    t.printStackTrace(); result = "request error!";
                }
                L.i(tag+"reque result:"+result);
            }
        });


        mMain_adapter = new Activity_Main__adapter(mContext,mList);
//        mListview.setAdapter(mMain_adapter);
//        mMain_adapter.notifyDataSetChanged();
//        mListview.onRefreshComplete();//

    }


    //
    private void testCategory(){
        int size = (int)SPUtil.get(this,"size",0);
        for(int a=0;a<size;a++){
            String result=(String)SPUtil.get(this,a+"","");
            L.i(tag+a+"spu:" + result);
        }
    }

}
