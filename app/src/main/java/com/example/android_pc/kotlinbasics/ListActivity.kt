package com.example.android_pc.kotlinbasics

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activtiy_list.*
import kotlinx.android.synthetic.main.user_row_item.view.*

class ListActivity : AppCompatActivity(), MyClickListener {


    private var adapter: UserAdapter? = null
    private var list = ArrayList<User>()
    private var user = User()
    private val REQUEST_USER: Int = 0
    private val REQUEST_CODE: Int = 1
    private var sqLiteHelper: SQLiteHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activtiy_list)
        val button = findViewById<Button>(R.id.btn)
        sqLiteHelper = SQLiteHelper(this)

        button.setOnClickListener {
            val intent = Intent(this@ListActivity, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_USER)
        }

        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(list, this, this)
        recyclerView.adapter = adapter
        list.addAll(sqLiteHelper!!.fetchRecord())

    }

    override fun myClick(view: View, position: Int) {
        when (view.id) {
            R.id.delete_layout -> {
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
                val user= list[position]
                sqLiteHelper!!.deleteRecord(user.id)
                list.removeAt(position)
                adapter!!.notifyItemRemoved(position)

            }
            R.id.edit_layout -> {
                val user = list[position]
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Update", user)
                intent.putExtra("Position", position)
                startActivityForResult(intent, REQUEST_CODE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

             if (requestCode == REQUEST_CODE) {

                 val userObject = data!!.getParcelableExtra<User>("updatedUser")
                 val userPosition = data.getIntExtra("positionUser", -1)

                 list.removeAt(userPosition)
                 list.add(userPosition, userObject)
                 adapter!!.notifyDataSetChanged()

             } else if (requestCode == REQUEST_USER) {
                if (data != null) {
                    user = data.getParcelableExtra("userModel")
                    list.add(user)
                    adapter!!.notifyDataSetChanged()
                    Log.d("name:", user.name)
                }
            }
        }
    }

    class UserAdapter(
        private var list: List<User>,
        private var context: Context,
        val listener: MyClickListener
    ) : RecyclerView.Adapter<UserAdapter.MyHolder>() {


        inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
            override fun onClick(view: View) {
                when (view.id) {
                    R.id.delete_layout, R.id.edit_layout -> listener.myClick(view, adapterPosition)
                }
            }

            fun bindItems(user: User) {
                itemView.txtName.text = "Name: " + user.name
                itemView.txtAge.text = "Age: " + user.age.toString()
                itemView.txtMob.text = "Mob: " + user.mob.toString()
                itemView.txtEmail.text = "Email: " + user.email

                itemView.delete_layout.setOnClickListener(this)
                itemView.edit_layout.setOnClickListener(this)

            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): UserAdapter.MyHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.user_row_item, viewGroup, false)
            return MyHolder(view)
        }

        override fun getItemCount(): Int {
            Log.d("size:", list.size.toString())
            return list.size
        }

        override fun onBindViewHolder(holder: UserAdapter.MyHolder, position: Int) {
            holder.bindItems(list[position])

        }

    }


}
