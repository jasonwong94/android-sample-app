package com.example.jasonwong94.productapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
//        implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView listView;
    ProductDBHelper dbHelper;
    List<String> productTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById( R.id.displayProductsList );
        dbHelper = new ProductDBHelper( ProductListActivity.this );

        List<ProductClass> productList = dbHelper.getAllData();
        productTitles = new ArrayList<String>();

        for( ProductClass product: productList){
            productTitles.add(product.getProductDescription() );
        }

        if( productList == null ){
            //enter your message here
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                productTitles
        );

        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick( View view ){
                Intent intent = new Intent( ProductListActivity.this, ProductCreateActivity.class );
                startActivity( intent );
            }
        });
    }

//    TODO: implement these functions
//    public Loader<Cursor> onCreateLoader( int id, Bundle args){
//
//    }
//
//    public void onLoadFinished( Loader<Cursor> loader, Cursor data){
//
//    }
//
//    public void onLoaderReset( Loader<Cursor> loader ){
//
//    }

}
