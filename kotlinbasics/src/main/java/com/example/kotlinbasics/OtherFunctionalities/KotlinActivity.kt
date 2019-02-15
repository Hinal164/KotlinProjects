package com.example.kotlinbasics.OtherFunctionalities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlinbasics.R

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)


        //Destructuring Declarations
        //It allows to declare multiple variables at once.
        val s = Student("Hello", "World")
        /*A destructuring declaration is compiled as:
               val name=s.component1()
               val subject=s.component2()*/
        val (name, subject) = s
        Log.d("test", "" + name + "\t" + subject)

        //we can return two values from a function
        val (result, status) = twoValuesReturn()
        Log.d("test", "" + result + "\t" + status)

        var map: HashMap<Int, Student> = HashMap()
        map[1] = s//or map.put(1, s)
        for ((key, value) in map) {
            Log.d("test", "Key: $key, Value: $value")
        }




        //Collections: List, Set, Map
        val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
        val readOnlyView: List<Int> = numbers
        Log.d("test", "" + numbers) // prints "[1, 2, 3]"
        numbers.add(4)
        Log.d("test", "" + readOnlyView)   // prints "[1, 2, 3, 4]"
        // readOnlyView.clear()    // -> does not compile
        val strings = hashSetOf("a", "b", "c", "c")
        assert(strings.size == 3)

        val items = listOf(1, 2, 3, 4)
        //Once the list is created we can not modify your data.Nor can we add elements
        //items.first() == 1
        //items.last() == 4
        items.filter { it % 2 == 0 }   // returns [2, 4]

        val rwList = mutableListOf(1, 2, 3)
        //We can use add() method to add any item to mutable list
        rwList.requireNoNulls()        // returns [1, 2, 3]
        if (rwList.none { it > 6 })
            Log.d("test","No items above 6")  // prints "No items above 6"
        val item = rwList.firstOrNull()
        Log.d("test",""+item)

    }

    private fun twoValuesReturn(): Pair<Int, String> {

        return Pair(1, "success")
    }
}