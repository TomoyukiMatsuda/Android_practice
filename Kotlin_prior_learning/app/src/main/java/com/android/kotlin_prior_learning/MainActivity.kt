package com.android.kotlin_prior_learning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.System.*

// classの前に何も指定しない場合は public スコープなクラスとなる
// class クラス名 :(extendsとimplementsを両方兼ねている)
class MainActivity : AppCompatActivity() {

    val num = 1
    var num2 : Int = 2



    // Bundle? なのでNull許容される
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val result : String = if(false) { "true" } else { "false" }

        println(result)
        println(num)
        println(num2)

        val value = 3

        // javaで いうswitch文
        val str = when(value) {
            1 -> "one"
            2 -> "two"
            else -> "other"
        }

        println(str)
    }
}
