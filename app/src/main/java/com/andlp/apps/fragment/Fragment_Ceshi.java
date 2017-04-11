package com.andlp.apps.fragment;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.andlib.lp.util.L;
import com.andlib.lp.util.SPUtil;
import com.andlp.apps.R;
import com.andlp.apps.adapter.Fragment_down__adapter;
import com.andlp.apps.bean.MyFile;
import com.andlp.apps.config.Constant;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/** 717219917@qq.com  2016/12/14 0:15 */
@ContentView( R.layout.fragment_ceshi)
public class Fragment_Ceshi extends Fragment_Base{
    @ViewInject(R.id.fragment_ceshi_listview) ListView listView;

    Fragment_down__adapter mMain_adapter;
    List<MyFile> mList=new ArrayList<>();
    Context mContext;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();mList.clear();
        getDatile(Constant.Server+"kf.txt"+Constant.now);
    }


    private void getDatile(final String url){
        x.task().run(new Runnable() {
            @Override public void run() {
                RequestParams params = new  RequestParams(url);
                try{
                    final  String result=x.http().getSync(params,String.class);
                    String[] listArray = result.split("\n");
                    SPUtil.put(x.app(),"size",listArray.length);
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
                            mMain_adapter = new Fragment_down__adapter(mContext,mList);
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
