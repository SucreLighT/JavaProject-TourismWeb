package cn.sucre.domain;

/**
 * @description:商家实体类
 * @author: sucre
 * @date: 2020/08/02
 * @time: 15:52
 */
public class Seller {
    private int sid;
    private String sname;
    private String consphone;
    private String address;

    /**
     * 无参构造方法
     */
    public Seller(){}

    public Seller(int sid, String sname, String consphone, String address) {
        this.sid = sid;
        this.sname = sname;
        this.consphone = consphone;
        this.address = address;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getConsphone() {
        return consphone;
    }

    public void setConsphone(String consphone) {
        this.consphone = consphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
