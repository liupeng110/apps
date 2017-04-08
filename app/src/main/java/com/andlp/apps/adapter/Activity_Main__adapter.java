package com.andlp.apps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andlib.lp.util.L;
import com.andlp.apps.R;
import com.andlp.apps.bean.MyFile;
import com.andlp.apps.config.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class Activity_Main__adapter extends BaseAdapter{
    List<MyFile> mList;
    Context mContext;

    public Activity_Main__adapter() { }

    public Activity_Main__adapter(Context context, List<MyFile>list){
        L.i("适配器 构造函数:" +list.size());
        mContext=context;
        mList =list;
    }

    @Override
    public int getCount() {
        return mList!=null? mList.size():0;
    }

    @Override
    public MyFile getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        L.i("适配器 getView:" +position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_main_item,parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MyFile mMyFile =  getItem(position);
        // 绑定处理数据
        holder.text.setText(mMyFile.getAppName());
        holder.txt_ms.setText(mMyFile.getMs());

        final Button down = holder.btn;
        down.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                RequestParams params= new RequestParams(Constant.Server+mMyFile.getName());
                params.setSaveFilePath(Constant.path_down+mMyFile.getName());
                down(params,down);
            }
        });
        return convertView;
    }

    static class ViewHolder {
       ViewHolder(View view){ x.view().inject(this,view); }//构造函数中注解view
        @ViewInject(R.id.txt) TextView text;
        @ViewInject(R.id.txt_ms)  TextView txt_ms;
        @ViewInject(R.id.img) ImageView icon;
        @ViewInject(R.id.btn)  Button btn;
    }


    //执行下载操作
    private void down(RequestParams params,final Button btn){
            x.http().get(params, new Callback.ProgressCallback<File>() {
                @Override public void onStarted() { btn.setText("started");   }
                @Override public void onSuccess(File file) { btn.setText("succ"); }
                @Override public void onLoading(long all, long curr, boolean b) {
                    btn.setText((int)(curr/all)*100+"%");
                }
                @Override public void onError(Throwable throwable, boolean b) {  btn.setText("err");  }
                @Override public void onCancelled(CancelledException e) {  btn.setText("cancel");  }
                @Override public void onFinished() {  }
                @Override public void onWaiting() { }
            });

    }

}
