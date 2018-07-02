package com.example.a93202.wareho;
/*
* 库存显示界面
* */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void toClick(View view) {
        int id = view.getId();
        Intent it = new Intent();
        switch (id){
            case R.id.bt1:
                it.setClass(BaseActivity.this,IntoBaseActivity.class);              //跳转到入库记录界面
                startActivity(it);
                break;
            case R.id.bt2:
                it.setClass(BaseActivity.this,OutBaseActivity.class);               //跳转到出库记录界面
                startActivity(it);
                break;
            case R.id.bt3:
                it.setClass(BaseActivity.this,StockActivity.class);                 //跳转到剩余库存显示界面
                startActivity(it);
                break;
        }
    }
}
