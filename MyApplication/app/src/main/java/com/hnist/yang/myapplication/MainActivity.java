package com.hnist.yang.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hnist.yang.myapplication.RegisterActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"2cb04c095c3a5828848a8ad31cb3fa99");
        Button login=(Button)findViewById(R.id.login);
        final EditText username=(EditText)findViewById(R.id.username);
        final EditText password=(EditText)findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser userlogin = new MyUser();
                final String uname=username.getText().toString();
                String pwd=password.getText().toString();
                userlogin.setUsername(uname);
                userlogin.setPassword(pwd);
                userlogin.login(new SaveListener<MyUser >() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e==null)
                        {
                            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                            intent.putExtra("username",uname);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this,"用户或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        Button re=(Button)findViewById(R.id.re);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
