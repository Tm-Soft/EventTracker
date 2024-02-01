package com.kukwonho.myapplication.keyboard_observer

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewTreeObserver
import kotlin.math.absoluteValue

abstract class CrossBaseKeyboardObserver (
    private val activity: Activity,
    private val relativeHeight: Int
) {
    private val decorView = activity.window.decorView
    private var lastIsShow = false

    private lateinit var onKeyboardListener: OnKeyboardListener

    /**
     * 네비게이션을 제스쳐, 힌트 표시 X 로 설정한 기기에서 [originalWindowHeight] 변수를 초기화 할 때 키보드가 띄어진 상태의 Height 를 저장하는 경우가 존재함.
     * 해당 보완 코드는 하단에 작성함.
     */
    private val originalWindowHeight by lazy { getWindowHeight() }

    private var lastWindowHeight = getWindowHeight()

    private val onGlobalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener { onGlobalLayout() }

    private val softKeyButtonHeight = run {
        val applicationDisplayHeight = DisplayMetrics().apply {
            this@CrossBaseKeyboardObserver.activity.windowManager.defaultDisplay.getMetrics(this)
        }.heightPixels
        val realDisplayHeight = DisplayMetrics().apply {
            this@CrossBaseKeyboardObserver.activity.windowManager.defaultDisplay.getRealMetrics(this)
        }.heightPixels
        realDisplayHeight - applicationDisplayHeight
    }

    private val getStatusBarHeight by lazy { Rect().apply { decorView.getWindowVisibleDisplayFrame(this) }.top }


    private fun getWindowHeight() = Rect().apply { decorView.getWindowVisibleDisplayFrame(this) }.bottom

    private fun registerActivityLifecycleCallbacks() {
        activity.application.registerActivityLifecycleCallbacks(object : CrossKeyboardActivityLifecycleCallbacks(activity) {
            override fun onActivityCreated() {}

            override fun onActivityDestroyed() {
                this@CrossBaseKeyboardObserver.onActivityDestroyed()
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
        })
    }

    protected fun internalListen(onKeyboardListener: OnKeyboardListener) {
        registerActivityLifecycleCallbacks()
        this.onKeyboardListener = onKeyboardListener
        decorView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
    }

    private var reverseFlag = false


    private fun onGlobalLayout() {
        val currentWindowHeight = getWindowHeight()
        if (isSoftKeyChanged()) {
            lastWindowHeight = currentWindowHeight
            return
        }
        lastWindowHeight = currentWindowHeight

        /**
         * originalWindowHeight 가 Keyboard 띄어진 상태의 크기로 되었을 떄를 대비한 리버스 플래그
         */
        reverseFlag = originalWindowHeight != (relativeHeight + getStatusBarHeight)

        var isShow = originalWindowHeight != currentWindowHeight

        if (reverseFlag) {
            isShow = !isShow
        }

        lastIsShow = isShow
        onKeyboardListener.onKeyboardChange(isShow)
    }

    private fun isSoftKeyChanged() = ((lastWindowHeight - getWindowHeight()).absoluteValue) == softKeyButtonHeight

    protected open fun onActivityDestroyed() {
        decorView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
    }

    interface OnKeyboardListener {
        fun onKeyboardChange(isShow: Boolean)
    }
}