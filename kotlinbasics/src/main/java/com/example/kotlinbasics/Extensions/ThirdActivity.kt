package com.example.kotlinbasics.Extensions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlinbasics.R

class ThirdActivity : AppCompatActivity() {
    lateinit var list:List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        list=listOf<String>("a","b","c")
        Log.d("test",""+list)

        val l = mutableListOf(1, 2, 3)
        l.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'l'
        Log.d("test",""+l)

        val a=printFoo(DemoClass2()) // prints DemoClass1

        C().caller(D())   // prints "D.foo in C"
        C1().caller(D())  // prints "D.foo in C1" - dispatch receiver is resolved virtually
        C().caller(D1())  // prints "D.foo in C" - extension receiver is resolved statically

        val jack = User(name = "Jack", age = 1)
        val olderJack = jack.copy(age = 2)
        Log.d("Copy",""+olderJack.toString())
    }
    private fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] // 'this' corresponds to the list
        this[index1] = this[index2]
        this[index2] = tmp
    }
    private fun DemoClass1.foo()="DemoClass1" //extension function
    fun DemoClass2.foo()="DemoClass2" //extension function

    private fun printFoo(c:DemoClass1){
        //because the extension function being called depends only on the declared type of the parameter c, which is the DemoClass1 class.

        Log.d("test",""+c.foo())
    }

}