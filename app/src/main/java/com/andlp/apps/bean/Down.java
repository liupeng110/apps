package com.andlp.apps.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 717219917@qq.com  2017/3/1 16:34
 */
@Table(name="Down")
public class Down {

    @Column(name="name",isId = true) private String name;  //文件名,唯一
    @Column(name="index") private String index;            //当前下载进度
    @Column(name="size")private String size;               //总大小
    @Column(name="path")private String path;               //保存路径
    @Column(name="success") private boolean success =false;//是否下载成功

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
