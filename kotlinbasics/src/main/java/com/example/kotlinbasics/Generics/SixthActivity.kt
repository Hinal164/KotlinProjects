package com.example.kotlinbasics.Generics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlinbasics.R

class SixthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)


        //VARIANCES
        val i = arrayOf(1, 2, 3)
        // val j: Array<Any> = i
        // this won't compile because kotlin arrays are invariant
        //Invariant means subtypes and supertypes are not allowed

        //GENERICS-COVARIANT(use 'out')
        //val x: GenericClass<Any> = GenericClass<Int>() // Error: Type mismatch
        val y: GenericClass<out Any> = GenericClass<String>() // Works
        // val z: GenericClass<out String> = GenericClass<Any>() // Error
        //Out modifier is used for applying

        val a: GenericClass2<Any> = GenericClass2<Int>()
        //Because we have directly annotated the wildcard argument on the parameter type of GenericClass2
        //This is known as declare-site variance
        var correct: GenericClass2<Vehicle> = GenericClass2<Car>()
        // var wrong: GenericClass2<Car> = GenericClass2<Vehicle>()


        //GENERICS-CONTRACOVARIANT(use 'in')
        var correct1: GenericClass3<Car> = GenericClass3<Vehicle>()
        //var wrong: GenericClass3<Vehicle> = GenericClass3<Car>()
        //This is just the opposite of Covariance


    }

}