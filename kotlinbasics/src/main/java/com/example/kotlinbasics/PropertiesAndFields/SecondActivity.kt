package com.example.kotlinbasics.PropertiesAndFields

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlinbasics.R

class SecondActivity : AppCompatActivity() {


    var initialized = 1 // has type Int, default getter and setter
    val inferredType = 1 // has type Int and a default getter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        fun copyAddress(address: Address):Address{ //:Address is a return type
            val result=Address()
            result.name=address.name
            result.city=address.city
            result.state=address.state
            result.zip=address.zip
            return result
        }
    }
}