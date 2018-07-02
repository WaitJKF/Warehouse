package entity;

/**
 * Created by 93202 on 2018-04-23.
 *
 *
 * 创建一个顾客实体类
 */

public class Customer {
    private String companyname;
    private String address;
    private String contacts;
    private String cityname;
    private String telephone;
    private String companyurl;

    public Customer(String company, String address, String contacts, String cityname, String telephone, String companyurl) {
        this.companyname = company;
        this.address = address;
        this.contacts = contacts;
        this.cityname = cityname;
        this.telephone = telephone;
        this.companyurl = companyurl;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getAddress() {
        return address;
    }

    public String getContacts() {
        return contacts;
    }

    public String getCityname() {
        return cityname;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCompanyurl() {
        return companyurl;
    }
}
