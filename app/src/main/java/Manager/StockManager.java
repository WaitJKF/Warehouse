package Manager;
/*
*
* 创建一个库存表操作类，并继承封装好的接口
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

import DataBaseService.StockService;
import mySqliteHelper.MyDataBase;

/**
 * Created by 93202 on 2018-04-26.
 */

public class StockManager implements StockService {
    private boolean flag;
    private MyDataBase myDataBase;
    private SQLiteDatabase sqLiteDatabase;
    private String sql;
    private Map<String ,String > map;
    private List<Map<String ,String >> list;

    public StockManager(Context context){
        myDataBase = new MyDataBase(context);
    }
    @Override
    public boolean addProduct(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "insert into stocktable (proname,proguige,pronum) values(?,?,?)";
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
            sql = "delete from stocktable where proname=?";
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
            sql = "update stocktable set pronum=? where proname=?";
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
            sql = "select *from stocktable where proname=?";
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
            sql = "select *from stocktable";
            Cursor cursor = sqLiteDatabase.rawQuery(sql,strings);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                map = new HashMap<String, String>();
                for (int i=0; i<colums; i++){
                    String key = cursor.getColumnName(i);
                    String values = cursor.getString(cursor.getColumnIndex(key));
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
