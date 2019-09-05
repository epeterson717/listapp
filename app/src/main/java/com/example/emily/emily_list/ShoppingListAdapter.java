package com.example.emily.emily_list;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.emily.emily_list.ShoppingListDatabase.*;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public ShoppingListAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class ShoppingViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName;

        public ShoppingViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.textView_item_name);
        }
    }

    @Override
    public ShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate (R.layout.item, parent, false);
        return new ShoppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(ListItem.COLUMN_NAME));
        long id = mCursor.getLong(mCursor.getColumnIndex(ShoppingListDatabase.ListItem._ID));
        holder.itemName.setText(name);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }



}
