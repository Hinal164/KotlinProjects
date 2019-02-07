package com.example.android_pc.kotlinbasics

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.row_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class SecondActivity : AppCompatActivity() {
    private val list = ArrayList<User>() //val :->We can assign single time
    private var adapter:DemoAdapter?=null //var :->we can assign multiple times
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter=DemoAdapter(list,this)
        recyclerView.adapter=adapter
        callApi()
    }

    private fun callApi() {
        val apiInterface = ApiInterface.create()
        val call = apiInterface.getPhotos()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body()?.let { list.addAll(it) }
                adapter!!.notifyDataSetChanged()
                Log.d("Response", list.size.toString())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Failure", "Failed to get response")

            }

        })
    }

    class DemoAdapter(private val list: List<User>, private val context: Context) : RecyclerView.Adapter<DemoAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DemoAdapter.MyViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.row_item, viewGroup, false)

            return MyViewHolder(view)
        }

        override fun onBindViewHolder(myViewHolder: DemoAdapter.MyViewHolder, i: Int) {

            myViewHolder.bindItems(list[i])
          /*  myViewHolder.albumId.text = "Album Id:" + list[i].albumId.toString()
            myViewHolder.id.text = "Id:" + list[i].id.toString()
            myViewHolder.title.text = "Title:" + list[i].title
            Glide.with(context).load(list[i].thumbnailUrl).into(myViewHolder.img)
*/
        }

        override fun getItemCount(): Int {
            return list.size
        }

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItems(user: User){
               /* itemView.txtAlbumId.text ="Album id: " +user.albumId.toString()
                itemView.txtId.text="Id:" +user.id.toString()
                itemView.txtTitle.text= "Title:" + user.title
                Glide.with(context).load(user.thumbnailUrl).into(itemView.img)*/
            }
          /*  var img: ImageView
            var albumId: TextView
            var id: TextView
            var title: TextView

            init {

                albumId = itemView.findViewById(R.id.txtAlbumId) as TextView
                id = itemView.findViewById(R.id.txtId) as TextView
                title = itemView.findViewById(R.id.txtTitle) as TextView
                img = itemView.findViewById(R.id.img) as ImageView
            }*/
        }
    }
}