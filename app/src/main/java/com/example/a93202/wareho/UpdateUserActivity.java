package com.example.a93202.wareho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

import Manager.UserManager;

public class UpdateUserActivity extends AppCompatActivity {
    private UserManager um;
    private EditText userName;
    private EditText psw1;
    private EditText psw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        userName = (EditText) findViewById(R.id.userName);
        psw1 = (EditText) findViewById(R.id.psw1);
        psw2 = (EditText) findViewById(R.id.psw2);
    }

    public void toUpdate(View view) {
        String name = userName.getText().toString();
        String p1 = psw1.getText().toString();
        String p2 = psw2.getText().toString();
        if (!name.equals("")){
            um = new UserManager(this);
            Map<String,String> map = um.getOne(new String[]{name});
            boolean res = p1.equals(p2);
            if ((map.size() != 0) || res){
                boolean result = um.update(new Object[]{p2,null,name});
                if (result){
                    Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
                }
            }else if (res = false){
                Toast.makeText(this,"两次密码不一样，请重新输入",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"用户不存在，请输入正确的用户名",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
