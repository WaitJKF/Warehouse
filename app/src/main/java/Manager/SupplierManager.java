package Manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataBaseService.SupplierService;
import mySqliteHelper.MyDataBase;

/**
 * Created by 93202 on 2018-04-26.
 *
 * /*
 *
 * 创建一个供应商表操作类，并继承封装好的接口
 *
 *
 */

public class SupplierManager implements SupplierService {
    private boolean flag;
    private MyDataBase myDataBase;
    private String sql;
    private SQLiteDatabase sqLiteDatabase;
    private List<Map<String,String>> list;
    private Map<String,String> map;

    public SupplierManager(Context context){
        myDataBase = new MyDataBase(context);
    }

    @Override
    public boolean add(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "insert into supplier (suppliername,address,cityname,contacts,telephone,companyurl) values(?,?,?,?,?,?)";
            sqLiteDatabase.execSQL(sql,objects);
            flag= true;

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
    public boolean del(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "delete from supplier where suppliername=?";
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
            sql = "update supplier set address=?,cityname=?,contacts=?,telephone=?,companyurl=? where suppliername=?";
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
        map = new HashMap<String, String>();
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getReadableDatabase();
            sql = "select *form supplier where suppliername=?";
            Cursor cursor = sqLiteDatabase.rawQuery(sql,strings);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                for (int i=0; i<colums; i++){
                    String key = cursor.getColumnName(i);
                    String values = cursor.getString(cursor.getColumnIndex(key));
                    if (values==null){
                        values = "";
                    }
                    map.put(key,values);
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
            sql = "select *from supplier";
            Cursor cursor = sqLiteDatabase.rawQuery(sql,strings);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                map = new HashMap<String, String>();
                for (int i=0; i<colums; i++){
                    String key = cursor.getColumnName(i);
                    String values = cursor.getString(cursor.getColumnIndex(key));
                    if (values==null){
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
