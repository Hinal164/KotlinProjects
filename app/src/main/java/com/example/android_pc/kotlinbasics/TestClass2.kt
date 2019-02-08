package com.example.android_pc.kotlinbasics

import android.content.Context
import android.util.Log

open class TestClass2(name:String, val lastName: String, override  val count:Int)
    : TestClass1(name.capitalize().also { println("Argument for Base: $it") }),TestInerface {
    //to inherit base class make base class as "open"
    //count is a property of TestInterface

    override var x:Int=0
    //if base class have var property then we have to write var
    //if base class have val property then we can write var or val also


    override fun test() { //to override base class's method make that method as "open"
        super.test()
    }

    init { Log.d("test","Initializing Derived") }

    override val size: Int =
        (super.size + lastName.length).also { Log.d("test","Initializing size in Derived: $it") }

}