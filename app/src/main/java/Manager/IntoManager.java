package Manager;
/*
*
* 创建一个入库表操作类，并继承封装好的接口
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

import DataBaseService.IntoBaseService;
import mySqliteHelper.MyDataBase;

/**
 * Created by 93202 on 2018-04-23.
 */

public class IntoManager implements IntoBaseService {
    private boolean flag;

    private List<Map<String, String>> list;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private String sql;
    private MyDataBase myDataBase;


    public IntoManager(Context context){
        myDataBase = new MyDataBase(context);
    }

    @Override
    public boolean add(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
            try {
                sql = "insert into intotable (companyname,intoguige,contacts,telephone,intonum,proname,proguige,proprice,intotime) values (?,?,?,?,?,?,?,?,?)";
                sqLiteDatabase = myDataBase.getWritableDatabase();
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
    public boolean del(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            sql = "delete from intotable where proname=?";
            sqLiteDatabase = myDataBase.getWritableDatabase();
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
            sql = "update intotable set intoguige=?,contacts=?,telephone=?,intonum=?,proname=?,proguige=?,proprice=?,intotime=? where companyname=?";
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
    public List<Map<String, String>> getAll(String[] strings) {
        sqLiteDatabase = null;
        sql = "";
        try {
            list = new ArrayList<Map<String ,String >>();
            sql = "select *from intotable";
            sqLiteDatabase = myDataBase.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(sql, strings);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                Map<String, String > map = new HashMap<String, String>();
                for (int i =0; i<colums; i++){
                    String key = cursor.getColumnName(i);
                    String value = cursor.getString(cursor.getColumnIndex(key));
                    if (value == null){
                        value = "";
                    }
                    map.put(key,value);
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
