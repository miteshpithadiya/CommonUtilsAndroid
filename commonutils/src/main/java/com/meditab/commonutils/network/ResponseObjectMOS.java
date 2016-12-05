package com.meditab.commonutils.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Mitesh on 12/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObjectMOS<R> {

    private String code;

    private String description;

    private R response;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public R getResponse() {
        return response;
    }
}
