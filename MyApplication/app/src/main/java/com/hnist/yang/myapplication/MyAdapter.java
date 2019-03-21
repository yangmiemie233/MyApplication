package com.hnist.yang.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Loss> lossList;
    private LayoutInflater inflater;
    public MyAdapter(){}

    public MyAdapter(List<Loss> lossList, Context context) {
        this.lossList = lossList;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return  lossList==null?0:lossList.size();
    }
    public Loss getItem(int position){
        return lossList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.activity_list_view,null);
        Loss loss=getItem(position);
        TextView title=(TextView)view.findViewById(R.id.title);
        TextView phone=(TextView)view.findViewById(R.id.phone);
        TextView describe=(TextView)view.findViewById(R.id.describe);
        title.setText(loss.getTitle());
        phone.setText(loss.getPhone());
        describe.setText(loss.getDescribe());
        return view;
    }
}
