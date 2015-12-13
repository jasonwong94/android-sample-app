package com.example.jasonwong94.productapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//        implements LoaderManager.LoaderCallbacks<Cursor> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductCreateActivity.class);
                startActivity(intent);
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
