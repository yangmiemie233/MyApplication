package com.hnist.yang.myapplication;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class ListViewActivity extends AppCompatActivity {
private ListView listView;
private ImageView search;
String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Bmob.initialize(this,"2cb04c095c3a5828848a8ad31cb3fa99");
        Intent intent=getIntent();
        user=intent.getStringExtra("username");
        getData();
        listView=(ListView)findViewById(R.id.list_item);
    }

    private void getData() {
        BmobQuery<Mycsdn> query=new BmobQuery<Mycsdn>();
        query.findObjects(new FindListener<Mycsdn>() {
            @Override
            public void done(final List<Mycsdn> list, BmobException e) {
                if (e==null){
                    int a=list.size();
                    Toast.makeText(ListViewActivity.this,"查询"+a+"条数据",Toast.LENGTH_SHORT).show();
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
                        mapList.add(mHashMap);
                    }
                    System.out.print(mapList);
                   simpleAdapter=new SimpleAdapter(ListViewActivity.this,mapList,R.layout.list,new String[]{"head_name","visit_number","createdAt","belongTo","keyWords"},new int[]{
                            R.id.head_name,R.id.visit_number,R.id.createdAt,R.id.belongTo,R.id.keyWords});
                  listView.setAdapter(simpleAdapter);
                  simpleAdapter.notifyDataSetChanged();
                    listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ListView listView=(ListView)parent;
                              HashMap<String,String >info=(HashMap<String, String>)listView.getItemAtPosition(position) ;
                              String head_name=info.get("head_name");
                              String visit_number=info.get("visit_number");
                              String createdAt=info.get("createdAt");
                              String belongTo=info.get("belongTo");
                              String topic_content=info.get("topic_content");
                              String keyWord=info.get("keyWords");
                              Toast.makeText(ListViewActivity.this,head_name.toString(),Toast.LENGTH_SHORT).show();
                              Bundle bundle =new Bundle();
                              bundle.putString("head_name",head_name);
                              bundle.putString("visit_number",visit_number);
                              bundle.putString("createdAt",createdAt);
                              bundle.putString("belongTo",belongTo);
                              bundle.putString("topic_content",topic_content);
                              bundle.putString("username",user);
                              bundle.putString("keyWords",keyWord);
                            Intent intent=new Intent(ListViewActivity.this,item.class);
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
}
