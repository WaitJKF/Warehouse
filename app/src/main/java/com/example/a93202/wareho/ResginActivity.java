package com.example.a93202.wareho;
/*
*
* 注册界面
*
* */
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Manager.UserManager;
import entity.User;

public class ResginActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private Button bt_ok;
    private UserManager userManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgin);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        bt_ok = (Button) findViewById(R.id.bt_ok);
        context = this;

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userManager = new UserManager(context);
                String name = et1.getText().toString();
                String psw = et2.getText().toString();
                String num = name.substring(0, 1) + String.valueOf(name.hashCode());
                User user = new User(name, psw, num);
                boolean rt = userManager.addUser(new String[]{user.getName(), user.getPsw(), user.getNum()});
                if (rt) {
                    Toast.makeText(ResginActivity.this, "注册成功，请登录！", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ResginActivity.this, "注册失败，请重新注册！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
