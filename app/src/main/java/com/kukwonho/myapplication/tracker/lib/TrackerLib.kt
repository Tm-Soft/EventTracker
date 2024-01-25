package com.example.newproject.util.tracker.lib

import android.util.Log

object Amplitude {
    private var isBoot: Boolean? = null

    fun init() {
        // 초기화
        isBoot = false
        Log.d("whk", "앰플리튜드 초기화")
    }

    fun boot() {
        isBoot = true
        Log.d("whk", "앰플리튜드 부팅")
    }

    fun isBoot(): Boolean {
        if (isBoot != null) {
            return isBoot!!
        }

        return false
    }

    fun sendEvent(message: String) {
        // 이벤트 전송
        Log.d("whk", "앰플리튜드 이벤트 : $message")
    }
}

object FirebaseLog {
    private var isBoot: Boolean? = null

    fun init() {
        // 초기화
        isBoot = false
        Log.d("whk", "파이어베이스 초기화")
    }

    fun boot() {
        isBoot = true
        Log.d("whk", "파이어베이스 부팅 전송")
    }

    fun isBoot(): Boolean {
        if (isBoot != null) {
            return isBoot!!
        }

        return false
    }

    fun sendEvent(message: String) {
        // 이벤트 전송
        Log.d("whk", "파이어베이스 이벤트 : $message")
    }
}

object ChannelTalk {
    private var isBoot: Boolean? = null

    fun init() {
        // 초기화
        isBoot = false
        Log.d("whk", "채널톡 초기화")
    }

    fun boot() {
        isBoot = true
        Log.d("whk", "채널톡 부팅")
    }

    fun isBoot(): Boolean {
        if (isBoot != null) {
            return isBoot!!
        }

        return false
    }

    fun sendEvent(message: String) {
        // 이벤트 전송
        Log.d("whk", "채널톡 이벤트 : $message")
    }
}

object AppsFlyer {
    private var isBoot: Boolean? = null

    fun init() {
        // 초기화
        isBoot = false
        Log.d("whk", "앱스플라이어 초기화")
    }

    fun boot() {
        isBoot = true
        Log.d("whk", "앱스플라이어 부팅")
    }

    fun isBoot(): Boolean {
        if (isBoot != null) {
            return isBoot!!
        }

        return false
    }

    fun sendEvent(message: String) {
        // 이벤트 전송
        Log.d("whk", "앱스플라이어 이벤트 : $message")
    }
}

