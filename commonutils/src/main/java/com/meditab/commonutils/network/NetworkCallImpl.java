package com.meditab.commonutils.network;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class for making the service call using retrofit.
 * Created by MiteshP on 06/06/16.
 */
public class NetworkCallImpl<Response> implements NetworkUtils.NetworkCall<Response> {


    /**
     * @param observable The retrofit observable
     * @param listener   The listener of the service call
     */
    @Override
    public void makeNetworkCall(Observable<ResponseObject<Response>> observable,
                                final NetworkUtils.NetworkListener<Response> listener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<Response>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // https://github.com/square/retrofit/issues/1260
                        // When the Throwable passed to the failure callback is an IOException,
                        // this means that it was a network problem (socket timeout, unknown
                        // host, etc.). Any other exception means something broke either in
                        // serializing/deserializing the data or it's a configuration problem.
                        // You can (t instanceof IOException) to determine network problem and
                        // react appropriately. A 401 (or any non-2xx response code) will
                        // actually go to the response callback, because it was a successful
                        // response even though it may not have been a successful operation on
                        // the server. You can check this in onResponse by calling response
                        // .isSuccess().
                        if (e instanceof IOException) {
                            listener.onError("Unable to connect to the network!");
                        } else {
                            listener.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ResponseObject<Response> responseResponseObject) {
                        listener.onSuccess(responseResponseObject);
                    }
                });
    }

    /**
     * @param observable The retrofit observable
     * @param listener   The listener of the service call for meditab related responses
     */
    public void makeNetworkCall(Observable<ResponseObject<Response>> observable,
                                final NetworkUtils.MtabNetworkListener<Response> listener) {

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObject<Response>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // https://github.com/square/retrofit/issues/1260
                        // When the Throwable passed to the failure callback is an IOException,
                        // this means that it was a network problem (socket timeout, unknown
                        // host, etc.). Any other exception means something broke either in
                        // serializing/deserializing the data or it's a configuration problem.
                        // You can (t instanceof IOException) to determine network problem and
                        // react appropriately. A 401 (or any non-2xx response code) will
                        // actually go to the response callback, because it was a successful
                        // response even though it may not have been a successful operation on
                        // the server. You can check this in onResponse by calling response
                        // .isSuccess().

                        e.printStackTrace();

                        if (e instanceof IOException) {
                            listener.onError(NetworkUtils.NETWORK_ERROR_CODE, "Unable to connect to the network!");
                        } else {
                            listener.onError(NetworkUtils.INTERNAL_SERVER_ERROR_CODE, e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ResponseObject<Response> responseResponseObject) {
                        if (responseResponseObject.getCode().equalsIgnoreCase
                                ("1")) {
                            listener.onCodeOneSuccess(responseResponseObject
                                    .getData(), responseResponseObject.getDescription());
                        } else {
                            listener.onOtherCodeSuccess(Integer.parseInt(responseResponseObject
                                            .getCode()), responseResponseObject.getDescription()
                                    , responseResponseObject.getData());
                        }
                    }
                });
    }

    @Override
    public void makeNetworkCallMOS(Observable<ResponseObjectMOS<Response>> observable, final NetworkUtils.MtabNetworkListener<Response> listener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObjectMOS<Response>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // https://github.com/square/retrofit/issues/1260
                        // When the Throwable passed to the failure callback is an IOException,
                        // this means that it was a network problem (socket timeout, unknown
                        // host, etc.). Any other exception means something broke either in
                        // serializing/deserializing the data or it's a configuration problem.
                        // You can (t instanceof IOException) to determine network problem and
                        // react appropriately. A 401 (or any non-2xx response code) will
                        // actually go to the response callback, because it was a successful
                        // response even though it may not have been a successful operation on
                        // the server. You can check this in onResponse by calling response
                        // .isSuccess().

                        e.printStackTrace();

                        if (e instanceof IOException) {
                            listener.onError(NetworkUtils.NETWORK_ERROR_CODE, "Unable to connect to the network!");
                        } else {
                            listener.onError(NetworkUtils.INTERNAL_SERVER_ERROR_CODE, e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ResponseObjectMOS<Response> responseResponseObject) {
                        if (responseResponseObject.getCode().equalsIgnoreCase
                                ("1")) {
                            listener.onCodeOneSuccess(responseResponseObject
                                    .getResponse(), responseResponseObject.getDescription());
                        } else {
                            listener.onOtherCodeSuccess(Integer.parseInt(responseResponseObject
                                            .getCode()), responseResponseObject.getDescription()
                                    , responseResponseObject.getResponse());
                        }
                    }
                });
    }

    @Override
    public void makeNetworkCallEula(Observable<Response> observable, final NetworkUtils
            .MtabNetworkListener<Response> listener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // https://github.com/square/retrofit/issues/1260
                        // When the Throwable passed to the failure callback is an IOException,
                        // this means that it was a network problem (socket timeout, unknown
                        // host, etc.). Any other exception means something broke either in
                        // serializing/deserializing the data or it's a configuration problem.
                        // You can (t instanceof IOException) to determine network problem and
                        // react appropriately. A 401 (or any non-2xx response code) will
                        // actually go to the response callback, because it was a successful
                        // response even though it may not have been a successful operation on
                        // the server. You can check this in onResponse by calling response
                        // .isSuccess().
                        if (e instanceof IOException) {
                            listener.onError(NetworkUtils.NETWORK_ERROR_CODE, "Unable to connect to the network!");
                        } else {
                            listener.onError(NetworkUtils.INTERNAL_SERVER_ERROR_CODE, e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Response response) {
                        listener.onCodeOneSuccess(response, null);
                    }
                });
    }

    /**
     * @param observable The retrofit observable
     * @param listener   The listener of the service call for meditab related responses
     */
    public void makeNetworkCallDotNet(Observable<ResponseObjectDotNet<Response>> observable,
                                      final NetworkUtils.MtabNetworkListener<Response> listener) {

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseObjectDotNet<Response>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // https://github.com/square/retrofit/issues/1260
                        // When the Throwable passed to the failure callback is an IOException,
                        // this means that it was a network problem (socket timeout, unknown
                        // host, etc.). Any other exception means something broke either in
                        // serializing/deserializing the data or it's a configuration problem.
                        // You can (t instanceof IOException) to determine network problem and
                        // react appropriately. A 401 (or any non-2xx response code) will
                        // actually go to the response callback, because it was a successful
                        // response even though it may not have been a successful operation on
                        // the server. You can check this in onResponse by calling response
                        // .isSuccess().
                        if (e instanceof IOException) {
                            listener.onError(NetworkUtils.NETWORK_ERROR_CODE, "Unable to connect to the network!");
                        } else {
                            listener.onError(NetworkUtils.INTERNAL_SERVER_ERROR_CODE, e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ResponseObjectDotNet<Response> responseResponseObject) {
                        if (responseResponseObject.getCode().equalsIgnoreCase("1")) {
                            listener.onCodeOneSuccess(responseResponseObject.getData(), responseResponseObject.getDescription());
                        } else {
                            listener.onOtherCodeSuccess(Integer.parseInt(responseResponseObject
                                            .getCode()), responseResponseObject.getDescription()
                                    , responseResponseObject.getData());
                        }
                    }
                });
    }

}
