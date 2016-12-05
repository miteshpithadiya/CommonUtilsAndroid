/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.exceptions;

/**
 * @author niravt (Nirav Tukadiya)
 *         <p>
 *         Created on 9/6/16 7:25 PM.
 */

public class InvalidResourceIdException extends Exception {
    public InvalidResourceIdException(String detailMessage) {
        super(detailMessage);
    }

    public InvalidResourceIdException() {
        super("InvalidResourceIdException");
    }
}
