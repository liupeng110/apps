package com.andlp.apps;

import com.andlib.lp.util.L;
import com.andlib.lp.util.SPUtil;
import com.andlp.apps.activity.Activity_Main;
import com.andlp.apps.config.Constant;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**  717219917@qq.com  2017/3/1 15:35 */
public class plan {
    //7niu 分类()

    //模块划分
    //1.下载,更新
    //2.ui
    //2.1 欢迎页ui
    //2.2 主页ui
    //2.3 模块详情ui------->icon+txt
    //2.4
    //3.反馈?
    //4.

    //----2.0
    //分页
    //搜索
    //我
    //反馈

    //分类  分类.txt    /br换行,扩展多个分类
    //子类   开发.txt(kf)   常用.txt(cy)    精简.txt(jj)  创意.txt(cy)
    //re(.apk),icon(.png),txt,txt /br      直接一个list,单item为 Icon+Txt+Txt+Button
    //

    //3.category动态获取分类
//    private void getCategory(){
//        x.task().run(new Runnable() {
//            @Override public void run() {
//                String result ="";
//                RequestParams params = new  RequestParams(Constant.fenlei);
//                try{
//                    result=x.http().getSync(params,String.class);
//                    String[] listArray = result.split("\n");
//                    SPUtil.put(Activity_Main.this,"size",listArray.length);
//                    for (int i = 0; i < listArray.length; i++) {
//                        L.i(tag+i+"line:" + listArray[i]);
//                        getCategory_2(listArray[i]);
//                        SPUtil.put(Activity_Main.this,i+"",listArray[i]);//save &read  Decoupling
//                    }
//                } catch(Throwable t) {
//                    t.printStackTrace(); result = "request error!";
//                }
//                L.i(tag+"reque result:"+result);
//            }
//        });
//    }
//    //4.category detail
//    private void getCategory_2(final String name){
//        x.task().run(new Runnable() {
//            @Override public void run() {
//                String result ="";
//                RequestParams params = new  RequestParams(Constant.Server+name+Constant.now);
//                try{
//                    result=x.http().getSync(params,String.class);
//                    String[] listArray = result.split("\n");
//                    for (int i = 0; i < listArray.length; i++)
//                    {
//                        L.i(tag+i+"getCategory_2:" + listArray[i]);
//                    }
//                } catch(Throwable t) {
//                    t.printStackTrace();result = "getCategory_2 request error!";
//                }
//                L.i(tag+"getCategory_2 result:"+result);
//            }
//        });
//    }


}
