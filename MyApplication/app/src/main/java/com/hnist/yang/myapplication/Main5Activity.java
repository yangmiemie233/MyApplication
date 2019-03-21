package com.hnist.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//我发布的博客详细页面
public class Main5Activity extends AppCompatActivity {
    private TextView head;//标题
    private TextView person;//head
    private TextView createdAt;//创建时间
    private TextView visit_numbers;//访问量
    private TextView content;//内容
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Bundle bundle=getIntent().getExtras();
        String head1=bundle.getString("head_name");
        final String person1=bundle.getString("username");
        String createdAt1=bundle.getString("createdAt");
        String content1=bundle.getString("topic_content");
        String visit_number=bundle.getString("visit_number");
        head=(TextView)findViewById(R.id.textView6);
        person=(TextView)findViewById(R.id.person);
        createdAt=(TextView)findViewById(R.id.creat_time);
        content=(TextView)findViewById(R.id.content);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
        visit_numbers=(TextView)findViewById(R.id.visit_numbers);
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main5Activity.this,MycsdnActivity.class);
                intent.putExtra("username",person1);
                startActivity(intent);
            }
        });

        head.setText(head1);
        person.setText(person1+"的博客");
        createdAt.setText(createdAt1);
        content.setText(content1);
        visit_numbers.setText("访问量:"+visit_number);

    }
}
