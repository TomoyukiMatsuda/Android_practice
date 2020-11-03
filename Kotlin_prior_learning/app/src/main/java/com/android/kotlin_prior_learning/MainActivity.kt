package com.android.kotlin_prior_learning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.System.*

// classの前に何も指定しない場合は public スコープなクラスとなる
// class クラス名 :(extendsとimplementsを両方兼ねている)
class MainActivity : AppCompatActivity() {

    // 1どだけ代入するものはvalを使う
    val handler = Handler()
    // 繰り返し代入するためvarを使う
    var timeValue = 0

    // Bundle? なのでNull許容される
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // view 要素を取得して変数に代入 as ~ でnull許容型か非null許容型かを指定している TextView or TextView?
        val timeText = findViewById(R.id.timeText) as TextView
        val startButton = findViewById(R.id.start) as Button
        val stopButton = findViewById(R.id.stop) as Button
        val resetButton = findViewById(R.id.reset) as Button

        // 1秒ごとに処理を実行
        val runnable = object : Runnable {
            override fun run() {

                timeValue++

                // TextView を更新
                // ?.let を用いて、nullではない場合のみ更新
                // 変数名?.let はアンラップ的なノリで使うこの結果をブロック内で it として使える
//                変数名?.let {
//                    it
//                }

                timeToText(timeValue)?.let {
                    // timeToText(timeValue)のreturn値がlet内ではitとして使える
                    // timeValueの値をストップウォッチの表示テキストに代入
                    timeText.text = it
                }
                handler.postDelayed(this, 1000)
            }
        }

        // ボタンを押した時の処理

        // start
        startButton.setOnClickListener {
            handler.post(runnable)
        }

        // stop
        stopButton.setOnClickListener {
            handler.removeCallbacks(runnable)
        }

        // reset
        resetButton.setOnClickListener {
            handler.removeCallbacks(runnable)
            timeValue = 0
            // timeToText の引数はデフォルト値が設定されているので、引数省略できる
            timeToText()?.let {
                timeText.text = it
            }
        }
    }

    private fun timeToText(time: Int = 0): String? {

        return if (time < 0) {
            null
        } else if (time == 0) {
            "00:00:00"
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
}
