package com.example.a93202.wareho;

/*
* 添加供应商信息界面
* */

import android.content.Intent;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Manager.SupplierManager;
import entity.supplier;

public class AddSupplierActivity extends AppCompatActivity {

    private EditText suppliername;
    private EditText address;
    private EditText cityname;
    private EditText contacts;
    private EditText telephone;
    private EditText companyurl;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);

        suppliername = (EditText) findViewById(R.id.suppliername);
        address = (EditText) findViewById(R.id.address);
        cityname = (EditText) findViewById(R.id.cityname);
        contacts = (EditText) findViewById(R.id.contacts);
        telephone = (EditText) findViewById(R.id.telephone);
        companyurl = (EditText) findViewById(R.id.companyurl);

        bundle = this.getIntent().getExtras();
        if (bundle != null){                                                            //判断是否从修改界面而来
            int flag = bundle.getInt("flag");
            List<Map<String,String >> list =(ArrayList<Map<String,String>>) bundle.getSerializable("list");         //得到传递过来的供应商信息，并填写到控件中
            suppliername.setText(list.get(flag).get("supplier"));
            address.setText(list.get(flag).get("address"));
            cityname.setText(list.get(flag).get("cityname"));
            contacts.setText(list.get(flag).get("contacts"));
            telephone.setText(list.get(flag).get("telephone"));
            companyurl.setText(list.get(flag).get("companyurl"));
        }
    }

    public void addSuplier(View view) {
        SupplierManager sm = new SupplierManager(AddSupplierActivity.this);             //得到供应商表操作类的对象
        if (bundle != null){
            boolean rtn = sm.update(new Object[]{address.getText().toString(),cityname.getText().toString(),contacts.getText().toString(),telephone.getText().toString(),
                                    companyurl.getText().toString(),suppliername.getText().toString()});
            String message = (rtn)? "更新成功！":"更新失败！";                                    //判断是否更新成功
            Toast.makeText(AddSupplierActivity.this,message,Toast.LENGTH_SHORT).show();
            finish();                                                                               //关闭界面
        }else {
            supplier supplier = new supplier(suppliername.getText().toString(),address.getText().toString(),cityname.getText().toString(),      //创建一个供应商类对象
                    contacts.getText().toString(),telephone.getText().toString(),companyurl.getText().toString());
            if (supplier.getSuppliername().equals("")||supplier.getAddress().equals("")||supplier.getCityname().equals("")||supplier.getContacts().equals("")||supplier.getTelephone().equals("")||supplier.getCompanyurl().equals("")){
                Toast.makeText(AddSupplierActivity.this,"请输入全部信息！",Toast.LENGTH_SHORT).show();
            }else {
                boolean rtn = sm.add(new Object[]{supplier.getSuppliername(),supplier.getAddress(),supplier.getCityname(),supplier.getContacts(),supplier.getTelephone(),supplier.getCompanyurl()});
                if (rtn){
                    Toast.makeText(AddSupplierActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(AddSupplierActivity.this,"添加失败！",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
