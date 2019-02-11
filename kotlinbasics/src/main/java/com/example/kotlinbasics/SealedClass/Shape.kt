package com.example.kotlinbasics.SealedClass

sealed class Shape {
     class Circle(var radius:Float):Shape()
     class Square(var length:Int):Shape()
     class Rectangle(var length:Int, var breadth:Int):Shape()
}