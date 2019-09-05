package com.example.emily.emily_list;
import android.provider.BaseColumns;

public class ShoppingListDatabase {

    public static final class ListItem implements BaseColumns {
        public final static String LIST_TABLE = "ShoppingList";
        public final static String COLUMN_NAME = "name";
    }

}