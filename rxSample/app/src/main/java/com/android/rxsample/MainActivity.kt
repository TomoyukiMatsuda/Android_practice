package com.android.rxsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rxExecute()
    }

    private fun rxExecute() {

        // 流れてきた値の数だけonNext される
        Observable.just(1, 2, 3, 4, 5)
            .take(3)
            .filter { it % 2 ==1 }
            .subscribeBy(
                onNext = {
                    println("onNext: $it") },
                onError = { println("エラーーーー")},
                onComplete = { println("正常に終了！！！！") }
            )

        // 流せる値は１個だけ
        Single.just(arrayOf(1, 2, 3, 4, 5, 6, 7))
            .map {
                it.filter { i ->
                    i % 2 == 1
                }
            }
            .subscribeBy(
                onSuccess = {
                    println("onSuccess: $it") },
                onError = { println("エラーーーー")},
            )
    }
}
