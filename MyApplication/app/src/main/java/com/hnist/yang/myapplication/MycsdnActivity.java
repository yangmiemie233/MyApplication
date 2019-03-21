package com.hnist.yang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import cn.bmob.v3.listener.UpdateListener;

//查询我发布的博客
public class MycsdnActivity extends AppCompatActivity {
   private ListView listView;
   private ImageView back;
   String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycsdn);
        Bmob.initialize(this,"2cb04c095c3a5828848a8ad31cb3fa99");
        listView=(ListView)findViewById(R.id.listview);
        Intent intent=getIntent();
        user=intent.getStringExtra("username");
        getData();
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MycsdnActivity.this,Main2Activity.class);
                intent.putExtra("username",user);
                startActivity(intent);
            }
        });
    }
    private void getData() {

        BmobQuery<Mycsdn> bmobQuery=new BmobQuery<Mycsdn>();
       bmobQuery.addWhereEqualTo("belongTo",user);
        bmobQuery.findObjects(new FindListener<Mycsdn>() {
            @Override
            public void done(final List<Mycsdn> list, BmobException e) {
                  if (e==null){
                   int a=list.size();
                      Toast.makeText(MycsdnActivity.this,"查询"+a+"条数据",Toast.LENGTH_SHORT).show();
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
                          mHashMap.put("objectId",data.getObjectId());
                          mHashMap.put("username",user);
                          mapList.add(mHashMap);
                      }
                      System.out.print(mapList);
                      simpleAdapter=new SimpleAdapter(MycsdnActivity.this,mapList,R.layout.activity_csdn_items,
                              new String[]{"head_name","keyWords","visit_number","createdAt"},new int[]{
                              R.id.head_name,R.id.keyWords,R.id.visit_number,R.id.createdAt}){
                          @Override
                          public View getView(int position, View convertView, ViewGroup parent) {
                              final View view= super.getView(position, convertView, parent);
                              ListView listView=(ListView)parent;
                              final HashMap<String,String >info=(HashMap<String, String>)listView.getItemAtPosition(position) ;
                              final String objectId=info.get("objectId");
                              final String head_name=info.get("head_name");
                              final String keyWords=info.get("keyWords");
                              final String topic_content=info.get("topic_content");
                              final String visit_number=info.get("visit_number");
                              final String belongTo=info.get("belongTo");
                              String createdAt=info.get("createdAt");
                              ImageView delete=(ImageView)view.findViewById(R.id.delete);
                              ImageView update=(ImageView)view.findViewById(R.id.update);
                              delete.setTag(position);
                              update.setTag(position);
                              delete.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      Mycsdn de=new Mycsdn();
                                      de.setObjectId(objectId);

                                      System.out.print(objectId);
                                      mapList.remove((int)view.getTag());
                                     de.delete(new UpdateListener() {
                                         @Override
                                         public void done(BmobException e) {
                                           if (e==null){
                                               Toast.makeText(MycsdnActivity.this,"已经删除",Toast.LENGTH_SHORT).show();
                                           }else {
                                               Toast.makeText(MycsdnActivity.this, objectId,Toast.LENGTH_SHORT).show();
                                           }
                                         }
                                     });
                                      notifyDataSetChanged();
                                  }
                              });
                              update.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Bundle bundle =new Bundle();
                                      bundle.putString("head_name",head_name);
                                      bundle.putString("keyWords",keyWords);
                                      bundle.putString("topic_content",topic_content);
                                      bundle.putString("objectId",objectId);
                                      bundle.putString("username",user);
                                      Intent intent=new Intent(MycsdnActivity.this,Update.class);
                                      intent.putExtras(bundle);
                                      finish();
                                      startActivity(intent);
                                  }
                              });
                              return view;
                          }
                      };
                      listView.setAdapter(simpleAdapter);
                      simpleAdapter.notifyDataSetChanged();
                      listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                          @Override
                          public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                              ListView listView=(ListView)parent;
                              final HashMap<String,String >info=(HashMap<String, String>)listView.getItemAtPosition(position) ;
                              String head_name=info.get("head_name");
                              String keyWords=info.get("keyWords");
                              String topic_content=info.get("topic_content");
                              String visit_number=info.get("visit_number");
                              String belongTo=info.get("belongTo");
                              String createdAt=info.get("createdAt");
                              Toast.makeText(MycsdnActivity.this,head_name.toString(),Toast.LENGTH_SHORT).show();
                              Bundle bundle =new Bundle();
                              bundle.putString("head_name",head_name);
                              bundle.putString("keyWords",keyWords);
                              bundle.putString("topic_content",topic_content);
                              bundle.putString("visit_number",visit_number);
                              bundle.putString("belongTo",belongTo);
                              bundle.putString("createdAt",createdAt);
                              bundle.putString("username",user);
                              Intent intent=new Intent(MycsdnActivity.this,Main5Activity.class);
                              intent.putExtras(bundle);
                              finish();
                              startActivity(intent);

                          }
                      });
                  }
                  else {
                      Log.d("查询失败：","无相关信息");
                  }
            }
        });
    }
}
