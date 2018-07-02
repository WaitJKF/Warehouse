package com.example.a93202.wareho;

/*
*
* 显示库存剩余信息，不提供修改删除操作
*
* */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

import Manager.StockManager;

public class StockActivity extends AppCompatActivity {

    private StockManager sm;
    private List<Map<String, String>> getList;
    private ListView listView;
    private SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        sm = new StockManager(this);
        listView = (ListView)findViewById(R.id.listview);

        getList = sm.getAll(null);
        sa = new SimpleAdapter(this,getList,R.layout.pro_show,new String[]{"proname","proguige","pronum"},new int[]{R.id.mc,R.id.gg,R.id.jg});
        listView.setAdapter(sa);


    }

}
