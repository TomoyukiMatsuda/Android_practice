package com.android.rxsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rxExecute()
    }

    private fun rxExecute() {
        Observable.just(1, 2, 3, 4, 5)
            .take(3)
            .filter { it % 2 ==1 }
            .subscribeBy(
                onNext = { println("onNext: $it") },
                onError = { println("エラーーーー")},
                onComplete = { println("正常に終了！！！！") }
            )
    }
}
