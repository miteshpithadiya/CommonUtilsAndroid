package com.meditab.commonutils.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

/**
 * Created by niravtukadiya on 12/12/16.
 */

public class MtNotificationUtils {

    public static void showExpandableNotification(Context context, String contentTitle, String contentText, String bigTitle, String[] contentArr, int smallIcon, int largeIcon, PendingIntent pendingIntent, boolean showInWatch, NotificationAction[] notificationActions, int notificationId) {

        int id = (int) System.currentTimeMillis();

        if (notificationId != 0) {
            id = notificationId;
        }

        Log.e("MtNotificationUtils", "showExpandableNotification");
        NotificationManager mNotificationManager = getNotificationManager(context);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Building the expandable content
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
//            String lorem = context.getResources().getString(R.string.long_lorem);
//            String[] content = lorem.split("\\.");

            if (bigTitle != null) {
                inboxStyle.setBigContentTitle(bigTitle);
            } else {
                inboxStyle.setBigContentTitle(contentTitle);
            }

            if (contentArr != null) {
                for (String line : contentArr) {
                    inboxStyle.addLine(line);
                }
            } else {
                inboxStyle.addLine(contentText);
            }

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            // Building the notification
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(smallIcon) // notification icon
                    .setContentTitle(contentTitle) // title of notification
                    .setContentText(contentText) // text inside the notification
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon))
                    .setSound(defaultSoundUri)
                    .setPriority(Notification.PRIORITY_HIGH).setDefaults(Notification.DEFAULT_VIBRATE)
                    .setStyle(inboxStyle); // adds the expandable content to the notification

            for (int i = 0; notificationActions != null && i < notificationActions.length; i++) {
                NotificationAction notificationAction = notificationActions[i];

                NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(notificationAction.getActionIcon(), notificationAction.getActionText(), notificationAction.getActionIntent());
                if (showInWatch) {
                    actionBuilder.extend(new NotificationCompat.Action.WearableExtender());
                }

                nBuilder.addAction(actionBuilder.build());
            }

            if (showInWatch) {
                nBuilder.extend(new NotificationCompat.WearableExtender());
            }


            Notification notif = nBuilder.build();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                int smallIconViewId = context.getResources().getIdentifier("right_icon", "id", android.R.class.getPackage().getName());

                if (smallIconViewId != 0) {
                    if (notif.contentView != null)
                        notif.contentView.setViewVisibility(smallIconViewId, View.INVISIBLE);

                    if (notif.headsUpContentView != null)
                        notif.headsUpContentView.setViewVisibility(smallIconViewId, View.INVISIBLE);

                    if (notif.bigContentView != null)
                        notif.bigContentView.setViewVisibility(smallIconViewId, View.INVISIBLE);
                }
            }

            mNotificationManager.notify(id, notif);
        }
    }

    public static void showExpandableNotification(Context context, String contentTitle, String contentText, int smallIcon, int largeIcon, PendingIntent pendingIntent, boolean showInWatch) {
        showExpandableNotification(context, contentTitle, contentText, null, null, smallIcon, largeIcon, pendingIntent, showInWatch, null, 0);
    }

    public static void showExpandableNotification(Context context, String contentTitle, String contentText, int smallIcon, int largeIcon, PendingIntent pendingIntent) {
        showExpandableNotification(context, contentTitle, contentText, null, null, smallIcon, largeIcon, pendingIntent, false, null, 0);
    }

    public static void showExpandableNotification(Context context, String contentTitle, String contentText, int smallIcon, int largeIcon, PendingIntent pendingIntent, int notificationId) {
        showExpandableNotification(context, contentTitle, contentText, null, null, smallIcon, largeIcon, pendingIntent, false, null, notificationId);
    }

    public static void showExpandableNotification(Context context, String contentTitle, String contentText, int smallIcon, int largeIcon, PendingIntent pendingIntent, boolean showInWatch, NotificationAction[] notificationActions) {
        showExpandableNotification(context, contentTitle, contentText, null, null, smallIcon, largeIcon, pendingIntent, showInWatch, notificationActions, 0);
    }

    public static void showExpandableNotification(Context context, String contentTitle, String contentText, int smallIcon, int largeIcon, PendingIntent pendingIntent, NotificationAction[] notificationActions) {
        showExpandableNotification(context, contentTitle, contentText, null, null, smallIcon, largeIcon, pendingIntent, false, notificationActions, 0);
    }

    public static void showExpandableNotification(Context context, String contentTitle, String contentText, int smallIcon, int largeIcon, PendingIntent pendingIntent, NotificationAction[] notificationActions, int notificationId) {
        showExpandableNotification(context, contentTitle, contentText, null, null, smallIcon, largeIcon, pendingIntent, false, notificationActions, notificationId);
    }

    private static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void showBigStyleNotification(Context context, String contentTitle, String contentText, int smallIcon, int largeIcon, PendingIntent pendingIntent, boolean showInWatch, NotificationAction[] notificationActions, int notificationId) {

        int id = (int) System.currentTimeMillis();

        if (notificationId != 0) {
            id = notificationId;
        }

        Log.e("MtNotificationUtils", "showExpandableNotification");
        NotificationManager mNotificationManager = getNotificationManager(context);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Building the expandable content
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();

            bigTextStyle.setBigContentTitle(contentTitle);
            bigTextStyle.bigText(contentText);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            // Building the notification
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(smallIcon) // notification icon
                    .setContentTitle(contentTitle) // title of notification
                    .setContentText(contentText) // text inside the notification
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon))
                    .setSound(defaultSoundUri)
                    .setPriority(Notification.PRIORITY_HIGH).setDefaults(Notification.DEFAULT_VIBRATE)
                    .setStyle(bigTextStyle); // adds the expandable content to the notification

            for (int i = 0; notificationActions != null && i < notificationActions.length; i++) {
                NotificationAction notificationAction = notificationActions[i];

                NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(notificationAction.getActionIcon(), notificationAction.getActionText(), notificationAction.getActionIntent());
                if (showInWatch) {
                    actionBuilder.extend(new NotificationCompat.Action.WearableExtender());
                }

                nBuilder.addAction(actionBuilder.build());
            }

            if (showInWatch) {
                nBuilder.extend(new NotificationCompat.WearableExtender());
            }


            Notification notif = nBuilder.build();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                int smallIconViewId = context.getResources().getIdentifier("right_icon", "id", android.R.class.getPackage().getName());

                if (smallIconViewId != 0) {
                    if (notif.contentView != null)
                        notif.contentView.setViewVisibility(smallIconViewId, View.INVISIBLE);

                    if (notif.headsUpContentView != null)
                        notif.headsUpContentView.setViewVisibility(smallIconViewId, View.INVISIBLE);

                    if (notif.bigContentView != null)
                        notif.bigContentView.setViewVisibility(smallIconViewId, View.INVISIBLE);
                }
            }

            mNotificationManager.notify(id, notif);
        }
    }

    public static class NotificationAction {
        private int actionIcon;
        private String actionText;
        private PendingIntent actionIntent;

        public NotificationAction(int actionIcon, String actionText, PendingIntent actionIntent) {
            this.actionIcon = actionIcon;
            this.actionText = actionText;
            this.actionIntent = actionIntent;
        }

        public int getActionIcon() {
            return actionIcon;
        }

        public void setActionIcon(int actionIcon) {
            this.actionIcon = actionIcon;
        }

        public String getActionText() {
            return actionText;
        }

        public void setActionText(String actionText) {
            this.actionText = actionText;
        }

        public PendingIntent getActionIntent() {
            return actionIntent;
        }

        public void setActionIntent(PendingIntent actionIntent) {
            this.actionIntent = actionIntent;
        }
    }

}
