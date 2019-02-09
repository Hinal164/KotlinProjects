package com.example.kotlinbasics.VisibilityModifiers

class SubClass : OuterClass() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible

    override val b = 5   // 'b' is protected
}