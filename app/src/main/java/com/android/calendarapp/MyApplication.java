package com.android.calendarapp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.android.calendarapp.eventsHandling.EventManagement;

public class MyApplication extends Application {

    private int activityReferences = 0;
    private boolean isActivityChangingConfigurations = false;

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

            @Override
            public void onActivityStarted(Activity activity) {
                if (++activityReferences == 1 && !isActivityChangingConfigurations) {
                    // aplikace v popredi
                    onAppForegrounded();
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                isActivityChangingConfigurations = activity.isChangingConfigurations();
                if (--activityReferences == 0 && !isActivityChangingConfigurations) {
                    // aplikace na pozadi
                    onAppBackgrounded();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

            @Override
            public void onActivityDestroyed(Activity activity) {}
        });
    }

    private void onAppForegrounded() {

        Log.d(TAG, "Aplikace je v popředí");
    }

    private void onAppBackgrounded() {
        EventManagement.getInstance().serialize(getApplicationContext());
        Log.d(TAG, "Aplikace je v pozadí");
    }
}