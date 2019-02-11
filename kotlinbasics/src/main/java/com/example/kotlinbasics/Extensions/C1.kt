package com.example.kotlinbasics.Extensions

import android.util.Log

class C1 : C() {
    override fun D.foo() {
        Log.d("test","D.foo in C1")
    }

    override fun D1.foo() {
        Log.d("test","D1.foo in C1")
    }

}
