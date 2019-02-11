package com.example.kotlinbasics.PropertiesAndFields

class Address {

    var name: String = "Hinal"
    var city: String = "Rajkot"
    var state: String? = "Gujarat"
    var zip: String = "360005"

    private val bar: Int = 1
    class Nested {
        fun foo() = 2
        //fun foo()=bar will give an error
    }

    inner class Inner{
        fun foo()=bar
    }

}