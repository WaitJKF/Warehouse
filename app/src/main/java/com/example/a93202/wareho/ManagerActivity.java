package com.example.a93202.wareho;
/*
*
* 选择功能的二级界面
*
* */
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ManagerActivity extends AppCompatActivity {
    private Button m_add;
    private Button m_delet;
    private Button m_update;
    private Intent it = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        m_add = (Button)findViewById(R.id.m_add);
        m_delet = (Button)findViewById(R.id.m_delet);
        m_update = (Button)findViewById(R.id.m_update);

        setButton();

    }

    private void setButton(){                                               //根据传递的信息为Button设置文本信息，并绑定监听事件
        Bundle getBundle = this.getIntent().getExtras();
        int flag = getBundle.getInt("flag");
        switch (flag){
            case 0:
                m_add.setText("添加商品信息");
                m_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,AddProductActivity.class);
                        startActivity(it);
                    }
                });
                m_delet.setText("修改商品信息");
                m_delet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,ShowProActivity.class);
                        startActivity(it);
                    }
                });
                m_update.setText("删除商品信息");
                m_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,DelProductActivity.class);
                        startActivity(it);
                    }
                });
                break;
            case 1:
                m_add.setText("添加顾客信息");
                m_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,AddCustomerActivity.class);
                        startActivity(it);
                    }
                });
                m_delet.setText("修改顾客信息");
                m_delet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,ShowCusActivity.class);
                        startActivity(it);
                    }
                });
                m_update.setText("删除顾客信息");
                m_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,DelCustomerActivity.class);
                        startActivity(it);
                    }
                });
                break;
            case 2:
                m_add.setText("添加供应商信息");
                m_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,AddSupplierActivity.class);
                        startActivity(it);
                    }
                });
                m_delet.setText("修改供应商信息");
                m_delet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,ShowSupplierActivity.class);
                        startActivity(it);
                    }
                });
                m_update.setText("删除供应商信息");
                m_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,DelSupplierActivity.class);
                        startActivity(it);
                    }
                });
                break;
            case 3:
                m_add.setText("商品入库");
                m_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,IntoActivity.class);
                        startActivity(it);
                    }
                });
                m_delet.setText("商品出库");
                m_delet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        it.setClass(ManagerActivity.this,OutActivity.class);
                        startActivity(it);
                    }
                });
                m_update.setText("返回");
                m_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ManagerActivity.this.finish();
                    }
                });
                break;
        }
    }


}
