package entity;

/**
 * Created by 93202 on 2018-04-24.
 *
 *
 * 创建一个出库实体类
 */

public class outBase {
    private String companyname;
    private String proname;
    private String outguige;
    private String contacts;
    private String telephone;
    private String outnum;
    private String proguige;
    private String proprice;
    private String outtime;

    public outBase(String outguige, String contacts, String telephone, String outnum, String proguige, String proprice) {
        this.outguige = outguige;
        this.contacts = contacts;
        this.telephone = telephone;
        this.outnum = outnum;
        this.proguige = proguige;
        this.proprice = proprice;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getProname() {
        return proname;
    }

    public String getOutguige() {
        return outguige;
    }

    public String getContacts() {
        return contacts;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getOutnum() {
        return outnum;
    }

    public String getProguige() {
        return proguige;
    }

    public String getProprice() {
        return proprice;
    }
}
