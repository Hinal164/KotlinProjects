package com.example.kotlinbasics.VisibilityModifiers

class OtherClass(o:OuterClass) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}