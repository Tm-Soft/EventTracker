package com.example.newproject.util.tracker

import com.example.newproject.util.tracker.lib.Amplitude
import com.example.newproject.util.tracker.lib.AppsFlyer
import com.example.newproject.util.tracker.lib.ChannelTalk
import com.example.newproject.util.tracker.lib.FirebaseLog

class DefaultTracker {
    private val amplitude = Amplitude
    private val firebaseLog = FirebaseLog
    private val channelTalk = ChannelTalk
    private val appsFlyer = AppsFlyer

    fun init(type: String) {
        if (type == "all") {
            amplitude.init()
            firebaseLog.init()
            channelTalk.init()
            appsFlyer.init()
        } else if (type == "amplitude") {
            amplitude.init()
        } else if (type == "firebaseLog") {
            firebaseLog.init()
        } else if (type == "channelTalk") {
            channelTalk.init()
        } else if (type == "appsFlyer") {
            appsFlyer.init()
        }
    }

    fun boot(type: String) {
        if (type == "all") {
            amplitude.boot()
            firebaseLog.boot()
            channelTalk.boot()
            appsFlyer.boot()
        } else if (type == "amplitude") {
            amplitude.boot()
        } else if (type == "firebaseLog") {
            firebaseLog.boot()
        } else if (type == "channelTalk") {
            channelTalk.boot()
        } else if (type == "appsFlyer") {
            appsFlyer.boot()
        }
    }

    fun sendEvent(type: String, message: String) {
        if (type == "all") {
            if (amplitude.isBoot()) {
                amplitude.sendEvent(message)
            }
            if (firebaseLog.isBoot()) {
                firebaseLog.sendEvent(message)
            }
            if (channelTalk.isBoot()) {
                channelTalk.sendEvent(message)
            }
            if (appsFlyer.isBoot()) {
                appsFlyer.sendEvent(message)
            }
        } else if (type == "amplitude") {
            if (amplitude.isBoot()) {
                amplitude.sendEvent(message)
            }
        } else if (type == "firebaseLog") {
            if (firebaseLog.isBoot()) {
                firebaseLog.sendEvent(message)
            }
        } else if (type == "channelTalk") {
            if (channelTalk.isBoot()) {
                channelTalk.sendEvent(message)
            }
        } else if (type == "appsFlyer") {
            if (appsFlyer.isBoot()) {
                appsFlyer.sendEvent(message)
            }
        }
    }
}