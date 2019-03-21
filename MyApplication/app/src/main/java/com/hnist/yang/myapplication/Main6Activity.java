package com.hnist.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {
private TextView head;
private TextView number;
private Button exit;
private ImageView mycsdn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        head=(TextView)findViewById(R.id.head);
        final Intent intent=getIntent();
        final String headName=intent.getStringExtra("username");
        final String size=intent.getStringExtra("size");
        head.setText(headName);
        exit=(Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Main6Activity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        mycsdn=(ImageView)findViewById(R.id.mycsdn);//我的博客
        mycsdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main6Activity.this,MycsdnActivity.class);
                intent.putExtra("username",headName);
                startActivity(intent);
            }
        });
    }
}
