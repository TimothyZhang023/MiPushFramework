package com.xiaomi.push;

import me.pqpo.librarylog4a.Log4a;

/**
 * Created by zts1993 on 2018/3/13.
 */

public class Hooks {
    private static final String TAG = "Hooks";

    public static void hookXM(Object desc, Object name) {
        Log4a.d(TAG, "hookXM1=" + desc.toString());
        Log4a.d(TAG, "hookXM2=" + name.toString());

    }
}
