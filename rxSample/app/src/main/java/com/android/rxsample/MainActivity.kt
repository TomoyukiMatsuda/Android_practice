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
import kotlin.IllegalStateException

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

//        rxWeather()
        rxExecute()
//        rxBehaviorSubject()
//        rxPublishSubject()
        //rxOnDoOn()
        //rxDoOnSF()
    }

    private fun rxExecute() {
        // 複数個の値を流すことができる / subscribe に流れてきた値の数だけ onNext される
        Observable.just(1, 2, 3, 4, 5, 6, 7)
            .filter {
                // 流れてきた値が 4 のときにエラーが起きるようにする
                if (it == 4) {
                    throw IllegalStateException("値が $it でエラー")
                }
                it % 2 == 0
            }
            .subscribeBy(
                onError = { println("onError: エラーーーー $it")}, // onError() ではエラーの内容（Throwable型）を受け取れる
                onComplete = { println("onComplete: 正常に処理終了！") }, // onComplete() では値は受け取れない
                onNext = { println("onNext: $it") } // onNext() では値を受け取れる
            )

//        // 流せる値は１個だけなので、リストを一つの値として渡している
//        Single.just(arrayListOf(1, 2, 3, 4, 5, 6, 7))
//            .map {
//                it.filter { i ->
//                    i % 2 == 0
//                }
//            }
//            .subscribeBy(
//                onSuccess = {
//                    throw IllegalStateException("onSuccess でエラー")
//                    println("onSuccess: $it")
//                            }, // onSuccess() では流れてきた値を受け取れる
//                onError = { println("onError: エラーーーー $it") }, // onError() ではエラーの内容（Throwable型）を受け取れる
//            )

        // 値は渡せない
//        Completable.create { emitter: CompletableEmitter ->
//            try {
//                emitter.onComplete()
//            } catch (ex: Exception) {
//                emitter.onError(ex)
//            }
//        }.subscribeBy(
//            onError = { println("onError: エラーーーー $it") }, // onError() ではエラーの内容（Throwable型）を受け取れる
//            onComplete = {
//                throw IllegalStateException("onComplete でエラー")
//                println("onComplete: 正常に終了！")
//            } // onComplete() では値は受け取れない
//        )


//        println("#######################CompositeDisposableを利用しない説明用#####################################")
//        // ############################################################
//        // CompositeDisposable を利用しないでまとめて破棄できない面倒なパターン説明用
//        observable = Observable.just(1, 2, 3, 4, 5)
//            .take(3)
//            .filter { it % 2 ==1 }
//            .subscribeBy(
//                onNext = {
//                    println("onNext: $it") },
//                onError = { println("エラーーーー")},
//                onComplete = { println("onComplete: 正常に終了！！！！") } // 値は受け取れない(onNextのようにitにアクセスできない）
//            )
//
//        single = Single.just(arrayOf(1, 2, 3, 4, 5, 6, 7))
//            .map {
//                it.filter { i ->
//                    i % 2 == 1
//                }
//            }
//            .subscribeBy(
//                onSuccess = {
//                    println("onSuccess: $it") },
//                onError = { println("エラーーーー")}, // onCompleteは存在しない
//            )
//
//        completable = Completable.create { emitter: CompletableEmitter ->
//            try {
//                // 何らかの処理
//                emitter.onComplete()
//            } catch (ex: Exception) {
//                emitter.onError(ex)
//            }
//        }.subscribeBy(
//            onError = {println("エラーーーー")},
//            onComplete = {println("onComplete: 正常に終了！！！！")}
//        )
//            .addTo(disposables) // disposablesに このRxオブジェクトを追加
    }

    private fun rxDoOnSF() {
        Observable.just(1, 2, 3, 4, 5)
            .doOnError {
                println("doOnError 1: $it")
            }
            .doOnComplete {
                println("doOnComplete: １")
            }
            .doOnNext {
                if (it == 3) {
                    println("doOnNext (it == 3): $it")
                    //throw IllegalStateException("doOnNext 1 でエラー: $it")
                }
                println("doOnNext : $it")
            }
            .filter { it % 2 == 1 }
            .doOnError {
                println("doOnError 2: $it")
            }
            .doOnSubscribe {
                // ストリームを開始するときに呼ばれてるっぽい doOnNextよりも先に呼ばれてる
                println("doOnSubscribe: $it")
            }
            .doOnError {
                println("doOnError 3: $it")
            }
            .doFinally {
                // 一番最後？に呼ばれているっぽい、onCompleteよりも後に呼ばれている
                println("doFinally")
            }
            .doOnComplete {
                println("doOnComplete: ２")
            }
            .subscribeBy(
                onNext = {
                    println("onNext: $it") },
                onError = {
                    println("エラーーーー: $it")
                },
                onComplete = { println("onComplete: 正常に終了！！！！") }
            )
            .addTo(disposables)
    }

    private fun rxOnDoOn() {
        Observable.just(1, 2, 3, 4, 5)
            .doOnNext {
                if (it == 3) {
                    println("doOnNext 1: $it")
                    return@doOnNext
                }
            }
            .take(3)
            .doOnError {
                println("doOnError 1: $it")
            }
            .doOnNext { // 途中で流れてきた値にアクセスして処理できる？
//                if (it == 2) {
//                    throw IllegalStateException("doOnNext 1 でエラー: $it")
//                }
                println("doOnNext 2: $it")
            }
            .doOnComplete {
                println("doOnComplete 1: 正常？")
            }
            .doOnError {
                println("doOnError 2: $it")
            }
            .filter { it % 2 == 1 }
            .doOnNext {
                if (it == 3) {
                    throw IllegalStateException("doOnNext 3 でエラー: $it")
                }
                println("doOnNext 3: $it")
            }
            .doOnComplete {
                println("doOnComplete 2: 正常？")
            }
            .subscribeBy(
                onNext = {
                    println("onNext: $it") },
                onError = {
                    println("エラーーーー: $it")
                          },
                onComplete = { println("onComplete: 正常に終了！！！！") } // 値は受け取れない(onNextのようにitにアクセスできない）
            )
            .addTo(disposables)
    }

    private fun rxBehaviorSubject() {
        val bSubject = BehaviorSubject.create<String>()

        // onNext で subscribe 前に値を流す。しかし、最新の値しかキャッシュされない。
        bSubject.onNext("Behavior/onNext: キャッシュ①")
        bSubject.onNext("Behavior/onNext: キャッシュ②")
        bSubject.onNext("Behavior/onNext: キャッシュ③")

        bSubject.subscribeBy(
            onComplete = { println("Behavior/onComplete: 終了！！！") },
            onError = { println(it) },
            onNext = { println(it) }
        )

        bSubject.onNext("Behavior/onNext: リスナー①")
        // エラーが起きたら終了する
        // bSubject.onError(IllegalStateException("Behavior/onError: エラーーーーー"))
        bSubject.onNext("Behavior/onNext: リスナー②")
        bSubject.onNext("Behavior/onNext: リスナー③")
        bSubject.onComplete()
    }

    private fun rxPublishSubject() {
        val pSubject = PublishSubject.create<String>()

        // onNext で subscribe 前に値を流そうとする。しかし、キャッシュされない。（ここがBehaviorSubjectと異なるポイント）
        pSubject.onNext("Publish/onNext: キャッシュ①")
        pSubject.onNext("Publish/onNext: キャッシュ②")
        pSubject.onNext("Publish/onNext: キャッシュ③")

        pSubject.subscribeBy(
            onComplete = { println("Publish/onComplete: 終了！！！") },
            onError = { println(it) },
            onNext = { println(it) }
        )

        pSubject.onNext("Publish/onNext: リスナー①")
        // エラーが起きたら終了する
        // pSubject.onError(IllegalStateException("Publish/onError: エラーーーーー"))
        pSubject.onNext("Publish/onNext: リスナー②")
        pSubject.onNext("Publish/onNext: リスナー③")
        pSubject.onComplete()
    }

    private fun rxWeather() {
        Observable.just(1,4,3,2,8,5)
            .map {
                // 値が流れてきたら map で変換している
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
