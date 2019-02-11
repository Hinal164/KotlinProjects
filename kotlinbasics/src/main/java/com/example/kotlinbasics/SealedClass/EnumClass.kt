package com.example.kotlinbasics.SealedClass

enum class EnumClass {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): EnumClass
}