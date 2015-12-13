package com.example.jasonwong94.productapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by jasonwong94 on 15-12-12.
 */
public class ProductSimpleArrayAdapter extends ArrayAdapter<String> {
    final String[] values;
    final Context context;

    public ProductSimpleArrayAdapter(Context context, String[] values){
        super(context, R.layout.content_row_list_product, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
        View rowView = inflater.inflate(R.layout.content_row_list_product, viewGroup, false);
        TextView productName = (TextView) rowView.findViewById(R.id.rowProductName);
        TextView productTotalCost = (TextView) rowView.findViewById(R.id.productTotalCost);

        productName.setText(values[position]);
        productTotalCost.setText(values[position]);

        return rowView;
    }
}
