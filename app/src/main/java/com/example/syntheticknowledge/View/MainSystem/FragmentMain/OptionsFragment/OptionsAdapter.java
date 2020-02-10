package com.example.syntheticknowledge.View.MainSystem.FragmentMain.OptionsFragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OptionsAdapter extends BaseAdapter {
    private ArrayList<String> list;
    
    public OptionsAdapter(ArrayList<String> list){
        this.list = list;
    }
    
    @Override
    public int getCount(){
        return list.size();
    }
    
    @Override
    public Object getItem(int position){
        return list.get( position );
    }
    
    @Override
    public long getItemId(int position){
        return 0;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = (convertView == null)?View.inflate( parent.getContext(), android.R.layout.simple_list_item_2, null ) : convertView;
        TextView optionItem = v.findViewById( android.R.id.text1 );
        optionItem.setText( list.get( position ) );
        optionItem.setGravity( Gravity.CENTER_VERTICAL );
        optionItem.setTextColor( Color.parseColor( "#FF0E90" ) );
        optionItem.setHeight( 250 );
        return v;
    }
}