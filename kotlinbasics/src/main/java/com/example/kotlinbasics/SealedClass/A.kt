package com.example.kotlinbasics.SealedClass

import android.util.Log

sealed class A {
    class B : A() {
        class D : A()
    }
    object C : A() {
        fun name() {
            Log.d("test", "Object C")
        }
    }
    data class E(var name: String) : A()
}