package com.example.emily.emily_list;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;


import static com.example.emily.emily_list.ShoppingListDatabase.*;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private SQLiteDatabase database;
    private RecyclerView recyclerView;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShoppingListDatabaseHelper dbHelper = new ShoppingListDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        input = findViewById(R.id.itemEditText);

        recyclerView = findViewById(R.id.itemRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ShoppingListAdapter(this, getItems());
        recyclerView.setAdapter(adapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void addItem(View view) {
        String item = input.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(ListItem.COLUMN_NAME, item);

        database.insert(ListItem.LIST_TABLE, null, cv);
        adapter.swapCursor(getItems());

        input.setText("");
    }

        public Cursor getItems() {
            return database.query(
                    ListItem.LIST_TABLE,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
    }

    private void removeItem(long id) {
        Toast.makeText(getBaseContext(), "Item Removed", Toast.LENGTH_SHORT).show();
        database.delete(ListItem.LIST_TABLE, ListItem._ID + "=" + id, null);
        adapter.swapCursor(getItems());
    }

}
