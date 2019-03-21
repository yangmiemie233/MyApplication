package com.hnist.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Search extends AppCompatActivity {
private ListView listView;
private ImageView search;
private EditText search_input;
private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView=(ListView)findViewById(R.id.listView);
        search=(ImageView)findViewById(R.id.search);
        search_input=(EditText) findViewById(R.id.search_input);
        Bmob.initialize(this,"2cb04c095c3a5828848a8ad31cb3fa99");
        Intent intent=getIntent();
        final String user=intent.getStringExtra("username");
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String search_type=search_input.getText().toString();
                BmobQuery<Mycsdn> bmobQuery=new BmobQuery<Mycsdn>();
                bmobQuery.addWhereEqualTo("type",search_type);
                bmobQuery.findObjects(new FindListener<Mycsdn>() {
                    @Override
                    public void done(List<Mycsdn> list, BmobException e) {
                       if (e==null){
                           final ArrayList<HashMap<String,String>> mapList=new ArrayList<HashMap<String, String>>();
                           SimpleAdapter simpleAdapter;
                           for (Mycsdn data:list)
                           {
                               HashMap<String,String>   mHashMap=new HashMap<String, String>();
                               mHashMap.put("head_name",data.getHead_name());
                               mHashMap.put("keyWords",data.getKeyWords());
                               mHashMap.put("topic_content",data.getTopic_content());
                               mHashMap.put("visit_number",data.getVisit_number());
                               mHashMap.put("belongTo",data.getBelongTo());
                               mHashMap.put("createdAt",data.getCreatedAt());
                               mHashMap.put("username",user);
                               mapList.add(mHashMap);
                           }
                           System.out.print(mapList);
                           simpleAdapter=new SimpleAdapter(Search.this,mapList,R.layout.search_listitems,new String[]{"head_name","belongTo","visit_number","createdAt","keyWords"},new int[]{
                                   R.id.head_name,R.id.belongTo,R.id.visit_number,R.id.createdAt,R.id.keyWords});
                           listView.setAdapter(simpleAdapter);
                           simpleAdapter.notifyDataSetChanged();
                           listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                               @Override
                               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                   ListView listView=(ListView)parent;
                                   HashMap<String,String >info=(HashMap<String, String>)listView.getItemAtPosition(position) ;
                                   String head_name=info.get("head_name");
                                   String keyWords=info.get("keyWords");
                                   String topic_content=info.get("topic_content");
                                   String visit_number=info.get("visit_number");
                                   String belongTo=info.get("belongTo");
                                   String createdAt=info.get("createdAt");
                                   String user=info.get("username");
                                   Toast.makeText(Search.this,head_name.toString(),Toast.LENGTH_SHORT).show();
                                   Bundle bundle =new Bundle();
                                   bundle.putString("head_name",head_name);
                                   bundle.putString("keyWords",keyWords);
                                   bundle.putString("topic_content",topic_content);
                                   bundle.putString("visit_number",visit_number);
                                   bundle.putString("belongTo",belongTo);
                                   bundle.putString("createdAt",createdAt);
                                   bundle.putString("username",user);
                                   Intent intent=new Intent(Search.this,item.class);
                                   intent.putExtras(bundle);
                                   finish();
                                   startActivity(intent);
                               }
                           });
                       }else {
                           Log.d("查询失败：","无相关信息");
                       }
                    }
                });

            }
        });

    }
}
