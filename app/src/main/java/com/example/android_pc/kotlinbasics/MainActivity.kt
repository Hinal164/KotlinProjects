package com.example.android_pc.kotlinbasics

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isEdit:Boolean =false
    private var user=User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sqLiteHelper: SQLiteHelper= SQLiteHelper(this)

        val edName=findViewById<EditText>(R.id.edName)
        val edAge=findViewById<EditText>(R.id.edAge)
        val edMob=findViewById<EditText>(R.id.edMob)
        val edEmail=findViewById<EditText>(R.id.edEmail)
        val button=findViewById<Button>(R.id.submit)
        var success:Boolean=true
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        isEdit = intent.hasExtra("Update")

        if(isEdit){
            button.text = "UPDATE"
            setData()
        }else{
            button.text="SUBMIT"
        }

        button.setOnClickListener{


            user.name=edName.text.toString()
            user.age=edAge.text.toString().toInt()
            user.mob=edMob.text.toString().toLong()
            user.email=edEmail.text.toString()


            if(edName.text.toString().length<5 || edName.text.isEmpty()){
                edName.error="Username must contain 5 character"
                edName.requestFocus()
                success=false
            }
            if(edAge.text.toString().length!=2 || edAge.text.isEmpty()){
                edAge.error="Age must contain 2 digit"
                edAge.requestFocus()
                success=false

            }
            if(edMob.text.toString().length!=10 || edMob.text.isEmpty()){
                edMob.error="Mobile number must contain 10 digits"
                edMob.requestFocus()
                success=false

            }
            if(!emailPattern.toRegex().matches(edEmail.text.toString()) || edEmail.text.isEmpty()){
                edEmail.error="InValid Email id"
                edEmail.requestFocus()
                success=false

            }
            if(success){
                if(isEdit){

                    val position = intent.getIntExtra("Position", -1)
                    Log.d("POSITION", user.name)

                    sqLiteHelper.updateRecord(user.id.toString(), user.name.toString(), user.age.toString(), user.mob.toString(),user.email.toString())
                    Toast.makeText(applicationContext, "Data is successfully updated", Toast.LENGTH_SHORT).show()

                    val returnIntent = Intent()
                    returnIntent.putExtra("updatedUser", user)
                    returnIntent.putExtra("positionUser", position)
                    Log.d("positionReturn", "" + position)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }else{

                    Log.d("userModel",user.name)
                    sqLiteHelper.addUser(user)
                    val returnIntent = Intent()
                    returnIntent.putExtra("userModel",user)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }

            }
        }

    }

    private fun setData() {
        user = intent.getParcelableExtra("Update")
        edName.setText(user.name)
        edEmail.setText(user.email)
        edAge.setText(user.age.toString())
        edMob.setText(user.mob.toString())
    }

}
