package com.androchef.happytimersample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androchef.happytimer.countdowntimer.HappyTimer
import com.androchef.happytimersample.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var timerType: HappyTimer.Type = HappyTimer.Type.COUNT_DOWN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTimerConfiguration(30)
        onClicks()
    }

    private fun onClicks() {
        btnStart.setOnClickListener {
            countDownTimer.startTimer()
        }

        btnStop.setOnClickListener {
            countDownTimer.stopTimer()
        }

        btnPause.setOnClickListener {
            countDownTimer.pauseTimer()
        }

        btnResume.setOnClickListener {
            countDownTimer.resumeTimer()
        }

        btnSetTime.setOnClickListener {
            initTimerConfiguration(edtTimeInSeconds.text?.toString()?.toInt() ?: 30)
        }

        rdCountDown.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                timerType = HappyTimer.Type.COUNT_DOWN
            }
        }

        rdCountUp.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                timerType = HappyTimer.Type.COUNT_UP
            }
        }
    }

    private fun initTimerConfiguration(totalSeconds: Int) {
        countDownTimer.timerTextIsBold = true
        countDownTimer.timerTextSize = 12F
        countDownTimer.initTimer(totalSeconds, timerType)
        countDownTimer.setOnTickListener(object : HappyTimer.OnTickListener {
            override fun onTick(completedSeconds: Int, remainingSeconds: Int) {
                Log.d("TIMER","On Tick $completedSeconds")
            }

            override fun onTimeUp() {
                Log.d("TIMER","TimeUp")
            }
        })
        countDownTimer.setStateChangeListener(object : HappyTimer.OnStateChangeListener{
            override fun onStateChange(
                state: HappyTimer.State,
                completedSeconds: Int,
                remainingSeconds: Int
            ) {
                Log.d("TIMER",state.name)
            }
        })
    }

    companion object
}
