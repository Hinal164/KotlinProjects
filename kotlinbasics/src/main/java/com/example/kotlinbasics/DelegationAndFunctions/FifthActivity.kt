package com.example.kotlinbasics.DelegationAndFunctions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlinbasics.R

class FifthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        val b = BaseImplClass(10)
        DerivedClass(b).print()
        DerivedClass(b).printMessage()
        DerivedClass(b).printMessageLine()
        Log.d("test", "" + DerivedClass(b).message)

        Log.d("test", "${double(4)}")
        foo(baz = 6)// The default value bar = 3 is used
        //foo(baz=6, 3) will give an error because all the positional arguments should be placed before the first named one
        //that means we can write foo(3,baz=6)

        reformat("hinal")//Used default values as it is
        reformat("hinal", wordSeparator = '_')// if we do not need all arguments
        Log.d("test", "${double1(3)}")

        val list1 = asList("aaa", "bbb", "ccc")
        val list2 = asList(1, 2, 3)
        Log.d("test",""+list1)
        Log.d("test",""+list2)

        val arr=arrayOf(6,7,8)
        val list3=asList('a','b',*arr,'c')
        Log.d("test",""+list3)
        foo(strings = *arrayOf("a", "b", "c"))

        this add "abc"   // Correct
        add("abc")       // Correct
        //add "abc"      // Incorrect: the receiver must be specified

    }

    private fun double(x: Int): Int {
        return 2 * x
    }

    private fun foo(bar: Int = 3, baz: Int) {
        Log.d("test", "${bar * baz}")
    }

    private fun reformat(
        str: String,
        normalizeCase: Boolean = true,
        upperCaseFirstLetter: Boolean = true,
        divideByCamelHumps: Boolean = false,
        wordSeparator: Char = ' ') {

        Log.d("test", str)
    }

    private fun double1(x: Int): Int = x * 2
    //single-expression function
    //Explicitly declaring the return type is optional
    //fun double1(x: Int) = x * 2

    private fun <T> asList(vararg ts: T): List<T> {
        //vararg means variable argument function
        val result = ArrayList<T>()
        for (i in ts)
            result.add(i)
        return result
    }
    private fun foo(vararg strings: String) {
       Log.d("test",""+strings.size)
    }
    private infix fun add(s: String) {
        Log.d("test","Infix Function Calling...$s")
    }

}