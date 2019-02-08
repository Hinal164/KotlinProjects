package com.example.android_pc.kotlinbasics

import android.content.Context
import android.util.Log
import android.view.View

open class TestClass1(name:String)  {

    open fun test() {
        //if method is open then we can override it if it's final then we can not override it
        Log.d("test", "Hello")
    }
    open var x: Int=0

    init { Log.d("test","Initializing Base") }
    open val size: Int = name.length.also {Log.d("test","Initializing size in Base: $it") }

}