package com.example.a93202.wareho;
/*
*
* 显示入库记录界面
* */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

import Manager.IntoManager;

public class IntoBaseActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter sa;
    private IntoManager im;
    private List<Map<String,String >> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into_base);

        listView = (ListView)findViewById(R.id.listview);
        im = new IntoManager(this);
        list = im.getAll(null);                         //得到要展示的信息
        sa = new SimpleAdapter(this,list,R.layout.listview_into,new String[]{"companyname","intoguige","contacts",
                "telephone","intonum","proname","proguige","proprice","intotime"},new int[]{R.id.comname,R.id.guige,R.id.contacts,R.id.tel,
                R.id.num,R.id.proname,R.id.proguige,R.id.proprice,R.id.time});          //创建SimpleAdapter

        listView.setAdapter(sa);                                                           //为ListView绑定Adapter
    }
}
