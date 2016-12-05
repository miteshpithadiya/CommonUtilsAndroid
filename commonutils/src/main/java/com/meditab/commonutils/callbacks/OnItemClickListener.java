/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.callbacks;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p>
 *         Created on 9/6/16 7:16 PM.
 */

public interface OnItemClickListener<T> {
    void onItemClick(int position, T t);
}
