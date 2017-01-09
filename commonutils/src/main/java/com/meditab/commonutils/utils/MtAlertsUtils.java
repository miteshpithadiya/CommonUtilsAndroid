/**
 * Class for all types of alerts.
 */
package com.meditab.commonutils.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.meditab.commonutils.R;

/**
 * Created by niravt on 12/21/2016.
 */
public class MtAlertsUtils {

    Context mContext;

    /**
     * AlertDialog creation generic method
     *
     * @param strTitle:                title of the alertDialog
     * @param strMessage:              message inside alert dialog
     * @param cancelable:              dialog is cancelable on touch or not.
     * @param strPositiveButtonText:   text of positive button
     * @param strNegativeButtonText:   text of negative button
     * @param strNeutralButtonText:    text of neutral button
     * @param onPositiveClickListener: positive click listener
     * @param onNegativeClickListener: negative click listener
     * @param onNeutralClickListener:  neutral click listener
     * @return alertDialog
     */
    public static AlertDialog showAlert(Context context, String strTitle, Html strMessage, boolean cancelable,
                                        String strPositiveButtonText,
                                        String strNegativeButtonText,
                                        String strNeutralButtonText, final DialogOnClickListener
                                                onPositiveClickListener, final DialogOnClickListener
                                                onNegativeClickListener, final DialogOnClickListener
                                                onNeutralClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(strTitle);
        builder.setMessage((CharSequence) strMessage);

        if (strPositiveButtonText != null) {
            builder.setPositiveButton(strPositiveButtonText, null);
        }

        if (strNegativeButtonText != null) {
            builder.setNegativeButton(strNegativeButtonText, null);
        }

        if (strNeutralButtonText != null) {
            builder.setNeutralButton(strNeutralButtonText, null);
        }

        builder.setCancelable(cancelable);

        final AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnPositive = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
                Button btnNeutral = alert.getButton(AlertDialog.BUTTON_NEUTRAL);


                if (null != btnPositive) {
                    btnPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onPositiveClickListener != null) {
                                onPositiveClickListener.onClick(alert, AlertDialog.BUTTON_POSITIVE);
                            } else {
                                alert.dismiss();
                            }
                        }

                    });
                    //Method for activating clickListener of Positive Button
                }
                if (null != btnNegative) {
                    btnNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onNegativeClickListener != null) {
                                onNegativeClickListener.onClick(alert, AlertDialog.BUTTON_NEGATIVE);
                            } else {
                                alert.dismiss();
                            }
                        }
                    });
                }
                if (null != btnNeutral) {
                    btnNeutral.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onNeutralClickListener != null) {
                                onNeutralClickListener.onClick(alert, AlertDialog.BUTTON_NEUTRAL);
                            } else {
                                alert.dismiss();
                            }
                        }
                    });
                }
            }
        });
        alert.show();

        return alert;
    }

    public static AlertDialog showAlert(Context context, String strTitle, String strMessage, boolean cancelable,
                                        String strPositiveButtonText,
                                        String strNegativeButtonText,
                                        String strNeutralButtonText, final DialogOnClickListener
                                                onPositiveClickListener, final DialogOnClickListener
                                                onNegativeClickListener, final DialogOnClickListener
                                                onNeutralClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);

        if (strPositiveButtonText != null) {
            builder.setPositiveButton(strPositiveButtonText, null);
        }

        if (strNegativeButtonText != null) {
            builder.setNegativeButton(strNegativeButtonText, null);
        }

        if (strNeutralButtonText != null) {
            builder.setNeutralButton(strNeutralButtonText, null);
        }

        builder.setCancelable(cancelable);

        final AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnPositive = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
                Button btnNeutral = alert.getButton(AlertDialog.BUTTON_NEUTRAL);


                if (null != btnPositive) {
                    btnPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onPositiveClickListener != null) {
                                onPositiveClickListener.onClick(alert, AlertDialog.BUTTON_POSITIVE);
                            } else {
                                alert.dismiss();
                            }
                        }

                    });
                    //Method for activating clickListener of Positive Button
                }
                if (null != btnNegative) {
                    btnNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onNegativeClickListener != null) {
                                onNegativeClickListener.onClick(alert, AlertDialog.BUTTON_NEGATIVE);
                            } else {
                                alert.dismiss();
                            }
                        }
                    });
                }
                if (null != btnNeutral) {
                    btnNeutral.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onNeutralClickListener != null) {
                                onNeutralClickListener.onClick(alert, AlertDialog.BUTTON_NEUTRAL);
                            } else {
                                alert.dismiss();
                            }
                        }
                    });
                }
            }
        });
        alert.show();

        return alert;
    }

    /**
     * @param strTitle:              title of the alertDialog
     * @param strMessage:            message inside alert dialog
     * @param cancelable:            dialog is cancelable on touch or not.
     * @param strPositiveButtonText: text  of positive button
     * @param strNegativeButtonText: text  of negative button
     * @param positiveClickListener: Click listener for positive button
     * @param negativeClickListener: Click listener for negative button
     * @return alertDialog
     */
    public static AlertDialog showAlert(Context context, String strTitle, String strMessage, boolean cancelable,
                                        String strPositiveButtonText,
                                        String strNegativeButtonText, DialogOnClickListener
                                                positiveClickListener, DialogOnClickListener
                                                negativeClickListener) {
        return showAlert(context, strTitle, strMessage, cancelable, strPositiveButtonText
                , strNegativeButtonText, null, positiveClickListener, negativeClickListener, null);
    }

    /**
     * @param strTitle:             title of the alertDialog
     * @param strMessage:           message inside alert dialog
     * @param cancelable:           dialog is cancelable on touch or not.
     * @param strNeutralButtonText: text  of neutral button
     * @param alertClickListener:   neutral button click listener.
     * @return alertDialog
     */
    public static AlertDialog showAlert(Context context, String strTitle, String strMessage,
                                        boolean cancelable, String strNeutralButtonText, DialogOnClickListener alertClickListener) {
        return showAlert(context, strTitle, strMessage, cancelable, null, null,
                strNeutralButtonText, null, null, alertClickListener);
    }


    /**
     * @param strTitle:             title of the alertDialog
     * @param strMessage:           message inside alert dialog
     * @param strNeutralButtonText: text  of neutral button
     * @param alertClickListener:   neutral button click listener.
     * @return alertDialog
     */
    public static AlertDialog showAlert(Context context, String strTitle, String strMessage,
                                        String strNeutralButtonText, DialogOnClickListener alertClickListener) {
        return showAlert(context, strTitle, strMessage, false, null, null, strNeutralButtonText,
                null, null, alertClickListener);
    }

    public static AlertDialog showAlert(Context context, String strMessage) {

        String defaultTitle = context.getString(R.string.alertDefaultTitle);
        String defaultButtonText = context.getString(R.string.alertDefaultButtonText);

        return showAlert(context, defaultTitle, strMessage, false, null, null, defaultButtonText,
                null, null, null);
    }


    public static AlertDialog showAlert(Context context, String strTitle, String strMessage) {

        String defaultButtonText = context.getString(R.string.alertDefaultButtonText);
        return showAlert(context, strTitle, strMessage, false, null, null, defaultButtonText,
                null, null, null);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when an
     * item on the dialog is clicked..
     */
    public interface DialogOnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog The dialog that received the click.
         */
        void onClick(DialogInterface dialog, int which);
    }
}
