package com.example.a93202.wareho;
/*
* 添加顾客
*
* */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Manager.CustomerManager;
import entity.Customer;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText companyname;
    private EditText address;
    private EditText cityname;
    private EditText contacts;
    private EditText telephone;
    private EditText companyurl;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        companyname = (EditText) findViewById(R.id.companyname);
        address = (EditText) findViewById(R.id.address);
        cityname = (EditText) findViewById(R.id.cityname);
        contacts = (EditText) findViewById(R.id.contacts);
        telephone = (EditText) findViewById(R.id.telephone);
        companyurl = (EditText) findViewById(R.id.companyurl);

        bundle = this.getIntent().getExtras();
        if (bundle != null){                                                                //判断是否从修改页面跳转而来
            int flag = bundle.getInt("flag");
            List<Map<String,String >> list =(ArrayList<Map<String,String>>) bundle.getSerializable("list");
            companyname.setText(list.get(flag).get("companyname"));
            address.setText(list.get(flag).get("address"));
            cityname.setText(list.get(flag).get("cityname"));
            contacts.setText(list.get(flag).get("contacts"));
            telephone.setText(list.get(flag).get("telephone"));
            companyurl.setText(list.get(flag).get("companyurl"));
        }
    }

    public void toAdd(View view) {
        CustomerManager cm = new CustomerManager(AddCustomerActivity.this);         //创建一个客户表操作类的对象
        if (bundle != null){                                                                //如果是从修改页面跳转，则进行更新操作
            boolean rtn = cm.update(new Object[]{address.getText().toString(),cityname.getText().toString(),contacts.getText().toString(),
                    telephone.getText().toString(),companyurl.getText().toString(),companyname.getText().toString()});
            String message = (rtn)? "更新成功！":"更新失败！";                               //判断是否更新成功
            Toast.makeText(AddCustomerActivity.this,message,Toast.LENGTH_SHORT).show();
            finish();                                                                        //关闭界面
        }else {                                                                             //添加新的一项
            Customer customer = new Customer(companyname.getText().toString(),address.getText().toString(),contacts.getText().toString(),          //创建一个客户类对象
                    cityname.getText().toString(),telephone.getText().toString(),companyurl.getText().toString());
            if (customer.getCompanyname().equals("")||customer.getAddress().equals("")||customer.getCityname().equals("")||customer.getContacts().equals("")||customer.getTelephone().equals("")||customer.getCompanyurl().equals("")){
                Toast.makeText(AddCustomerActivity.this,"请输入全部信息！",Toast.LENGTH_SHORT).show();
            }else {
                boolean rtn = cm.add(new Object[]{customer.getCompanyname(),customer.getAddress(),customer.getCityname(),customer.getContacts(),customer.getTelephone(),customer.getCompanyurl()});
                if (rtn){
                    Toast.makeText(AddCustomerActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                    finish();                                                                 //关闭界面

                }else {
                    Toast.makeText(AddCustomerActivity.this,"添加失败！",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
