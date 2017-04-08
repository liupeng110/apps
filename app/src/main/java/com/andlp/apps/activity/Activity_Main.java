package com.andlp.apps.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.andlib.lp.util.L;
import com.andlib.lp.util.SPUtil;
import com.andlp.apps.R;
import com.andlp.apps.adapter.Activity_Main__adapter;
import com.andlp.apps.bean.MyFile;
import com.andlp.apps.config.Constant;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_main)
public class Activity_Main extends Activity_Base {

    @ViewInject(R.id.test)TextView  text;
    @ViewInject(R.id.main_listview) ListView listView;

    Activity_Main__adapter mMain_adapter;
    List<MyFile>mList=new ArrayList<>();
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
        getDatile(Constant.Server+"kf.txt"+Constant.now);
//        loadData();
    }

    private void loadData(){
        x.task().run(new Runnable() {
            @Override public void run() {
                String result ="";
                RequestParams params = new  RequestParams(Constant.fenlei);
                try{
                    result=x.http().getSync(params,String.class);
                    String[] listArray = result.split("\n");
                    SPUtil.put(Activity_Main.this,"size",listArray.length);
                    for (int i = 0; i < listArray.length; i++) {
                        L.i(tag+i+"line:" + listArray[i]);
                    }


                } catch(Throwable t) {
                    t.printStackTrace(); result = "request error!";
                }
                L.i(tag+"reque result:"+result);
            }
        });



    }


    //
    private void testCategory(){
        int size = (int)SPUtil.get(this,"size",0);
        for(int a=0;a<size;a++){
            String result=(String)SPUtil.get(this,a+"","");
            L.i(tag+a+"spu:" + result);
        }

    }


    private void getDatile(final String url){
        x.task().run(new Runnable() {
            @Override public void run() {
                RequestParams params = new  RequestParams(url);
                try{
                 final  String result=x.http().getSync(params,String.class);
                    String[] listArray = result.split("\n");
                    SPUtil.put(Activity_Main.this,"size",listArray.length);
                    for (int i = 0; i < listArray.length; i++) {
                        L.i(tag+i+",line:" + listArray[i]);

                        String[] listArray2 = listArray[i].split(",");
                        L.i(tag+i+",line2:" + listArray2.length);
                        L.i(tag+i+",line2:" + listArray2[0]);
                        L.i(tag+i+",line2:" + listArray2[1]);
                        L.i(tag+i+",line2:" + listArray2[2]);

                        MyFile myFile = new MyFile();
                        myFile.setName(listArray2[0]+".apk?v=23333");
                        myFile.setAppName(listArray2[1]);
                        myFile.setMs(listArray2[2]);
                        mList.add(myFile);
                    }

                    x.task().post(new Runnable() {
                        @Override
                        public void run() {
//                            text.setText(result);
                            mMain_adapter = new Activity_Main__adapter(mContext,mList);
                            listView.setAdapter(mMain_adapter);
                            mMain_adapter.notifyDataSetChanged();// listView.onRefreshComplete();

                        }
                    });

                } catch(Throwable t) {
                    t.printStackTrace(); L.i(tag+"reque result:request error!");
                }

            }
        });
    }



}
