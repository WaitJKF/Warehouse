package com.example.a93202.wareho;
/*
*
* 出库记录显示界面
* */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

import Manager.OutManager;

public class OutBaseActivity extends AppCompatActivity {

    private ListView listView;
    private OutManager om;
    private SimpleAdapter sa;
    private List<Map<String,String>> getList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_base);

        listView = (ListView) findViewById(R.id.listview);
        om = new OutManager(this);
        getList = om.getAll(null);
        sa = new SimpleAdapter(this,getList,R.layout.listview_into,new String[]{"companyname","outguige","contacts",
                "telephone","outnum","proname","proguige","proprice","outtime"},new int[]{R.id.comname,R.id.guige,R.id.contacts,R.id.tel,
                R.id.num,R.id.proname,R.id.proguige,R.id.proprice,R.id.time});
        listView.setAdapter(sa);
    }
}
