package com.example.markqueltaylor.macinventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.markqueltaylor.macinventoryapp.data.ItemContract;

public class MainActivity extends AppCompatActivity {

    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mDbHelper = new InventoryDbHelper(this);
        insertData();

        queryData();

    }

    private void queryData() {
        /**
         * Query the database.
         * Always close the cursor when you're done reading from it.
         * This releases all its resources and makes it invalid.
         */
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ItemContract.ItemEntry._ID,
                ItemContract.ItemEntry.PRODUCT_NAME,
                ItemContract.ItemEntry.PRICE,
                ItemContract.ItemEntry.QUANTITY,
                ItemContract.ItemEntry.SUPPLIER_NAME,
                ItemContract.ItemEntry.SUPPLIER_NAME_NUMBER,

        };

        Cursor cursor = db.query(
                ItemContract.ItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        cursor.close();

    }

    private void insertData() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ItemContract.ItemEntry.PRODUCT_NAME, "Rice");
        values.put(ItemContract.ItemEntry.PRICE, "$11.99");
        values.put(ItemContract.ItemEntry.QUANTITY, 1);
        values.put(ItemContract.ItemEntry.SUPPLIER_NAME, "Mojang");
        values.put(ItemContract.ItemEntry.SUPPLIER_NAME_NUMBER, 9999999);

        long newRowId = db.insert(ItemContract.ItemEntry.TABLE_NAME, null, values);

        Log.i("Catalog Activity", "Number of inventory items: " + newRowId);
    }
}
