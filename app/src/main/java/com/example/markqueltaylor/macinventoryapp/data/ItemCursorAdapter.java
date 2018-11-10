package com.example.markqueltaylor.macinventoryapp.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markqueltaylor.macinventoryapp.R;

/**
 * {@link ItemCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of item data as its data source. This adapter knows
 * how to create list items for each row of item data in the {@link Cursor}.
 */
public class ItemCursorAdapter extends CursorAdapter {


    /**
     * Constructs a new {@link ItemCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODO: Fill out this method and return the list item view (instead of null)
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the item data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the product_productName for the current item can be set on the product_productName TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    public void bindView(View view, final Context context, final Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.product_name);
        final TextView quantity = (TextView) view.findViewById(R.id.quantity);
        TextView price = (TextView) view.findViewById(R.id.price);
        Button sellOneBtn = (Button) view.findViewById(R.id.saleBtn);

        // Find the columns of product attributes that we're interested in
        String nameColumnIndex = String.valueOf(cursor.getColumnIndex(ItemContract.ItemEntry.PRODUCT_NAME));
        int quantityColumnIndex = cursor.getColumnIndexOrThrow(ItemContract.ItemEntry.QUANTITY);
        String priceColumnIndex = String.valueOf(cursor.getColumnIndexOrThrow(ItemContract.ItemEntry.PRICE));

        String productName = cursor.getString(Integer.parseInt(nameColumnIndex));
        final int productQuantity = Integer.parseInt(cursor.getString(quantityColumnIndex));
        String productPrice = cursor.getString(Integer.parseInt(priceColumnIndex));
        // Update the TextViews with the attributes for the current Product
        name.setText(String.valueOf(productName));
        price.setText("$" + String.valueOf(productPrice));
        quantity.setText(context.getString(R.string.quantity_text) + " " + String.valueOf(productQuantity));

        if (productQuantity == 0) {
            Toast.makeText(context, R.string.no_items_to_sell_toast, Toast.LENGTH_SHORT).show();
            Log.i(context.getString(R.string.hint_item_quantity), String.valueOf(productQuantity));
            return;
        } else {
            final String id = cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry._ID));
            sellOneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Uri currentProductUri = ContentUris.withAppendedId(ItemContract.ItemEntry.CONTENT_URI, Long.parseLong(id));
                    ContentValues values = new ContentValues();
                    values.put(ItemContract.ItemEntry.QUANTITY, productQuantity - 1);
                    context.getContentResolver().update(currentProductUri, values, null, null);
                }
            });
        }
    }
}