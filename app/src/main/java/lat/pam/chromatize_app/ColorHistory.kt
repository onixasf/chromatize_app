package lat.pam.chromatize_app

// ColorHistory.kt
object ColorHistory {
    val history = listOf(
        MoodData("Happy", Mood.HAPPY.value),
        MoodData("Sad", Mood.SAD.value),
        MoodData("Neutral", Mood.NEUTRAL.value),
        MoodData("Sad", Mood.SAD.value),
        MoodData("Very Bad", Mood.VERY_BAD.value)
    )
}
