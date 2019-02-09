package com.example.kotlinbasics.VisibilityModifiers

open class OuterClass {
    private val a = 1
    protected open val b = 2 //same as private + visible in subclasses too
    internal val c = 3 //any client inside this module who sees the declaring class sees its internal members
    val d = 4  // public by default

    protected class Nested {
        public val e: Int = 5
    }
}