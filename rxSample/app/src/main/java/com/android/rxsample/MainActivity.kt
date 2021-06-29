package com.android.rxsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {

    // CompositeDisposable を利用することで「イベント検知できる状態」のRxオブジェクトをまとめて破棄できる（「イベント検知できる状態」を終了できる
    val disposables = CompositeDisposable()

    // CompositeDisposable を利用しないでまとめて破棄できない面倒なパターン
    lateinit var observable: Disposable
    lateinit var single: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rxExecute()
    }

    private fun rxExecute() {

        // Observable　流れてきた値の数だけonNext される
        Observable.just(1, 2, 3, 4, 5)
            .take(3)
            .filter { it % 2 ==1 }
            .subscribeBy(
                onNext = {
                    println("onNext: $it") },
                onError = { println("エラーーーー")},
                onComplete = { println("正常に終了！！！！") } // 値は受け取れない(onNextのようにitにアクセスできない）
            )
            .addTo(disposables) // disposablesに このRxオブジェクトを追加

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
                onError = { println("エラーーーー")}, // onCompleteは存在しない
            )
            .addTo(disposables) // disposablesに このRxオブジェクトを追加


        println("############################################################")
        // ############################################################
        // CompositeDisposable を利用しないでまとめて破棄できない面倒なパターン説明用
        observable = Observable.just(1, 2, 3, 4, 5)
            .take(3)
            .filter { it % 2 ==1 }
            .subscribeBy(
                onNext = {
                    println("onNext: $it") },
                onError = { println("エラーーーー")},
                onComplete = { println("正常に終了！！！！") } // 値は受け取れない(onNextのようにitにアクセスできない）
            )

        single = Single.just(arrayOf(1, 2, 3, 4, 5, 6, 7))
            .map {
                it.filter { i ->
                    i % 2 == 1
                }
            }
            .subscribeBy(
                onSuccess = {
                    println("onSuccess: $it") },
                onError = { println("エラーーーー")}, // onCompleteは存在しない
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        // 「イベント検知できる状態」の終了
        disposables.clear()

        // CompositeDisposable を利用しないでまとめて破棄できない面倒なパターン説明用
        observable.dispose()
        single.dispose()
    }
}
