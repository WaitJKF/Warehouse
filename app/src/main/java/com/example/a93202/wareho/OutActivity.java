package com.example.a93202.wareho;
/*
*
* 出库操作界面
*
* */
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
import java.util.List;
import java.util.Map;

import Manager.CustomerManager;
import Manager.OutManager;
import Manager.ProductManager;
import Manager.StockManager;
import entity.outBase;

public class OutActivity extends AppCompatActivity {

    private List<Map<String, String>> cList;
    private List<Map<String, String>> pList;
    private List<Map<String, String>> getList;
    private EditText out1;
    private EditText out2;
    private EditText out3;
    private EditText out4;
    private EditText out5;
    private EditText out6;
    private Spinner sp1;
    private Spinner sp2;
    private SimpleAdapter cAdpater;
    private SimpleAdapter pAdpater;
    private DatePicker dp;
    private String cGetItem;
    private String pGetItem;
    private String outTime;

    private CustomerManager cm;
    private ProductManager pm;
    private OutManager om;
    private StockManager sm;

    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);

        sp1 = (Spinner) findViewById(R.id.sp1);
        sp2 = (Spinner) findViewById(R.id.sp2);
        out1 = (EditText) findViewById(R.id.into1);
        out2 = (EditText) findViewById(R.id.into2);
        out3 = (EditText) findViewById(R.id.into3);
        out4 = (EditText) findViewById(R.id.into4);
        out5 = (EditText) findViewById(R.id.into5);
        out6 = (EditText) findViewById(R.id.into6);
        dp = (DatePicker)findViewById(R.id.dp);

        cm = new CustomerManager(this);
        pm = new ProductManager(this);

        cList = cm.getAll(null);
        pList = pm.getAll(null);

        cAdpater = new SimpleAdapter(this,cList,R.layout.list_item,new String[]{"companyname"},new int[]{R.id.list_view});
        pAdpater = new SimpleAdapter(this,pList,R.layout.list_item,new String[]{"proname"},new int[]{R.id.list_view});

        sp1.setAdapter(cAdpater);
        sp2.setAdapter(pAdpater);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cGetItem = cList.get(i).get("companyname");
                Toast.makeText(OutActivity.this, cGetItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pGetItem = pList.get(i).get("proname");
                Toast.makeText(OutActivity.this,pGetItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        year = dp.getYear();
        month = dp.getMonth();
        day = dp.getDayOfMonth();
        outTime = year + "-" + (month+1) + "-" + day;
        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                outTime = dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth();
                Toast.makeText(OutActivity.this,outTime,Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void toOut(View view) {                                      //响应点击事件
        String companyname = cGetItem;
        String proname = pGetItem;
        String contacts = out1.getText().toString();
        String proguige = out2.getText().toString();
        String telephone = out3.getText().toString();
        String outguige = out4.getText().toString();
        String outnum = out5.getText().toString();
        String proprice = out6.getText().toString();

        outBase ob = new outBase(outguige, contacts, telephone, outnum, proguige, proprice);
        ob.setCompanyname(companyname);
        ob.setProname(proname);
        ob.setOuttime(outTime);
        sm = new StockManager(this);
        Map<String,String> getMap = sm.getOne(new String[]{proname});
        if (getMap == null){                                                        //判断剩余的库存中是否存在当前出库商品
            Toast.makeText(OutActivity.this,"出库失败！库存中已没有该商品！",Toast.LENGTH_SHORT).show();
        }else {
            om = new OutManager(OutActivity.this);                         //如果存在，就进行出库操作，并对库存表进行更新操作
            boolean flag = om.add(new Object[]{ob.getCompanyname(), ob.getOutguige(), ob.getContacts(), ob.getTelephone(), ob.getOutnum(), ob.getProname(), ob.getProguige(), ob.getProprice(),ob.getOuttime()});

            String num = getMap.get("pronum");
            int less = Integer.valueOf(num)-Integer.valueOf(outnum);
            if (less<=0){                                                           //如果进行出库操作后，库存已无剩余，则从库存表中删除此项
                sm.delProduct(new Object[]{proname});
            }else {
                sm.update(new Object[]{String.valueOf(less),proname});
            }
            if (flag) {
                Toast.makeText(OutActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                Toast.makeText(OutActivity.this, "添加失败！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
