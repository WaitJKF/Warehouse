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

import java.util.List;
import java.util.Map;

import Manager.ProductManager;

public class DelProductActivity extends AppCompatActivity {

    private ListView lv;
    private SimpleAdapter sa;
    private List<Map<String, String >> list;
    private ProductManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_product);

        lv = (ListView) findViewById(R.id.listView);
        pm = new ProductManager(this);
        list = pm.getAll(new String []{});
        sa = new SimpleAdapter(this,list,R.layout.pro_show,new String[]{"proname","proguige","proprice"},new int[]{R.id.mc,R.id.gg,R.id.jg});
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int id = i;
                new AlertDialog.Builder(DelProductActivity.this)
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
                                String onItem = list.get(id).get("proname");
                                pm.delProduct(new Object[]{onItem});
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
        list = pm.getAll(new String []{});
        sa = new SimpleAdapter(this,list,R.layout.pro_show,new String[]{"proname","proguige","proprice"},new int[]{R.id.mc,R.id.gg,R.id.jg});
        lv.setAdapter(sa);

    }
}
