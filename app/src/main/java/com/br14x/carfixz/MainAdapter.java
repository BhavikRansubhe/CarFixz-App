package com.br14x.carfixz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class MainAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] itemNames;
    private int[] quantities;

    public MainAdapter(Context c,String[] items,int[] quantities){
        context=c;
        this.itemNames=items;
        this.quantities=quantities;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.orderdetailsrow,null);
        }

        TextView itemName=convertView.findViewById(R.id.orderNametextView);
        TextView quantity=convertView.findViewById(R.id.orderQuantitytextView);

        itemName.setText(itemNames[position]);
        quantity.setText(quantities[position]);
        return convertView;
    }
}
