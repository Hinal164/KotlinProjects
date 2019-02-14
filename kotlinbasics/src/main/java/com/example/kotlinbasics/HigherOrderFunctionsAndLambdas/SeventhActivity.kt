package com.example.kotlinbasics.HigherOrderFunctionsAndLambdas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlinbasics.Extensions.User
import com.example.kotlinbasics.R
import javax.xml.xpath.XPathExpression

class SeventhActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seventh)



        //Lambda Expressions
        /*Two types of functions
          1) ()->String is a type that takes no parameters and returns a string
          2) (String)->String is a type that takes a string argument and returns a string argument.
        * */
        var lambdaFunction: (String) -> Unit = { s: String ->
            Log.d("test", "" + s)
        }
        lambdaFunction("Kotlin Lambda Functions")
        //or
        lambdaFunction = { Log.d("test", "" + it) }

        val noArgFunction: () -> Unit = { Log.d("test", "Another function") }
        noArgFunction()




        //High Order Functions
        /*We can pass a function as a parameter inside another function too using references
        * To pass a function as an argument inside another function we need to use the notation ::
        * */
        var printFunction: (String) -> Unit = { Log.d("test", "" + it) }
        functionReference2("Kotlin Language", printFunction)

        var function: (String) -> String = { "Hinal" }
        functionReference1("High Oder Function", function)

        functionReference2("Kotlin language", ::printFunction)

        //Lambda Expressions inside Higher Order Functions
        printMe { Log.d("test", "Lambda Inside a function") }
        printMe2(printFunction)




        //Anonymous Functions
        /*In order to set the return types explicitly, we can use Annoymous functions.
        *An anonymous function doesn’t require a name.
        */
        Log.d("test", "Anonymous Function:" + anoSum(3, 4))

        //If the parameter/return type of the annoymous function isn’t defined, it can be inferred just like normal functions.
        var myList = listOf<Int>(1, 2, 5, 7, 6, 10)
        myList = myList.filter(fun(item) = (item % 2 == 0))
        //filter is a higher order function that checks the given condition over each of the list items.
        Log.d("test", "" + myList)


        //The compiler can infer the function types for variables if there is enough information
        val a = { i: Int -> i + 1 } // The inferred type is (Int) -> Int





        //Non-literal values of function types with and without receiver are interchangeable, so that the receiver can
        // stand in for the first parameter, and vice versa. For instance, a value of type (A, B) -> C can be passed or assigned
        // where a A.(B) -> C is expected
        val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
        val twoParameters: (String, Int) -> String = repeatFun // OK
        fun runTransformation(f: (String, Int) -> String): String {
            return f("hello", 3)
        }
        val result = runTransformation(repeatFun) // OK
        Log.d("test", "Result=$result")




        //Invoking a function type instance
        //A value of a function type can be invoked by using its invoke() operator: funName.invoke(x) or just funName(x).
        /*If the value has a receiver type, the receiver object should be passed as the first argument.
         * Another way to invoke a value of a function type with receiver is to prepend it with the receiver object,
         * as if the value were an extension function: 1.foo(2)
         *  */
        val stringPlus: (String, String) -> String = String::plus
        val intPlus: Int.(Int) -> Int = Int::plus
        Log.d("test", ""+stringPlus.invoke("<-", "->"))
        Log.d("test", ""+stringPlus("Hello, ", "world!"))
        Log.d("test", ""+intPlus.invoke(1, 1))
        Log.d("test", ""+intPlus(1, 2))
        Log.d("test", ""+2.intPlus(3)) // extension-like call




        //Kotlin Closures
        /*Closures are functions that can access and modify properties defined outside the scope of the function.
        *The following closure function is a high order function that calculates the sum of all elements of the list and
        * updates a property defined outside the closure.
        * */
        var res = 0
        myList = listOf(1,2,3,4,5,6,7,8,9,10)
        myList.forEach { res+=it }
        Log.d("test", ""+res) //prints 55


        //Inline Function
        foo()
    }

    private fun functionReference1(str: String, expression: (String) -> String) {
        Log.d("test", "" + str)
        expression(str)
    }

    private fun functionReference2(str: String, expression: (String) -> Unit) {
        // Log.d("test", "High Oder Function")
        expression(str)
    }

    private fun printFunction(str: String) {
        Log.d("test", "" + str)
    }

    private fun printMe(string: () -> Unit) {
        string()
    }

    private fun printMe2(string: (String) -> Unit) {
        string("Hello")
    }

    val anoSum = fun(x: Int, y: Int): Int = x + y

    private inline fun inlined(block: () -> Unit) { //InlineFunction
        Log.d("test", "hi!")
    }
    fun foo() {
        inlined {
            return // OK: the lambda is inlined
        }
    }
}