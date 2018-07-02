package entity;

/**
 * Created by 93202 on 2018-04-24.
 *
 * 创建一个库存实体类
 */

public class Stock {
    private String proname;
    private String proguige;
    private String pronum;

    public Stock(String proname, String proguige, String pronum) {
        this.proname = proname;
        this.proguige = proguige;
        this.pronum = pronum;
    }

    public String getProname() {
        return proname;
    }

    public String getProguige() {
        return proguige;
    }

    public String getPronum() {
        return pronum;
    }
}
