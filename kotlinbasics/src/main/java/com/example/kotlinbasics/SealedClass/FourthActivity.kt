package com.example.kotlinbasics.SealedClass

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlinbasics.R

class FourthActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activtiy_fourth)

        // var a = A() //compiler error. Class A cannot be instantiated.

        val e = A.E("Hinal")
        Log.d("test",""+e) //prints E(name=Hinal)

        val d = A.C
        d.name() //prints Object C

        var circle = Shape.Circle(4.5f)
        var square = Shape.Square(4)
        var rectangle = Shape.Rectangle(4,5)

        eval(circle)
        eval(square)
        eval(rectangle)

        Log.d("test",""+EnumClass.WAITING)

    }
    private fun eval(e: Shape) =
        when (e) {
            is Shape.Circle ->  Log.d("test","Circle area is ${3.14*e.radius*e.radius}")
            is Shape.Square -> Log.d("test","Square area is ${e.length*e.length}")
            is Shape.Rectangle -> Log.d("test","Rectagle area is ${e.length*e.breadth}")
        }
}