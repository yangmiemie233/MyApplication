package com.hnist.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bmob.initialize(this,"2cb04c095c3a5828848a8ad31cb3fa99");
        Button button=(Button)findViewById(R.id.button);
        final EditText editText=(EditText)findViewById(R.id.editText);
        final EditText editText1=(EditText)findViewById(R.id.editText2);
        final EditText email=(EditText)findViewById(R.id.emali);

        final EditText address=(EditText)findViewById(R.id.address);

        final RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radio);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser user = new MyUser();
                for (int i=0;i<radioGroup.getChildCount();i++)
                {
                    RadioButton rb=(RadioButton)radioGroup.getChildAt(i);
                    if (rb.isChecked()){
                        user.setSex(rb.getText().toString());
                    }
                }
                user.setUsername(editText.getText().toString());
                user.setPassword(editText1.getText().toString());
                user.setEmail(email.getText().toString());
                user.setAddress(address.getText().toString());

                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
}

