package com.example.kotlinbasics.ClassesAndInheritance

import android.util.Log


open class TestClass2 (val name: String, val lastName: String, override val count: Int) :
    TestClass1(name.capitalize().also { Log.d("test", "Argument for Base: $it") }),
    TestInerface {
    //to inherit base class make base class as "open"
    //count is a property of TestInterface


    override var x: Int = 0
    //if base class have var property then we have to write var
    //if base class have val property then we can write var or val also


    override fun test() { //to override base class's method make that method as "open"
        super.test()
    }

    init {
        Log.d("test", "Initializing Derived")
    }

    override val size: Int =
        (super.size + lastName.length).also { Log.d("test", "Initializing size in Derived: $it") }


    override fun f() {
        super.f()
        Log.d("test", "TestClass2.f()")
    }
    override val y: Int get() = super.y + 1

    inner class Baz {
        fun g() {
            super@TestClass2.f() // Calls TestClass1's implementation of f()
            Log.d("test", super@TestClass2.y.toString()) // Uses TestClass1's implementation of y's getter
        }
    }


}