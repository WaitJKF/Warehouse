package com.example.a93202.wareho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import Manager.UserManager;

public class LoginActivity extends AppCompatActivity {

    private Button bt_log;
    private Button bt_res;
    private EditText et_name;
    private EditText et_psw;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_name = (EditText) findViewById(R.id.et_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
    }
    public void toClick(View view){                                         //点击事件，判断跳转到注册界面还是选择界面
        Intent it = new Intent();
        String name = et_name.getText().toString();
        String psw = et_psw.getText().toString();
        switch (view.getId()){
            case R.id.bt_log:
                int res = toJudge(name,psw);
                showMessage(res);
                if (res == 2){
                    it.setClass(this,SelectActivity.class);
                    startActivity(it);
                }
                break;
            case R.id.bt_res:
                it.setClass(this, ResginActivity.class);
                startActivity(it);
                break;
        }
    }
    private int toJudge(String name, String psw){                                   //检查输入的用户名,密码是否存在
        Map<String ,String > map = new HashMap<String ,String >();
        userManager = new UserManager(this);
        if (name.equals("")||psw.equals("")){
            return 0;
        }else {
            map = userManager.getOne(new String[]{name});
            if (map.size() == 0){
                return -1;
            }else {
                boolean result = psw.equals(map.get("password"));
                if (result){
                    return 2;
                }else{
                    return 1;
                }
            }
        }
    }
    private void showMessage(int i){                                                                //根据检查后的决定提示什么信息
        switch (i){
            case -1:
                Toast.makeText(this,"不存在该用户！",Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(this,"账号或密码为空！",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this,"密码错误！",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"登录成功！",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
