package com.example.kotlinbasics.DelegationAndFunctions

import android.util.Log

class DerivedClass(b:Base) :Base by b {
    override fun printMessage() {
        Log.d("test","abc")
    }

    override val message = "Message of Derived"

}