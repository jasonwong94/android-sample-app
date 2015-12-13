package com.example.jasonwong94.productapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasonwong94 on 15-12-12.
 */
public class ProductListActivity extends ListActivity {
    static final String PRODUCTID = "com.example.jasonwong94.productapp.ProductID";
    static final String tag = "ProductListActivity";
    ProductDBHelper dbHelper;
    List<String> productTitles;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        dbHelper = new ProductDBHelper(ProductListActivity.this);

        List<ProductClass> productList = dbHelper.getAllData();
        productTitles = new ArrayList<String>();

        if (productList == null) {
            TextView addProduct = (TextView) findViewById(R.id.addProduct);
            addProduct.setVisibility(addProduct.VISIBLE);
        }

        for (ProductClass product : productList) {
            productTitles.add(product.getProductDescription());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            R.layout.content_row_list_product,
            R.id.rowProductName,
            productTitles
        );

        setListAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, ProductCreateActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(tag, "Product" + id + "clicked on: " + position);
        Intent intent = new Intent( ProductListActivity.this, ProductCreateActivity.class );
        intent.putExtra( PRODUCTID, id );
        startActivity(intent);
    }
}