/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.parent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meditab.commonutils.DefaultMessages;
import com.meditab.commonutils.callbacks.OnItemClickListener;
import com.meditab.commonutils.exceptions.InvalidResourceIdException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p>
 *         Created on 9/6/16 7:15 PM.
 */

public abstract class ParentAdapter<VH extends RecyclerView.ViewHolder, I> extends RecyclerView.Adapter<VH> {

    Context mContext;
    int rowLayoutResourceId;
    RecyclerView recyclerView;
    ArrayList<I> itemList;
    OnItemClickListener onItemClickListener;

    public ParentAdapter(Context mContext, int rowLayoutResourceId, RecyclerView recyclerView, ArrayList<I> itemList, OnItemClickListener onItemClickListener) throws InvalidResourceIdException {
        this.mContext = mContext;
        this.rowLayoutResourceId = rowLayoutResourceId;
        this.recyclerView = recyclerView;
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;

        if (rowLayoutResourceId == 0) {
            throw new InvalidResourceIdException(DefaultMessages.INVALID_LAYOUT_ID);
        }
    }

    public ParentAdapter(Context mContext, int rowLayoutResourceId, RecyclerView recyclerView, ArrayList<I> itemList) throws InvalidResourceIdException {
        this(mContext, rowLayoutResourceId, recyclerView, itemList, null);
    }

    public ParentAdapter(Context mContext, int rowLayoutResourceId, ArrayList<I> itemList) throws InvalidResourceIdException {
        this(mContext, rowLayoutResourceId, null, itemList, null);
    }

    public ParentAdapter(Context mContext, int rowLayoutResourceId, ArrayList<I> itemList, OnItemClickListener onItemClickListener) throws InvalidResourceIdException {
        this(mContext, rowLayoutResourceId, null, itemList, onItemClickListener);
    }

    @Override
    public final VH onCreateViewHolder(final ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(rowLayoutResourceId, parent, false);
        VH viewHolder = getViewHolder(rowView);
        return viewHolder;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public abstract VH getViewHolder(View view);

    public abstract void onBindViewHolder(VH holder, int position, I item);

    @Override
    public final void onBindViewHolder(VH holder, final int position) {
        if (itemList != null && position < itemList.size()) {
            final I item = itemList.get(position);
            ((ViewHolder) holder).bind(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, item);
                    }
                }
            });
            onBindViewHolder(holder, position, item);
        } else {
            throw new ArrayIndexOutOfBoundsException("");
        }
    }

    @Override
    public final int getItemCount() {
        return ((itemList == null) ? 0 : itemList.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public void bind(@NotNull View.OnClickListener clickListener) {
            itemView.setOnClickListener(clickListener);
        }

        public final View getItemView() {
            return itemView;
        }
    }

    public final Context getContext() {
        return mContext;
    }
}
