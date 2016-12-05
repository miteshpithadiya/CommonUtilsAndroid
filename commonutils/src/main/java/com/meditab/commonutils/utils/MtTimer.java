package com.meditab.commonutils.utils;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 7/1/16 1:00 PM
 */
public class MtTimer {

    private long lShowAlertAfterMillis;
    private long lEndTimerAfterMillis;
    private long lNotifyTickAfterMillis;

    private long lShowAlertForMillis;

    OnTimeoutListener mOnTimeoutListener;


    private InactiveTimer inactiveTimer;

    private boolean mainTimerRunning;
    private boolean subTimerRunning;
    private boolean activityRunning = true;

    public MtTimer(long lEndTimerAfterMillis, long showAlertAfterMillis, long lNotifyTickAfterMillis, OnTimeoutListener mOnTimeoutListener) {
        this.lShowAlertAfterMillis = showAlertAfterMillis;
        // Adding 1 sec to lEndTimerAfterMillis assuming that CountDownTimer will take 1 sec to process as well.
        this.lEndTimerAfterMillis = lEndTimerAfterMillis;
        this.lNotifyTickAfterMillis = lNotifyTickAfterMillis;
        this.mOnTimeoutListener = mOnTimeoutListener;

        //Show Alert for Duration = Total Time (lEndTimerAfterMillis) - Alert shown after(showAlertAfterMillis)
        // i.e. show alert for 60 seconds  for Total time (10 minutes) - alert shown after (9 minutes)
        this.lShowAlertForMillis = (this.lEndTimerAfterMillis - this.lShowAlertAfterMillis) + 500;

    }

    public void restart() {
        removeHandlerCallback();
        start();
    }

    public void stop() {
        stopTimer();
        removeHandlerCallback();
    }

    public void start() {
        stopTimer();
        initTimer();
        setHandlerCallback();
    }


    public void reInitialize(long lEndTimerAfterMillis, long showAlertAfterMillis, long lNotifyTickAfterMillis) {
        this.lShowAlertAfterMillis = showAlertAfterMillis;
        this.lEndTimerAfterMillis = lEndTimerAfterMillis;
        this.lNotifyTickAfterMillis = lNotifyTickAfterMillis;

        //Show Alert for Duration = Total Time (lEndTimerAfterMillis) - Alert shown after(showAlertAfterMillis)
        // i.e. show alert for 60 seconds  for Total time (10 minutes) - alert shown after (9 minutes)
        this.lShowAlertForMillis = (this.lEndTimerAfterMillis - this.lShowAlertAfterMillis) + 500;
        restart();
    }


    private void initTimer() {
        Log.e("initTimer()", "initTimer()");
        inactiveTimer = new InactiveTimer(lShowAlertForMillis, lNotifyTickAfterMillis);
    }


    private void startTimer() {
        Log.e("startTimer()", "startTimer()");
        if (subTimerRunning) {
            stopTimer();
            initTimer();
        }

        subTimerRunning = true;
        inactiveTimer.start();

    }

    public void onActivityResumed() {
        activityRunning = true;
    }

    public void onActivityPaused() {
        activityRunning = false;
    }

    private void stopTimer() {

        Log.e("stopTimer()", "stopTimer()");
        if (subTimerRunning) {
            try {
                inactiveTimer.cancel();
                inactiveTimer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            subTimerRunning = false;
        }
    }

    private void setHandlerCallback() {
        if (mainTimerRunning) {
            removeHandlerCallback();
        }
        handler.postDelayed(handlerCallback, lShowAlertAfterMillis);
        mainTimerRunning = true;
    }

    public void removeHandlerCallback() {
        if (mainTimerRunning) {
            handler.removeCallbacks(handlerCallback);
            mainTimerRunning = false;
        }

    }

    Handler handler = new Handler();
    Runnable handlerCallback = new Runnable() {
        @Override
        public void run() {
            startTimer();
        }
    };

    public boolean isRunning() {
        return mainTimerRunning;
    }


    class InactiveTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public InactiveTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("onTick()", "millisUntilFinished: " + millisUntilFinished);
            String remainingSeconds = String.valueOf((int) (millisUntilFinished / 1000));
            Log.e("onTick()", "seconds: " + remainingSeconds);
            if (mOnTimeoutListener != null && activityRunning) {
                mOnTimeoutListener.onTimeoutTicked(remainingSeconds);
            }
        }

        @Override
        public void onFinish() {
            Log.e("onFinish()", "onFinish()");
            subTimerRunning = false;
            mainTimerRunning = false;
            if (mOnTimeoutListener != null) {
                mOnTimeoutListener.onTimeoutCompleted();
            }
        }
    }


    public interface OnTimeoutListener {

        void onTimeoutTicked(String remainingSeconds);

        void onTimeoutCompleted();
    }
}
