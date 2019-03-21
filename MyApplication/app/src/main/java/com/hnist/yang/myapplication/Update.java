package com.hnist.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
//修改博客
public class Update extends AppCompatActivity {
private EditText head_name;
private EditText keyWord;
private EditText content;
private Button save;
private ImageView back;
String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle bundle=getIntent().getExtras();
        user=bundle.getString("username");
        final String head1=bundle.getString("head_name");
        String content1=bundle.getString("topic_content");
        final String keyWords=bundle.getString("keyWords");
        final String objectId=bundle.getString("objectId");
        head_name=(EditText)findViewById(R.id.head_name);
        keyWord=(EditText)findViewById(R.id.keyWords);
        content=(EditText)findViewById(R.id.content);
        save=(Button)findViewById(R.id.submit);
        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Update.this,MycsdnActivity.class);
                intent.putExtra("username",user);
                startActivity(intent);
            }
        });
        head_name.setText(head1);
        keyWord.setText(keyWords);
        content.setText(content1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String  a_head_name=head_name.getText().toString();
                final  String  b_keyWords=keyWord.getText().toString();
                final  String  c_content=content.getText().toString();
                Mycsdn up=new Mycsdn();
                up.setHead_name(a_head_name);
                up.setTopic_content(c_content);
                up.setKeyWords(b_keyWords);
                up.update(objectId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            Toast.makeText(Update.this,"修改成功",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Update.this,MycsdnActivity.class);
                            intent.putExtra("username",user);
                            startActivity(intent);
                        }else {

                        }
                    }
                });

            }
        });


    }
}
