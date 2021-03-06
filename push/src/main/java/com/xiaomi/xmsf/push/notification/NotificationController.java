package com.xiaomi.xmsf.push.notification;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;

import com.xiaomi.xmsf.R;

import static top.trumeet.common.utils.NotificationUtils.getChannelIdByPkg;

/**
 * Created by Trumeet on 2018/1/25.
 */

public class NotificationController {
    private static final String ID_GROUP_APPLICATIONS = "applications";

    @TargetApi(26)
    public static NotificationChannelGroup createGroup (@NonNull Context context) {
        return new NotificationChannelGroup(ID_GROUP_APPLICATIONS, Html.fromHtml(context.getString(R.string.notification_group_applications)));
    }

    @TargetApi(26)
    public static NotificationChannel createChannelWithPackage (@NonNull String packageName,
                                                                @NonNull CharSequence name) {
        NotificationChannel channel = new NotificationChannel(getChannelIdByPkg(packageName),
                name, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableVibration(true);
        channel.setGroup(ID_GROUP_APPLICATIONS);
        return channel;
    }

    public static void registerChannelIfNeeded (Context context, String packageName) {
        if (Build.VERSION.SDK_INT < 26)
            return;
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence name;
        try {
            name = context.getPackageManager().getApplicationInfo(packageName, 0)
                    .loadLabel(context.getPackageManager());
        } catch (PackageManager.NameNotFoundException ignored) {
            return;
        }

        if (manager.getNotificationChannel(getChannelIdByPkg(packageName))
                == null) {
            manager.createNotificationChannel(createChannelWithPackage(packageName, name));
        }
    }
}
