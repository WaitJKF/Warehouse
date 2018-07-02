package mySqliteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 93202 on 2018-04-12.
 *
 * 创建一个类，继承SQLiteOpenHelper类
 */

public class MyDataBase extends SQLiteOpenHelper {
    static final String DATEBASE_NAME = "warehous.db";
    static final int DATEBASE_VERSION = 1;

    String usertable = "create table usertable(" +                      //用户表
            "id integer auto_increment primary key," +
            "username char(20)," +
            "password char(20)," +
            "num char(20)" +
            ")";
    String producttable = "create table producttable(" +                //商品表
            "id integer auto_increment primary key," +
            "proname char(20)," +
            "proguige char(20)," +
            "proprice char(20)" +
            ")";
    String customertable = "create table customertable(" +              //顾客表
            "id integer auto_increment primary key," +
            "companyname char(20)," +
            "address char(20)," +
            "cityname char(20)," +
            "contacts char(20)," +
            "telephone char(20)," +
            "companyurl char(20)" +
            ")";
    String supplier = "create table supplier(" +                         //供应商表
            "id integer auto_increment primary key," +
            "suppliername char(20)," +
            "address char(20)," +
            "cityname char(20)," +
            "contacts char(20)," +
            "telephone char(20)," +
            "companyurl char(20)" +
            ")";
    String intotable = "create table intotable(" +                         //进货表
            "id integer auto_increment primary key," +
            "companyname char(20)," +
            "intoguige char(20)," +                                   //进货单位
            "contacts char(20)," +
            "telephone char(20)," +
            "intonum char(20)," +                                      //进货数量
            "proname char(20)," +
            "proguige char(20)," +
            "proprice char(20)," +
            "intotime char(20)"+
            ")";
    String outtable = "create table outtable(" +                         //出货表
            "id integer auto_increment primary key," +
            "companyname char(20)," +
            "outguige char(20)," +
            "contacts char(20)," +
            "telephone char(20)," +
            "outnum char(20)," +
            "proname char(20)," +
            "proguige char(20)," +
            "proprice char(20)," +
            "outtime char(20)"+
            ")";
    String stocktable = "create table stocktable(" +                    //库存表
            "_id int auto_increment," +
            "proname char(20)," +
            "proguige char(20)," +
            "pronum char(20)," +
            "primary key('_id')" +
            ")";

    public MyDataBase(Context context) {
        super(context, DATEBASE_NAME, null, DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(usertable);
        sqLiteDatabase.execSQL(producttable);
        sqLiteDatabase.execSQL(customertable);
        sqLiteDatabase.execSQL(supplier);
        sqLiteDatabase.execSQL(intotable);
        sqLiteDatabase.execSQL(outtable);
        sqLiteDatabase.execSQL(stocktable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
