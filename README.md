프로젝트를 진행 하면서 이벤트 트래킹을 위한 여러 sdk를 사용하고 있습니다.<br>
기획의 요구사항에 따라 이벤트 트래킹 sdk의 추가/삭제에 대한 유지 보수를 높히기 위해서 제작 하였습니다.

```kotlin
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
```

위와 같은 방식으로 사용한다면 여러 이벤트 트래킹에 단일로 Event를 보내거나 모든곳에 Event를 보낼 수 있습니다.

이벤트 트래킹 sdk가 추가/삭제 된다면 아래 enum을 수정하시면 됩니다.
```kotlin
enum class TrackerType(val strategy: TrackerStrategy?) {
    AMPLITUDE(AmplitudeStrategy(Amplitude)),
    FIREBASE_LOG(FirebaseLogStrategy(FirebaseLog)),
    CHANNEL_TALK(ChannelTalkStrategy(ChannelTalk)),
    APPS_FLYER(AppsFlyerStrategy(AppsFlyer)),
    ALL(null); // ALL은 특별 취급, 실제 전략은 없음

    companion object {
        fun applyToAll(action: (TrackerStrategy) -> Unit) {
            values().filter { it != ALL }.forEach { action(it.strategy!!) }
        }
    }
}
```

모든 이벤트 트래킹이 동일한 방식으로 init / boot / sendEvent 가 되는 것이 아니므로 TrackerStrategy 상속 받는 구현에 적용하시면 됩니다.
```kotlin
class AmplitudeStrategy(
    private val amplitude: Amplitude
) : TrackerStrategy {
    override fun init() = amplitude.init()
    override fun boot() = amplitude.boot()
    override fun sendEvent(message: String) = amplitude.sendEvent(message)
    override fun isBoot(): Boolean = amplitude.isBoot()
}
```
