package com.example.kotlinbasics.Extensions

import android.util.Log

open class C {
    open fun D.foo() {
        Log.d("test","D.foo in C")
    }

    open fun D1.foo() {
        Log.d("test","D1.foo in C")
    }

    fun caller(d: D) {
        d.foo()   // call the extension function
    }
}