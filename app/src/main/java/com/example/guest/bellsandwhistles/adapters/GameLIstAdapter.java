package com.example.guest.bellsandwhistles.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.bellsandwhistles.R;
import com.example.guest.bellsandwhistles.ui.MainActivity;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/12/16.
 */
public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ItemViewHolder> {
    private ArrayList<String> mTypes = new ArrayList<>();
    Context mContext;

    public GameListAdapter(Context context, ArrayList<String> types) {
        mContext = context;
        mTypes = types;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.bindType(mTypes.get(position));
    }



    @Override
    public int getItemCount() {
        return mTypes.size();
    }

    public Context getContext() {
        return mContext;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.Type) TextView mName;
        private Context mContext;

        public Context getContext() {
            return mContext;
        }

        public ItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnTouchListener(new OnSwipeTouchListener(mContext) {
                public void onSwipeTop() {
                    Toast.makeText(mContext, "top", Toast.LENGTH_SHORT).show();
                }

                public void onSwipeRight() {
                    itemView.animate().translationX(100.0f);
//                    removeAt(getAdapterPosition());
                    Toast.makeText(mContext, "right", Toast.LENGTH_SHORT).show();
                }

                public void onSwipeLeft() {
                    itemView.animate().translationX(-100.0f);
//                    removeAt(getAdapterPosition());
                    Toast.makeText(mContext, "left", Toast.LENGTH_SHORT).show();
                }

                public void onSwipeBottom() {
                    Toast.makeText(mContext, "bottom", Toast.LENGTH_SHORT).show();
                }

                public void onClick(){
                    itemView.animate().alpha(0.0f);
                    removeAt(getAdapterPosition());
                    Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
                }

                public void onDoubleClick() {
                    Toast.makeText(mContext, "Double clicked", Toast.LENGTH_SHORT).show();
                }

                public void onLongClick() {
                    Toast.makeText(mContext, "Long clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bindType(String type) {
            mName.setText(type);

        }

    }

    public void removeAt(int position) {
        mTypes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mTypes.size());
    }
}
