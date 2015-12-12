package com.example.jasonwong94.productapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasonwong94 on 15-11-17.
 */
public class ProductDBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + "( " +
                    FeedEntry.COLUMN_NAME_PRODUCT_ID + " INT, " +
                    FeedEntry.COLUMN_NAME_PRODUCT_TITLE + " TEXT, " +
                    FeedEntry.COLUMN_NAME_PRODUCT_DESCRIPTION + " TEXT, " +
                    FeedEntry.COLUMN_NAME_PRODUCT_COST + " MONEY, " +
                    FeedEntry.COLUMN_NAME_PRODUCT_QUANTITY + " INT, " +
                    FeedEntry.COLUMN_NAME_PRODUCT_TOTAL_COST + " MONEY, " +
                    FeedEntry.COLUMN_NAME_PRODUCT_CREATED + " DATETIME, " +
                    "PRIMARY KEY (" + FeedEntry.COLUMN_NAME_PRODUCT_ID + ")" +
                    ") ";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    private static final String SQL_QUERY =
            "SELECT * FROM " + FeedEntry.TABLE_NAME;

    private static final String SQL_DELETE =
            "DELETE FROM " + FeedEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Products.db";

    public ProductDBHelper( Context context ){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static abstract class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_NAME_PRODUCT_ID= "productId";
        public static final String COLUMN_NAME_PRODUCT_TITLE= "productTitle";
        public static final String COLUMN_NAME_PRODUCT_DESCRIPTION= "productDescription";
        public static final String COLUMN_NAME_PRODUCT_QUANTITY= "productQuantity";
        public static final String COLUMN_NAME_PRODUCT_COST= "productCost";
        public static final String COLUMN_NAME_PRODUCT_TOTAL_COST= "productTotalCost";
        public static final String COLUMN_NAME_PRODUCT_CREATED= "productCreatedOn";

//        implement this later
        public static final String COLUMN_NAME_PRODUCT_UPDATED= "productUpdatedOn";
        public static final String COLUMN_NAME_IMAGE_FILE="productImageFileName";
    }

    public void onCreate( SQLiteDatabase db ){
        db.execSQL(SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL( SQL_DELETE_TABLE );
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertProduct( String title, String description, String cost, String quantity, String totalCost, String dateCreated ){
        db = this.getWritableDatabase();
        ContentValues productContent = new ContentValues();
        productContent.put( FeedEntry.COLUMN_NAME_PRODUCT_TITLE, title);
        productContent.put( FeedEntry.COLUMN_NAME_PRODUCT_DESCRIPTION, description );
        productContent.put( FeedEntry.COLUMN_NAME_PRODUCT_QUANTITY, quantity );
        productContent.put( FeedEntry.COLUMN_NAME_PRODUCT_COST, cost );
        productContent.put( FeedEntry.COLUMN_NAME_PRODUCT_TOTAL_COST, totalCost);
        productContent.put(FeedEntry.COLUMN_NAME_PRODUCT_CREATED, dateCreated);

    }

    public ProductClass getDataById( int id ){
        db = this.getReadableDatabase();
        final String GET_ALL = SQL_QUERY + " WHERE " + FeedEntry.COLUMN_NAME_PRODUCT_ID + " = " + id;
        Cursor cursor = db.rawQuery( GET_ALL, null);
        if( cursor != null ){
            return new ProductClass(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getDouble(5),
                cursor.getString(6)
            );
        }
        return null;
    }

    public List<ProductClass> getAllData(){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( SQL_QUERY , null );
        ArrayList results = new ArrayList<ProductClass>();

        if ( cursor != null ){
            do{
                ProductClass product = new ProductClass(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getDouble(5),
                    cursor.getString(6)

                );
                results.add( product );
            }
            while( cursor.moveToNext());
        }
        return results;
    }

    public void deleteProduct( int id ){
        db = this.getWritableDatabase();
        final String DELETE_ID = SQL_DELETE + " WHERE " + FeedEntry.COLUMN_NAME_PRODUCT_ID + " = " + id;
        db.rawQuery( DELETE_ID, null );
    }

    public void updateProduct( int id, String title, String description, Double cost, Integer quantity, Double totalCost ){
        db= this.getWritableDatabase();
        final String UPDATE =
                " UPDATE " + FeedEntry.TABLE_NAME +
                " SET " +
                        FeedEntry.COLUMN_NAME_PRODUCT_TITLE + " = " + title + ", " +
                        FeedEntry.COLUMN_NAME_PRODUCT_DESCRIPTION + " = " + description + ", " +
                        FeedEntry.COLUMN_NAME_PRODUCT_COST + " = " + cost + ", " +
                        FeedEntry.COLUMN_NAME_PRODUCT_QUANTITY + " = " + quantity + ", " +
                        FeedEntry.COLUMN_NAME_PRODUCT_TOTAL_COST + " = " + totalCost +
                " WHERE " + FeedEntry.COLUMN_NAME_PRODUCT_ID + " = " + id;
        db.rawQuery( UPDATE, null );
    }
}
