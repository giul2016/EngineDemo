package com.lfk.justweengine.Info;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;

/**
 * UI 默认数据类 待修改
 *
 * @author liufengkai
 *         Created by liufengkai on 15/11/27.
 */
public class UIdefaultData {
    private static Context context;

    public static void init(Context context) {
        UIdefaultData.context = context;
    }

    public static final int sprite_default_color_paint = Color.WHITE;

    public static int screenWidth;

    public static int screenHeight;

    public static Point centerHorizontal;


}