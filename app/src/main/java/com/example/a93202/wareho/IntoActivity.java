package com.example.a93202.wareho;
/*
*
* 入库操作界面
* */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import Manager.CustomerManager;
import Manager.IntoManager;
import Manager.ProductManager;
import Manager.StockManager;
import entity.IntoBase;
import entity.Stock;

public class IntoActivity extends AppCompatActivity {

    private List<Map<String, String>> cList;
    private List<Map<String, String>> pList;
    private EditText into1;
    private EditText into2;
    private EditText into3;
    private EditText into4;
    private EditText into5;
    private EditText into6;
    private Spinner sp1;
    private Spinner sp2;
    private SimpleAdapter cAdpater;
    private SimpleAdapter pAdpater;
    private DatePicker dp;
    private String cGetItem;
    private String pGetItem;
    private String intoTime;

    private CustomerManager cm;
    private ProductManager pm;
    private IntoManager im;
    private StockManager sm;
    private int year;
    private int month;
    private int day;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into);

        sp1 = (Spinner) findViewById(R.id.sp1);
        sp2 = (Spinner) findViewById(R.id.sp2);
        into1 = (EditText) findViewById(R.id.into1);
        into2 = (EditText) findViewById(R.id.into2);
        into3 = (EditText) findViewById(R.id.into3);
        into4 = (EditText) findViewById(R.id.into4);
        into5 = (EditText) findViewById(R.id.into5);
        into6 = (EditText) findViewById(R.id.into6);
        dp = (DatePicker) findViewById(R.id.dp);

        cm = new CustomerManager(this);                 //创建一个新的客户表操作类对象
        pm = new ProductManager(this);                  //创建一个新的商品表操作类对象


        cList = cm.getAll(null);                        //获得客户表的所有信息
        pList = pm.getAll(null);                        //获得商品表的所有信息

        cAdpater = new SimpleAdapter(this,cList,R.layout.list_item,new String[]{"companyname"},new int[]{R.id.list_view});      //创建新的SimpleAdapter对象
        pAdpater = new SimpleAdapter(this,pList,R.layout.list_item,new String[]{"proname"},new int[]{R.id.list_view});


        sp1.setAdapter(cAdpater);                               //为Spinner绑定Adapter
        sp2.setAdapter(pAdpater);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cGetItem = cList.get(i).get("companyname");              //从Spinner中得到点击的信息
                Toast.makeText(IntoActivity.this, cGetItem, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pGetItem = pList.get(i).get("proname");
                Toast.makeText(IntoActivity.this,pGetItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        year = dp.getYear();
        month = dp.getMonth();
        day = dp.getDayOfMonth();
        intoTime = year + "-" + (month+1) + "-" + day;
        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {                           //设置DatePicker的日期信息
                intoTime = dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth();        //得到点击后的日期信息
                Toast.makeText(IntoActivity.this,intoTime,Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void intoOk(View view) {                                              //点击OK键后额点击事件，向入库表中插入新的一项
        String companyname = cGetItem;
        String proname = pGetItem;
        String contacts = into1.getText().toString();
        String proguige = into2.getText().toString();
        String telephone = into3.getText().toString();
        String intoguige = into4.getText().toString();
        String intonum = into5.getText().toString();
        String proprice = into6.getText().toString();

        IntoBase ib = new IntoBase(intoguige, contacts, telephone, intonum, proguige, proprice);    //创建一个入库类对象
        ib.setCompanyname(companyname);
        ib.setProname(proname);
        ib.setIntotime(intoTime);

        im = new IntoManager(this);                                                         //创建一个入库表操作类对象
        boolean flag = im.add(new Object[]{ib.getCompanyname(), ib.getIntoguige(), ib.getContacts(), ib.getTelephone(), ib.getIntonum(), ib.getProname(), ib.getProguige(), ib.getProprice(), ib.getIntotime()});
        sm = new StockManager(this);                                                        //创建一个库存表操作对象
        Stock stock = new Stock(proname,proguige,intonum);
        boolean rtn = sm.addProduct(new Object[]{stock.getProname(),stock.getProguige(),stock.getPronum()});

        if (flag&&rtn) {
            Toast.makeText(IntoActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
            this.finish();
        } else {
            Toast.makeText(IntoActivity.this, "添加失败！", Toast.LENGTH_SHORT).show();
        }

    }
}

