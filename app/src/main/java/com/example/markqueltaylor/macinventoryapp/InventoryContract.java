package com.example.markqueltaylor.macinventoryapp;

import android.provider.BaseColumns;

public final class InventoryContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class InventoryEntry implements BaseColumns {

        /** Name of database table for inventory */
        public final static String TABLE_NAME = "inventory";

        /**
         * Unique ID number for the inventory (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the inventory.
         *
         * Type: TEXT
         */
        public final static String PRODUCT_NAME ="name";

        /**
         * Price of the inventory.
         *
         * Type: TEXT
         */
        public final static String PRICE = "price";

        /**
         * Quantity of inventory
         * Type: INTEGER
         */
        public final static String QUANTITY = "quantity";

        /**
         * Supplier name of the inventory.
         *
         * Type: String
         */
        public final static String SUPPLIER_NAME = "supplierName";

        /**
         * Supplier name of the inventory.
         *
         * Type: String
         */
        public final static String SUPPLIER_NAME_NUMBER = "supplierPhoneNumber";

    }

}