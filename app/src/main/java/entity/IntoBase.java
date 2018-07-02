package entity;

/**
 * Created by 93202 on 2018-04-23.
 *
 * 创建一个入库实体类
 */

public class IntoBase {

    private String companyname;
    private String proname;
    private String intoguige;
    private String contacts;
    private String telephone;
    private String intonum;
    private String proguige;
    private String proprice;
    private String intotime;

    public IntoBase(String intoguige, String contacts, String telephone, String intonum, String proguige, String proprice) {
        this.intoguige = intoguige;
        this.contacts = contacts;
        this.telephone = telephone;
        this.intonum = intonum;
        this.proguige = proguige;
        this.proprice = proprice;
    }


    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getIntotime() {
        return intotime;
    }

    public void setIntotime(String intotime) {
        this.intotime = intotime;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getProname() {
        return proname;
    }

    public String getIntoguige() {
        return intoguige;
    }

    public String getContacts() {
        return contacts;
    }


    public String getTelephone() {
        return telephone;
    }


    public String getIntonum() {
        return intonum;
    }


    public String getProguige() {
        return proguige;
    }



    public String getProprice() {
        return proprice;
    }


}
