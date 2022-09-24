package com.wiut.studentapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CoinListAdapter extends BaseAdapter {

    private final List<Modelitem> itemsModelsl;
    private List<Modelitem> itemsModelListFiltered;
    private final Context context;

    public CoinListAdapter(List<Modelitem> itemsModelsl, Context context) {
        this.itemsModelsl = itemsModelsl;
        this.itemsModelListFiltered = itemsModelsl;
        this.context = context;
    }
    @Override
    public int getCount() {
        return itemsModelListFiltered.size();
    }
    @Override
    public Object getItem(int position) {
        return itemsModelListFiltered.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @SuppressLint({"SetTextI18n", "NewApi", "ClickableViewAccessibility"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(context).inflate(R.layout.coin_item, parent, false);
        }

        Modelitem dataModal = (Modelitem) getItem(position);

        TextView txtitem1 = listitemView.findViewById(R.id.txtitem1);
        TextView txtitem2 = listitemView.findViewById(R.id.txtitem2);
        TextView txtitem3 = listitemView.findViewById(R.id.txtitem3);

        txtitem1.setText(dataModal.getData());
        txtitem2.setText("1 "+dataModal.getCoin1());
        txtitem3.setText(dataModal.getCoin2()+" so'm");
        return listitemView;
    }
}
