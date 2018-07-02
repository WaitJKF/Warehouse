package entity;

/**
 * Created by 93202 on 2018-04-16.
 *
 * 创建一个商品实体类
 */

public class Product {
    private String proname;
    private String proguige;
    private String proprice;

    public Product(String proname,String proguige,String proprice){
        this.proname = proname;
        this.proguige = proguige;
        this.proprice = proprice;
    };

    public String getProname() {
        return proname;
    }

    public String getProguige() {
        return proguige;
    }

    public String getProprice() {
        return proprice;
    }
}
