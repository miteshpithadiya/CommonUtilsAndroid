package com.meditab.commonutils.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.meditab.commonutils.exceptions.InvalidDateFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Description: Date picker to be used in applications developed by Meditab Software.
 * Created by Mitesh on 20/05/16.
 */
public class MtDatePicker extends DialogFragment {

    public static final String REQUEST_CODE = "request code";
    private static final String YEAR = "year";
    private static final String MONTH_OF_YEAR = "monthOfYear";
    private static final String DAY_OF_MONTH = "dayOfMonth";
    private DatePickerDialog.OnDateSetListener mListenerInt;
    private OnDateSetListenerString mListenerString;
    private String mFormat;
    private DatePickerDialog mDatePickerDialog;

    public MtDatePicker() {

    }

    /**
     * @param year        The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     * @param dayOfMonth  The initial day of the dialog.
     */
    public static MtDatePicker newInstance(int year, int monthOfYear, int dayOfMonth) {
        final MtDatePicker meditabDatePicker = new MtDatePicker();

        final Bundle args = new Bundle();
        args.putInt(YEAR, year);
        args.putInt(MONTH_OF_YEAR, monthOfYear);
        args.putInt(DAY_OF_MONTH, dayOfMonth);

        meditabDatePicker.setArguments(args);

        return meditabDatePicker;
    }


    /**
     * @param date   The date in string format.
     * @param format The format of the date.
     */
    public static MtDatePicker newInstance(String date, String format) throws ParseException {
        final MtDatePicker meditabDatePicker = new MtDatePicker();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date dateInstance = simpleDateFormat.parse(date);

        final Bundle args = new Bundle();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateInstance);

        args.putInt(YEAR, calendar.get(Calendar.YEAR));
        args.putInt(MONTH_OF_YEAR, calendar.get(Calendar.MONTH));
        args.putInt(DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));

        meditabDatePicker.setArguments(args);

        return meditabDatePicker;
    }

    /**
     * @param calendar The calendar object of the initial date to be displayed.
     */
    public static MtDatePicker newInstance(Calendar calendar) {
        final MtDatePicker meditabDatePicker = new MtDatePicker();

        final Bundle args = new Bundle();
        args.putInt(YEAR, calendar.get(Calendar.YEAR));
        args.putInt(MONTH_OF_YEAR, calendar.get(Calendar.MONTH));
        args.putInt(DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));

        meditabDatePicker.setArguments(args);

        return meditabDatePicker;
    }


    /**
     * @param date The initial date to be selected.
     */
    public static MtDatePicker newInstance(Date date, int intRequestCode) {
        final MtDatePicker meditabDatePicker = new MtDatePicker();
        final Bundle args = new Bundle();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        args.putInt(YEAR, calendar.get(Calendar.YEAR));
        args.putInt(REQUEST_CODE, intRequestCode);
        args.putInt(MONTH_OF_YEAR, calendar.get(Calendar.MONTH));
        args.putInt(DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        return meditabDatePicker;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle bundle = getArguments();
        final int intRequestId = bundle.getInt(REQUEST_CODE);


        if (null != mListenerString) {
            mListenerInt = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    Date date = calendar.getTime();
                    mListenerString.onDateSet(date, intRequestId);
                }
            };
        }

        mDatePickerDialog = new DatePickerDialog(getActivity(), mListenerInt,
                bundle.getInt(YEAR), bundle.getInt(MONTH_OF_YEAR), bundle.getInt(DAY_OF_MONTH));
        return mDatePickerDialog;
    }

    public void setMinDate(Date date) {
        mDatePickerDialog.getDatePicker().setMinDate(date.getTime());
    }

    public void setMinDate(long millis) {


        String strDate = DateFormatter.convertMillisToStringDate(millis);
        Date date = DateFormatter.convertStringDateToDate(strDate);
        mDatePickerDialog.getDatePicker().setMinDate(date.getTime());
    }

    public void setMaxDate(long millis) {
        String strDate = DateFormatter.convertMillisToStringDate(millis);
        Date date = DateFormatter.convertStringDateToDate(strDate);
        mDatePickerDialog.getDatePicker().setMaxDate(date.getTime());
    }

    public void setMinDate(String strDate, String strFormat) {

        Date date = null;
        try {
            date = DateFormatter.convertStringDateToDate(strDate, strFormat);
        } catch (InvalidDateFormatException e) {
            e.printStackTrace();
        }
        mDatePickerDialog.getDatePicker().setMinDate(date.getTime());
    }

    public void setMaxDate(Date date) {
        mDatePickerDialog.getDatePicker().setMaxDate(date.getTime());
    }

    public void setMaxDate(String strDate, String strFormat) {

        Date date = null;
        try {
            date = DateFormatter.convertStringDateToDate(strDate, strFormat);
        } catch (InvalidDateFormatException e) {
            e.printStackTrace();
        }
        mDatePickerDialog.getDatePicker().setMaxDate(date.getTime());
    }


    /**
     * @param listener How the parent is notified that the date is set.
     */
    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener listener) {
        this.mListenerInt = listener;
    }

    /**
     * @param listener How the parent is notified that the date is set.
     * @param format   The format of the returned string date.
     */
    public void setOnDateSetListenerString(OnDateSetListenerString listener, String format) {
        this.mListenerString = listener;
        this.mFormat = format;
    }

    /**
     * The callback used to indicate the user is done filling in the date.
     */
    public interface OnDateSetListenerString {

        /**
         * @param date The date that was set.
         */

        void onDateSet(Date date, int intRequestCode);

        void onDateCancelled(Date date);
    }
}