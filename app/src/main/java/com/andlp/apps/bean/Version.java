package com.andlp.apps.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/** 717219917@qq.com  2017/3/1 17:14 */
@Table(name="Version")
public class Version {

    @Column(name="vercode",isId = true) private String vercode ="0";//依据
    @Column(name="vername") private String vername;//显示
    @Column(name="txt") private String txt;  //文本(尽量不要太长)
    @Column(name="way")private String way;  //0正常(只提示),1.强制更新(wifi)2.强制更新(wifi+3/4g)

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public String getVername() {
        return vername;
    }

    public void setVername(String vername) {
        this.vername = vername;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
