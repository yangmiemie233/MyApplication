package com.hnist.yang.myapplication;



import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Main2Activity extends TabActivity {
    private RadioButton guide_home, guide_add, guide_search,guide_person;
    private RadioGroup main_tab_group;
    private Intent intent1;
    private Intent intent2;
    private Intent intent3;
    private Intent intent4;
    private String tab="tab0";
    private int currIndex = 0;
    private TabHost tabHost;
    int  size;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bmob.initialize(this,"2cb04c095c3a5828848a8ad31cb3fa99");


        //初始化四个Activity
        Intent intent=getIntent();
          user=intent.getStringExtra("username");

        System.out.print(size);
        intent1 = new Intent(this, ListViewActivity.class);//列表的显示
        intent1.putExtra("username",user);
        intent2 = new Intent(this, ViewActivity.class);//添加话题
        intent2.putExtra("username",user);
        intent3 = new Intent(this, Search.class);//搜索功能
        intent3.putExtra("username",user);
        intent4 = new Intent(this, Main6Activity.class);//个人中心
        intent4.putExtra("username",user);
        intent4.putExtra("size",size);
        init();
        inittAB();


    }


    /**
     * 初始化组件
     */
    private void init() {
        guide_home = (RadioButton) findViewById(R.id.guide_home);
        guide_search = (RadioButton) findViewById(R.id.guide_search);
        guide_person = (RadioButton) findViewById(R.id.guide_person);
        guide_add=(RadioButton) findViewById(R.id.guide_add);
        main_tab_group=(RadioGroup)findViewById(R.id.main_tab_group);
        Drawable drawable_home=getResources().getDrawable(R.drawable.shouye);
        Drawable drawable_add=getResources().getDrawable(R.drawable.upadte);
        Drawable drawable_search=getResources().getDrawable(R.drawable.sousuo);
        Drawable drawable_person=getResources().getDrawable(R.drawable.shezhi);
        drawable_home.setBounds(0,0,100,100);
        guide_home.setCompoundDrawables(null,drawable_home,null,null);
        drawable_add.setBounds(0,0,100,100);
        guide_add.setCompoundDrawables(null,drawable_add,null,null);
        drawable_search.setBounds(0,0,100,100);
        guide_search.setCompoundDrawables(null,drawable_search,null,null);
        drawable_person.setBounds(0,0,100,100);
        guide_person.setCompoundDrawables(null,drawable_person,null,null);
        main_tab_group.check(R.id.guide_home);

        //设置点击事件
        guide_home.setOnClickListener(new MyOnPageChangeListener());
        guide_add.setOnClickListener(new MyOnPageChangeListener());
        guide_search.setOnClickListener(new MyOnPageChangeListener());
        guide_person.setOnClickListener(new MyOnPageChangeListener());
    }

    //填充TabHost
    private void inittAB() {
        tabHost = getTabHost();
        //这里tab0是第一个，tab1是第二个窗口，以此类推
        tabHost.addTab(tabHost.newTabSpec("tab0")
                .setIndicator("tab0")
                .setContent(intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("tab1")
                .setContent(intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("tab3")
                .setContent(intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("tab2")
                .setContent(intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        if(tab.equalsIgnoreCase("tab0")){
            tabHost.setCurrentTabByTag("tab0");
        }
    }

    /**
     * 点击事件类
     */
    private class MyOnPageChangeListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.guide_home:
                    currIndex = 0;
                    //切换第一个窗口
                    tabHost.setCurrentTabByTag("tab0");
                    break;
                case R.id.guide_search:
                    currIndex = 3;
                    //切换第二个窗口
                    tabHost.setCurrentTabByTag("tab2");
                    break;
                case R.id.guide_add:
                    currIndex = 4;
                    //切换第三个窗口
                    tabHost.setCurrentTabByTag("tab1");
                    break;
                case R.id.guide_person:
                    currIndex = 5;
                    //切换第四个窗口
                    tabHost.setCurrentTabByTag("tab3");
                    break;
            }
        }
    }
}