package com.example.a93202.wareho;
/*
*
* 显示修改客户信息界面
* */
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
import Manager.ProductManager;

public class ShowCusActivity extends AppCompatActivity {

    private ListView lv;
    private SimpleAdapter sa;
    private List<Map<String, String >> list;
    private CustomerManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cus);

        lv = (ListView) findViewById(R.id.lv);
        cm = new CustomerManager(this);
        list = cm.getAll(new String []{});
        sa = new SimpleAdapter(this,list,R.layout.listview_item,new String[]{"companyname","address","cityname","contacts","telephone","companyurl"},
                new int[]{R.id.mc,R.id.dz,R.id.cm,R.id.lx,R.id.dh,R.id.wz});
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {                     //当点击ListView中的某一项时，跳转到添加界面，并将此项信息传递过去
                Intent it = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("flag",i);
                bundle.putSerializable("list",(ArrayList<Map<String, String>>)list);
                it.putExtras(bundle);
                it.setClass(ShowCusActivity.this,AddCustomerActivity.class);
                startActivity(it);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {                  //长按ListView中的某一项时，提示是否删除此项
                final int id = i;
                new AlertDialog.Builder(ShowCusActivity.this)
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
                return true;
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
