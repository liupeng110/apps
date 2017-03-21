package com.andlp.apps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andlp.apps.R;
import com.andlp.apps.bean.MyFile;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class Activity_Main__adapter extends BaseAdapter{
    List<MyFile> mList;
    Context mContext;

    public Activity_Main__adapter() {

    }

    public Activity_Main__adapter(Context context, List<MyFile>list){
        mContext=context;
        mList =list;
    }

    @Override
    public int getCount() {
        return mList!=null?0:mList.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_main_item,parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.txt);
            holder.icon = (ImageView) convertView.findViewById(R.id.img);
            holder.btn = (Button) convertView.findViewById(R.id.btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MyFile mMyFile =  mList.get(position);

        // 绑定处理数据
        holder.text.setText(mMyFile.getAppName());
        return convertView;
    }

    static class ViewHolder {
        TextView text;
        ImageView icon;
         Button btn;
    }

}
