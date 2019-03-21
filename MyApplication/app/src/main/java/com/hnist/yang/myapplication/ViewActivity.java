package com.hnist.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class ViewActivity extends AppCompatActivity {
    private TextView head;
    private TextView keyWords;
    private TextView type;
    private TextView content;
    private TextView send;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_view);
        Bmob.initialize(this,"2cb04c095c3a5828848a8ad31cb3fa99");
     head=(TextView)findViewById(R.id.head);
     keyWords=(TextView)findViewById(R.id.keyWords);
     type=(TextView)findViewById(R.id.type);
     content=(TextView)findViewById(R.id.content);
     send=(Button)findViewById(R.id.send);
     back=(ImageView) findViewById(R.id.back);

     send.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=getIntent();
             final String user= intent.getStringExtra("username");
     String num = String.valueOf(new Double(Math.random() * 1000).intValue());
     String head_name=head.getText().toString();
     String keyWord=keyWords.getText().toString();
     String types=type.getText().toString();
     String contents=content.getText().toString();
     Mycsdn add=new Mycsdn();
     add.setHead_name(head_name);
     add.setKeyWords(keyWord);
     add.setTopic_content(contents);
     add.setType(types);
     add.setBelongTo(user);
     add.setVisit_number(num);
     add.save(new SaveListener<String>() {
         @Override
         public void done(String s, BmobException e) {
             if (e==null){
                 Toast.makeText(ViewActivity.this," 发布成功",Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(ViewActivity.this, Main2Activity.class);
                 intent.putExtra("username",user);
                 startActivity(intent);
             }
             else {
                 Toast.makeText(ViewActivity.this,"发布失败，请重新填写",Toast.LENGTH_SHORT).show();
             }
         }
     });
         }
     });


}}
