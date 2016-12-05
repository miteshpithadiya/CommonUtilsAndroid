package com.meditab.commonutils.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Mitesh on 12/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObject<R> {

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
