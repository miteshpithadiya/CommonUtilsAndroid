/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.widgets.easyrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.meditab.commonutils.R;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 6/23/16 8:22 PM
 */
public class DefaultItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public DefaultItemDecoration(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider, null);
        } else {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
