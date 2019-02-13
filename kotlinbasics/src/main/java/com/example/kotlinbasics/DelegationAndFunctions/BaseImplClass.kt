package com.example.kotlinbasics.DelegationAndFunctions

import android.util.Log

class BaseImplClass(val x: Int) : Base {
    override val message = "BaseImpl: x = $x"
    override fun print() {
        Log.d("test", "" + x)
    }

    override fun printMessage() {
        Log.d("test", "" + x)
    }

    override fun printMessageLine() {
        Log.d("test", "" + x)
    }
}