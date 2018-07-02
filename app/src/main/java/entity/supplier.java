package entity;

/**
 * Created by 93202 on 2018-04-24.
 *
 *
 * 创建一个供应商实体类
 */

public class supplier {
    private String suppliername;
    private String address;
    private String cityname;
    private String contacts;
    private String telephone;
    private String companyurl;

    public supplier(String suppliername, String address, String cityname, String contacts, String telephone, String companyurl) {
        this.suppliername = suppliername;
        this.address = address;
        this.cityname = cityname;
        this.contacts = contacts;
        this.telephone = telephone;
        this.companyurl = companyurl;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public String getAddress() {
        return address;
    }

    public String getCityname() {
        return cityname;
    }

    public String getContacts() {
        return contacts;
    }

    public String getTelephone() {
        return telephone;
    }


    public String getCompanyurl() {
        return companyurl;
    }
}
