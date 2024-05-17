package com.android.calendarapp;

import android.content.Context;
import android.content.Intent;

public class GeneralActivityCommands {
    //nechce se mi tuhle mrdku pokazdy psat znova, tak tu mam toto :) (kdyz pouzivam jen toto bez pridavani extras)
    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
