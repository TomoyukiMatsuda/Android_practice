package com.android.rxsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class MainActivity : AppCompatActivity() {

    // CompositeDisposable を利用することで「イベント検知できる状態」のRxオブジェクトをまとめて破棄できる（「イベント検知できる状態」を終了できる
    val disposables = CompositeDisposable()

    // CompositeDisposable を利用しないでまとめて破棄できない面倒なパターン
    lateinit var observable: Disposable
    lateinit var single: Disposable
    lateinit var completable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rxSubject()
        //rxExecute()
        //rxWeather()
    }

    private fun rxSubject() {
        // BehaviorSubject: 初期値を使うならこれ？？
        val bSubject = BehaviorSubject.create<String>()
        bSubject.onNext("BehaviorSubject onNext: ホゲホゲ")
        bSubject.onNext("BehaviorSubject onNext: ホゲホゲ２")
        bSubject.onNext("BehaviorSubject onNext: ホゲホゲ３")
        bSubject.subscribe {
            println(it)
        }
        bSubject.onComplete()

        // PublishSubject: 初期値を使わないならこれ？？
        // TODO: 機能してない、どうすれば機能するようになる？？
        val pSubject = PublishSubject.create<String>()
        pSubject.onNext("PublishSubject onNext: ホゲホゲ")
        pSubject.onNext("PublishSubject onNext: ホゲホゲ２")
        pSubject.onNext("PublishSubject onNext: ホゲホゲ３")
        pSubject.subscribeBy {
            println(it)
        }
    }

    private fun rxWeather() {
        Observable.just(1,4,3,2,8,5)
            .map {
                when (it) {
                    1 -> "一です"
                    2 -> "二です"
                    3 -> "三です"
                    4 -> "四です"
                    5 -> "五です"
                    else -> "ちゃう"
                }
            }
            .subscribe {
                println(it)
            }
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
                onComplete = { println("onComplete: 正常に終了！！！！") } // 値は受け取れない(onNextのようにitにアクセスできない）
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

        // 値は流せない
        Completable.create { emitter: CompletableEmitter ->
            try {
                // 何らかの処理
                emitter.onComplete()
            } catch (ex: Exception) {
                emitter.onError(ex)
            }
        }.subscribeBy(
            onError = {println("エラーーーー")},
            onComplete = {println("onComplete: 正常に終了！！！！")}
        )
        .addTo(disposables) // disposablesに このRxオブジェクトを追加


        println("#######################CompositeDisposableを利用しない説明用#####################################")
        // ############################################################
        // CompositeDisposable を利用しないでまとめて破棄できない面倒なパターン説明用
        observable = Observable.just(1, 2, 3, 4, 5)
            .take(3)
            .filter { it % 2 ==1 }
            .subscribeBy(
                onNext = {
                    println("onNext: $it") },
                onError = { println("エラーーーー")},
                onComplete = { println("onComplete: 正常に終了！！！！") } // 値は受け取れない(onNextのようにitにアクセスできない）
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

        completable = Completable.create { emitter: CompletableEmitter ->
            try {
                // 何らかの処理
                emitter.onComplete()
            } catch (ex: Exception) {
                emitter.onError(ex)
            }
        }.subscribeBy(
            onError = {println("エラーーーー")},
            onComplete = {println("onComplete: 正常に終了！！！！")}
        )
            .addTo(disposables) // disposablesに このRxオブジェクトを追加
    }

    override fun onDestroy() {
        super.onDestroy()
        // 「イベント検知できる状態」の終了
        // CompositeDisposable#clear() で全て dispose() したことと同じになる
        disposables.clear()

        // CompositeDisposable を利用しないでまとめて破棄できない面倒なパターン
        observable.dispose()
        single.dispose()
        completable.dispose()
    }
}
