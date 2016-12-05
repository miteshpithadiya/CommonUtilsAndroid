package com.meditab.commonutils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author
 * Mitesh Pithadiya
 * Meditab Software Inc.
 * miteshp@meditab.com
 * Created on 03/11/16 1:41 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapViewClass {
    String value();
}
