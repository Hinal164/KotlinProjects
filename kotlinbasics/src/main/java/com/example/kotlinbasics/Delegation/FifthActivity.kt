package com.example.kotlinbasics.Delegation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlinbasics.R

class FifthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        val b=BaseImplClass(10)
        DerivedClass(b).print()
        DerivedClass(b).printMessage()
        DerivedClass(b).printMessageLine()
        Log.d("test",""+DerivedClass(b).message)
    }
}