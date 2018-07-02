package com.example.a93202.wareho;
/*
* 添加商品界面
* */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Manager.ProductManager;
import entity.Product;

public class AddProductActivity extends AppCompatActivity {

    private EditText add_proname;
    private EditText add_proguige;
    private EditText add_proprice;
    private Bundle getBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        add_proname = (EditText) findViewById(R.id.add_porname);
        add_proguige = (EditText) findViewById(R.id.add_proguige);
        add_proprice = (EditText) findViewById(R.id.add_proprice);
        getBundle = this.getIntent().getExtras();
        if (getBundle != null){                                                     //判断是否从修改页面而来
            add_proname.setText(getBundle.getString("proname"));
            add_proguige.setText(getBundle.getString("proguige"));
            add_proprice.setText(getBundle.getString("proprice"));
        }

    }

    public void toAdd(View view) {
        ProductManager pm = new ProductManager(AddProductActivity.this);       //创建一个商品表操作类对象
        if (getBundle !=null){                                                         //进行更新操作
            boolean rtn = pm.update(new Object[]{add_proguige.getText().toString(),add_proprice.getText().toString(),add_proname.getText().toString()});
            String message = (rtn)? "更新成功！":"更新失败！";                          //判断是否更新成功
            Toast.makeText(AddProductActivity.this,message,Toast.LENGTH_SHORT).show();
            finish();                                                                    //结束后关闭此界面
        }else {                                                                          //进行添加操作
            Product product = new Product(add_proname.getText().toString(),add_proguige.getText().toString(),add_proprice.getText().toString());     //创建一个商品类对象
            if (product.getProname().equals("")||product.getProguige().equals("")||product.getProprice().equals("")){
                Toast.makeText(AddProductActivity.this,"请输入商品信息！",Toast.LENGTH_SHORT).show();
            }else {
                boolean rtn = pm.addProduct(new Object[]{product.getProname(),product.getProguige(),product.getProprice()});
                if (rtn){
                    Toast.makeText(AddProductActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                    finish();                                                               //添加成功后关闭界面
                }else {
                    Toast.makeText(AddProductActivity.this,"添加失败！",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
