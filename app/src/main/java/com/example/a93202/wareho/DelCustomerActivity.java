package com.example.a93202.wareho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Manager.CustomerManager;

public class DelCustomerActivity extends AppCompatActivity {
    private ListView lv;
    private SimpleAdapter sa;
    private List<Map<String, String >> list;
    private CustomerManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_customer);

        lv = (ListView) findViewById(R.id.lv);
        cm = new CustomerManager(this);
        list = cm.getAll(new String []{});
        sa = new SimpleAdapter(this,list,R.layout.listview_item,new String[]{"companyname","address","cityname","contacts","telephone","companyurl"},
                new int[]{R.id.mc,R.id.dz,R.id.cm,R.id.lx,R.id.dh,R.id.wz});
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {                     //当点击ListView中的某一项时，跳转到添加界面，并将此项信息传递过去
                final int id = i;
                new AlertDialog.Builder(DelCustomerActivity.this)
                        .setTitle("注意")
                        .setMessage("是否删除？")
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String onItem = list.get(id).get("companyname");
                                cm.del(new Object[]{onItem});
                                refreshNotesList();
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }
        });
    }
    private void refreshNotesList(){                                             //刷新列表显示
        int size = list.size();
        if (size>0){
            list.removeAll(list);
            sa.notifyDataSetChanged();
            lv.setAdapter(sa);
        }
        list = cm.getAll(new String []{});
        sa = new SimpleAdapter(this,list,R.layout.listview_item,new String[]{"companyname","address","cityname","contacts","telephone","companyurl"},
                new int[]{R.id.mc,R.id.dz,R.id.cm,R.id.lx,R.id.dh,R.id.wz});
        lv.setAdapter(sa);

    }
}
