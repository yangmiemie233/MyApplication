package com.hnist.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class item extends AppCompatActivity {
private TextView head_names;
private  TextView createdAts;
private TextView visit_numbers;
private TextView belongTos;
private TextView content;
private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Bundle bundle=getIntent().getExtras();
        String head_name=bundle.getString("head_name");
        String createdAt=bundle.getString("createdAt");
        String belongTo=bundle.getString("belongTo");
        String visit_number=bundle.getString("visit_number");
        String contents=bundle.getString("topic_content");
        final String user=bundle.getString("username");
        head_names=(TextView)findViewById(R.id.head_name);
        createdAts=(TextView)findViewById(R.id.createdAt);
        visit_numbers=(TextView)findViewById(R.id.visit_number);
        belongTos=(TextView)findViewById(R.id.belongTo);
        content=(TextView)findViewById(R.id.content);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
        head_names.setText(head_name);
        createdAts.setText(createdAt);
        visit_numbers.setText("访问量："+visit_number);
        belongTos.setText("作者:"+belongTo);
        content.setText(contents);
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(item.this,Main2Activity.class);
                intent.putExtra("username",user);
                startActivity(intent);
            }
        });


    }
}
