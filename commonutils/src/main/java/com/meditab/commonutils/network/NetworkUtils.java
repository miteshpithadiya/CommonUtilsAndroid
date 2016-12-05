package com.meditab.commonutils.network;

import rx.Observable;

/**
 * Created by Meditab on 06/06/16.
 */
public interface NetworkUtils {

    int NETWORK_ERROR_CODE = 503;
    int INTERNAL_SERVER_ERROR_CODE = 500;

    interface NetworkCall<Response> {

        void makeNetworkCall(Observable<ResponseObject<Response>> observable,
                             NetworkListener<Response> listener);

        void makeNetworkCall(Observable<ResponseObject<Response>> observable,
                             MtabNetworkListener<Response> listener);

        void makeNetworkCallMOS(Observable<ResponseObjectMOS<Response>> observable,
                                MtabNetworkListener<Response> listener);

        void makeNetworkCallEula(Observable<Response> observable, MtabNetworkListener<Response>
                listener);

    }

    interface NetworkListener<Response> {
        void onError(String message);

        void onSuccess(ResponseObject<Response> responseResponseObject);
    }

    interface MtabNetworkListener<Response> {
        void onCodeOneSuccess(Response data, String strDescription);

        void onError(int code, String message);

        void onOtherCodeSuccess(int code, String description, Response data);
    }
}
