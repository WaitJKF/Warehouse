package com.example.a93202.wareho;
/*
*
* 功能选择界面
*
* */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity {

    private Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        show = (Button) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this,BaseActivity.class);
                startActivity(intent);
            }
        });
    }
    public void toSelect(View view){
        int id = view.getId();
        Intent it = new Intent();
        Bundle bundle = new Bundle();
        switch (id){
            case R.id.product:
                it.setClass(this,ManagerActivity.class);
                bundle.putInt("flag",0);
                it.putExtras(bundle);
                startActivity(it);
                break;
            case R.id.customer:
                it.setClass(this,ManagerActivity.class);
                bundle.putInt("flag",1);
                it.putExtras(bundle);
                startActivity(it);
                break;
            case R.id.supplier:
                it.setClass(this,ManagerActivity.class);
                bundle.putInt("flag",2);
                it.putExtras(bundle);
                startActivity(it);
                break;
            case R.id.manager:
                it.setClass(this,ManagerActivity.class);
                bundle.putInt("flag",3);
                it.putExtras(bundle);
                startActivity(it);
                break;
        }
    }

    public void toUpdate(View view) {
        Intent it = new Intent(this,UpdateUserActivity.class);
        startActivity(it);
    }
}
