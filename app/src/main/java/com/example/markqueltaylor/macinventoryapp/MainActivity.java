package com.example.markqueltaylor.macinventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private InventoryDbHelper inventoryDbHelper;

    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Cursor queryData(){
        /**
         * Query the database.
         * Always close the cursor when you're done reading from it.
         * This releases all its resources and makes it invalid.
         */
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.PRODUCT_NAME,
                InventoryContract.InventoryEntry.PRICE,
                InventoryContract.InventoryEntry.SUPPLIER_NAME,
                InventoryContract.InventoryEntry.SUPPLIER_NAME_NUMBER,

        };

        Cursor cursor = db.query(
                InventoryContract.InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        cursor.close();

        return cursor;
    }

    private void insertData(){

        SQLiteDatabase db = inventoryDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(InventoryContract.InventoryEntry.PRODUCT_NAME, "Rice");
        values.put(InventoryContract.InventoryEntry.PRICE, "$11.99");
        values.put(InventoryContract.InventoryEntry.QUANTITY, "1");
        values.put(InventoryContract.InventoryEntry.SUPPLIER_NAME, "Mojang");
        values.put(InventoryContract.InventoryEntry.PRODUCT_NAME, "9999999999");

    }
}
