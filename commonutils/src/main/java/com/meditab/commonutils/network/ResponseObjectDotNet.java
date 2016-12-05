package com.meditab.commonutils.network;

/**
 * Created by Mitesh on 12/03/16.
 */
public class ResponseObjectDotNet<R> {

    private String code;

    private String description;

    private R data;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public R getData() {
        return data;
    }
}
