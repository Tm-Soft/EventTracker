package com.kukwonho.myapplication

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.newproject.util.tracker.NewTracker
import com.example.newproject.util.tracker.TrackerType
import com.kukwonho.myapplication.keyboard_observer.CrossKeyboardObserver
import gun0912.tedkeyboardobserver.TedKeyboardObserver

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListener()

        CrossKeyboardObserver(
            this,
                Rect().apply { window.decorView.getWindowVisibleDisplayFrame(this) }.bottom
            ).listen {
            if (it) {
                Toast.makeText(this, "키보드 ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "키보드 OFF", Toast.LENGTH_SHORT).show()
            }
        }

        Log.d("whk__", "onCreate : ${Rect().apply { window.decorView.getWindowVisibleDisplayFrame(this) }.bottom}")
    }

    private fun initListener() {
        findViewById<Button>(R.id.btn_all_init).setOnClickListener {
            NewTracker().init(TrackerType.ALL)
        }

        findViewById<Button>(R.id.btn_all_boot).setOnClickListener {
            NewTracker().boot(TrackerType.ALL)
        }

        findViewById<Button>(R.id.btn_all_event).setOnClickListener {
            NewTracker().sendEvent(TrackerType.ALL, "전체 이벤트")
        }

        findViewById<Button>(R.id.btn_amplitude_event).setOnClickListener {
            NewTracker().sendEvent(TrackerType.AMPLITUDE, "앰플리튜드 이벤트")
        }

        findViewById<Button>(R.id.btn_firebase_event).setOnClickListener {
            NewTracker().sendEvent(TrackerType.FIREBASE_LOG, "파이어베이스 이벤트")
        }

        findViewById<Button>(R.id.btn_channelTalk_event).setOnClickListener {
            NewTracker().sendEvent(TrackerType.CHANNEL_TALK, "채널톡 이벤트")
        }

        findViewById<Button>(R.id.btn_appsflyer_event).setOnClickListener {
            NewTracker().sendEvent(TrackerType.APPS_FLYER, "앱스플라이어 이벤트")
        }
    }
}