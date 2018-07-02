package Manager;
/*
*
* 创建一个商品表操作类，并继承封装好的接口
*
* */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataBaseService.ProductService;
import mySqliteHelper.MyDataBase;

/**
 * Created by 93202 on 2018-04-16.
 */

public class ProductManager implements ProductService {
    private boolean flag;
    private MyDataBase myDataBase;
    private SQLiteDatabase sqLiteDatabase;
    private String sql;
    private Map<String ,String > map;
    private List<Map<String ,String >> list;

    public ProductManager(Context context){
        myDataBase = new MyDataBase(context);
    }

    @Override
    public boolean addProduct(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "insert into producttable (proname,proguige,proprice) values(?,?,?)";
            sqLiteDatabase.execSQL(sql,objects);
            flag = true;

        }catch (Exception e){
            Log.e("Error",Log.getStackTraceString(e));
        }finally {
            if (sqLiteDatabase != null){
                sqLiteDatabase.close();
            }
        }
        return flag;
    }

    @Override
    public boolean delProduct(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "delete from producttable where proname=?";
            sqLiteDatabase.execSQL(sql,objects);
            flag = true;

        }catch (Exception e){
            Log.e("Error",Log.getStackTraceString(e));
        }finally {
            if (sqLiteDatabase != null){
                sqLiteDatabase.close();
            }
        }
        return flag;
    }

    @Override
    public boolean update(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "update producttable set proguige=?,proprice=? where proname=?";
            sqLiteDatabase.execSQL(sql,objects);
            flag = true;

        }catch (Exception e){
            Log.e("Error",Log.getStackTraceString(e));
        }finally {
            if (sqLiteDatabase != null){
                sqLiteDatabase.close();
            }
        }
        return flag;
    }

    @Override
    public Map<String, String> getOne(String[] strings) {
        sqLiteDatabase = null;
        sql = "";
        map = new HashMap<String, String>();
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "select *from producttable where proname=?";
            Cursor cursor = sqLiteDatabase.rawQuery(sql,strings);
            int colums = cursor.getColumnCount();
            while(cursor.moveToNext()){
                for (int i=0; i<colums; i++){
                    String c_name = cursor.getColumnName(i);
                    String c_values = cursor.getString(cursor.getColumnIndex(c_name));
                    if (c_values==null){
                        c_values = "";
                    }
                    map.put(c_name,c_values);
                }
            }

        }catch (Exception e){
            Log.e("Error",Log.getStackTraceString(e));
        }finally {
            if (sqLiteDatabase != null){
                sqLiteDatabase.close();
            }
        }
        return map;
    }

    @Override
    public List<Map<String, String>> getAll(String[] strings) {
        list = new ArrayList<Map<String, String>>();
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getReadableDatabase();
            sql = "select *from producttable";
            Cursor cursor = sqLiteDatabase.rawQuery(sql,strings);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                map = new HashMap<String, String>();
                for (int i=0; i<colums; i++){
                    String key = cursor.getColumnName(i);
                    String values = cursor.getString(cursor.getColumnIndex(key));
                    if ( values==null){
                        values = "";
                    }
                    map.put(key,values);
                }
                list.add(map);
            }


        }catch (Exception e){
            Log.e("Error",Log.getStackTraceString(e));
        }finally {
            if (sqLiteDatabase != null){
                sqLiteDatabase.close();
            }
        }
        return list;
    }

}

/*
        sqLiteDatabase = null;
        sql = "";
        try {

        }catch (Exception e){
        e.printStackTrace();
        }finally {
        if (sqLiteDatabase != null){
        sqLiteDatabase.close();
        }
        }*/
