package entity;

/**
 * Created by 93202 on 2018-04-12.
 *
 *
 * 创建一个用户实体类
 */

public class User {
    private String name;
    private String psw;
    private String num;
    public User(String name, String psw, String num){
        this.name = name;
        this.psw = psw;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getPsw() {
        return psw;
    }

    public String getNum() {
        return num;
    }
}
