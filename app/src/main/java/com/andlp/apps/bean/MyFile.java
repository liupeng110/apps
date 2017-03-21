package com.andlp.apps.bean;

/**
 * Created by Administrator on 2017/3/21.
 */
//7牛上的文件bean
public class MyFile {
    private String name;    //7牛文件名
    private String appName ;//app名
    private String ms;      //描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }
}
