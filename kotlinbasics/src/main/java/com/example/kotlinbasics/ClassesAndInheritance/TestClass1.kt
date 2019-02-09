package com.example.kotlinbasics.ClassesAndInheritance

import android.util.Log

open class TestClass1(name:String)  {

    open fun test() {
        //if method is open then we can override it if it's final then we can not override it
        Log.d("test", "Hello")
    }
    open var x: Int=0

    init { Log.d("test","Initializing Base") }
    open val size: Int = name.length.also {Log.d("test","Initializing size in Base: $it") }

    open fun f(){
        Log.d("test","TestClass1.f()")
    }
    open val y:Int get() = 10



}