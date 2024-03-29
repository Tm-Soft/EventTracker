package com.kukwonho.myapplication.keyboard_observer

import android.app.Activity
import android.app.Application
import android.os.Bundle

abstract class CrossKeyboardActivityLifecycleCallbacks(private val targetActivity: Activity) : Application.ActivityLifecycleCallbacks {

    abstract fun onActivityCreated()
    abstract fun onActivityDestroyed()


    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        if (targetActivity == activity) {
            onActivityCreated()
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (targetActivity == activity) {
            targetActivity.application.unregisterActivityLifecycleCallbacks(this)
            onActivityDestroyed()
        }
    }

    override fun onActivityPaused(activity: Activity) {
        // no-op
    }

    override fun onActivityResumed(activity: Activity) {
        // no-op
    }

    override fun onActivityStarted(activity: Activity) {
        // no-op
    }

    override fun onActivityStopped(activity: Activity) {
        // no-op
    }

}