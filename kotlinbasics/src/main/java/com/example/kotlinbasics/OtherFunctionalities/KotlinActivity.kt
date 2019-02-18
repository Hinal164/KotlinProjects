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
            Log.d("test", "No items above 6")  // prints "No items above 6"
        val item = rwList.firstOrNull()
        Log.d("test", "" + item)


        val words = setOf("pen", "cup", "dog", "person", "cement", "coal", "spectacles")
        words.forEach { e -> Log.d("test", "$e ") }
        for (word in words) {
            Log.d("test", "$word")
        }
        for (i in 0 until words.size) {
            Log.d("test", "${words.elementAt(i)} ")
        }
        val it: Iterator<String> = words.asIterable().iterator()
        while (it.hasNext()) {
            val e = it.next()
            Log.d("test", "$e ")
        }

        val sets = mutableSetOf<Int>(1, 23, 5, 7, 34, 88)
        //we can add/remove in mutable set
        sets.add(76)
        sets.forEach { set -> Log.d("test", "$set ") }








        //Type Checks and Casts: 'is' and 'as'
        demo(3)
        //"Unsafe" cast operator:: , the cast operator throws an exception if the cast is not possible. Thus, we call it unsafe.
        // The unsafe cast in Kotlin is done by the infix operator "as"
        val y=4
        //val x: String = y is String // This will gives a compile time error
        val x: String = y as String // runs perfectly
        Log.d("test", "y= $y")

        val y1=null
        //If y1=null then it will thrown the run time error
        //because  null cannot be cast to String as this type is not nullable, i.e. if y1 is null, the code above throws an exception.
        // In order to match Java cast semantics we have to have nullable type at cast right hand side, like:
        val x1: String? = y1 as String?
        Log.d("test", "y1= $y1")

        //we can use a safe cast operator "as?" that returns null on failure
        val x2: String? = y1 as? String
        Log.d("test", "y1= $y1")







        //Equality
        //Structural equality (a check for equals()) ::Checks by using == and != operator
        //Referential equality (two references point to the same object) :: Checks by using === and !== operator
        val a=""
        val b="Hinal"
        var i= a?.equals(b) ?: (b === null)
        //i.e. if a is not null, it calls the equals(Any?) function, otherwise (i.e. a is null) it checks
        // that b is referentially equal to null.
        //explicitly: a == null will be automatically translated to a === null.

    }

    private fun twoValuesReturn(): Pair<Int, String> {

        return Pair(1, "success")
    }
    private fun demo(x: Any) {
        //smart casts:: compiler tracks the is-checks and explicit casts for immutable values and inserts (safe) casts automatically when needed
        if (x is String) {
            Log.d("test","X is String, Length is:"+x.length) // x is automatically cast to String
        }
        if(x is Int){
            Log.d("test","X is Int:"+(x+10))
        }
    }

    //This Expression
    // In a member of a class, this refers to the current object of that class. //In an extension function or a function literal with receiver this denotes the receiver parameter that is passed on the
    // left-hand side of a dot.
    inner class B { // implicit label @B
        fun Int.foo() { // implicit label @foo
            val a = this@KotlinActivity // KotlinActivity's this
           // To access this from an outer scope, we write this@label
            val b = this@B // B's this

            val c = this // foo()'s receiver, an Int
            val c1 = this@foo // foo()'s receiver, an Int

            val funLit2 = { s: String ->
                // foo()'s receiver, since enclosing lambda expression
                // doesn't have any receiver
                val d1 = this
            }
        }
    }
}