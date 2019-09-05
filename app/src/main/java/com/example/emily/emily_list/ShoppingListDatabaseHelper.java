package com.example.emily.emily_list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.emily.emily_list.ShoppingListDatabase.*;

class ShoppingListDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "ShoppingList";
    private static final int DB_VERSION = 2;

    ShoppingListDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        final String SQL_CREATE_SHOPPINGLIST_TABLE = "CREATE TABLE " + ListItem.LIST_TABLE  + "(" +
                ListItem._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ListItem.COLUMN_NAME + " TEXT NOT NULL " +
                ");";

        db.execSQL(SQL_CREATE_SHOPPINGLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(ShoppingListDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + ListItem.LIST_TABLE);
        onCreate(db);

    }

}