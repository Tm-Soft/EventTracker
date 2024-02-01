package com.kukwonho.myapplication.keyboard_observer

import android.app.Activity

class CrossKeyboardObserver(
    activity: Activity,
    relativeHeight: Int
) : CrossBaseKeyboardObserver(activity, relativeHeight) {
    fun listen(onKeyboardListener: OnKeyboardListener) {
        internalListen(onKeyboardListener)
    }

    fun listen(action: (Boolean) -> Unit) {
        internalListen(object : OnKeyboardListener {
            override fun onKeyboardChange(isShow: Boolean) {
                action(isShow)
            }
        })
    }
}