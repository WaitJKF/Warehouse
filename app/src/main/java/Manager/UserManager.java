package Manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataBaseService.UserService;
import mySqliteHelper.MyDataBase;

/**
 * Created by 93202 on 2018-04-12.
 *
 *
 * 创建一个用户表操作类，并继承封装好的接口
 *
 *
 *
 */

public class UserManager implements UserService{
    private MyDataBase myDataBase;
    private SQLiteDatabase sqLiteDatabase;
    private boolean flag = false;
    private Map<String ,String > map;
    private List<Map<String ,String>> list;
    private String sql;

    public UserManager(Context context){
        myDataBase = new MyDataBase(context);
    }

    @Override
    public boolean addUser(String [] strings) {
        sqLiteDatabase = null;
        sql = "";
        /*sqLiteDatabase = myDataBase.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",strings[0]);
        cv.put("password",strings[1]);
        cv.put("num",strings[2]);
        sqLiteDatabase.insert("usertable","name",cv);
        flag = true;*/
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "insert into usertable (username,password,num) values (?,?,?)";
            sqLiteDatabase.execSQL(sql,strings);
            /*ContentValues cv = new ContentValues();
            cv.put("username",strings[0]);
            cv.put("password",strings[1]);
            cv.put("num",strings[2]);
            sqLiteDatabase.insert("usertable","name",cv);*/
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
    public boolean delUser(Object[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            sqLiteDatabase = myDataBase.getWritableDatabase();
            sql = "delete from usertable where username=?";
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
            sql = "update usertable set password=?,num=? where username=?";
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
    public Map<String, String> getOne(String[] name) {
        sqLiteDatabase = null;
        sql = "";
        try {
            map = new HashMap<String ,String >();
            sqLiteDatabase = myDataBase.getReadableDatabase();
            sql = "select *from usertable where username=?";
            Cursor cursor = sqLiteDatabase.rawQuery(sql,name);
            int colums = cursor.getColumnCount();
            while(cursor.moveToNext()){
                for (int i=0; i<colums; i++){
                    String c_name = cursor.getColumnName(i);
                    String c_values = cursor.getString(cursor.getColumnIndex(c_name));
                    if (c_values == null){
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
    public List<Map<String, String>> getAll(String[] objects) {
        sqLiteDatabase = null;
        sql = "";
        try {
            list = new ArrayList<>();
            sqLiteDatabase = myDataBase.getReadableDatabase();
            sql = "selet *from usertable";
            Cursor cursor = sqLiteDatabase.rawQuery(sql,objects);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                for (int i=0; i<colums; i++){
                    String c_name = cursor.getColumnName(i);
                    String c_values = cursor.getString(cursor.getColumnIndex(c_name));
                    if (c_values == null){
                        c_values = "";
                    }
                    map.put(c_name,c_values);
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
