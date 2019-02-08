package com.example.kotlinbasics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val n1 = 6
        val n2 = 3
        var n3 = 8
        var max: Int = maxOf(n1, n2)
        Log.d("max of two number", "$max")
        max = if (n3 > n2) n1 else n2
        Log.d("max", "Max of $n3 and $n2 = $max")

        if (n1 > n2) {
            if (n1 > n3) {
                Log.d("Kotlin", "Max of 3 numbers = $n1")
            } else {
                Log.d("Kotlin", "Max of 3 numbers = $n3")
            }
        } else {
            if (n2 > n3) {
                Log.d("Kotlin", "Max of 3 numbers = $n2")
            } else {
                Log.d("Kotlin", "Max of 3 numbers = $n3")
            }
        }

        for (i in 6 downTo 0 step 2) {//downto-> reverse list
            Log.d("Kotlin for loop", "$i")
        }
        for (i in 0..10 step 2) {//step 2 means increase by 2
            Log.d("Kotlin for loop", "$i")
        }

        when (n1) {
            6 -> Log.d("Kotlin when", "n1 == 6")
            3 -> Log.d("Kotlin when", "n1 == 3")
            else -> Log.d("Kotlin when", "otherwise")
        }

        //break
        loop@ for (i in 1..10) {
            for (j in 1..10) {
                if (j == 9)
                    break@loop
                Log.d("i,j", "$i, $j")
            }
        }

        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) return  // local return to the caller of the anonymous fun, i.e. the forEach loop
            Log.d("value", "$value")
        })
        Log.d("value", "done with anonymous function")

        run loop@{
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop // non-local return from the lambda passed to run
                Log.d("value", "$it")
            }
        }
        Log.d("value", " done with nested loop")

    }

}


