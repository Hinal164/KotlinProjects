package com.example.android_pc.kotlinbasics

import android.os.Parcel
import android.os.Parcelable

class User() : Parcelable {

    var id: Int = 0
    var name: String? = null
    var age: Int = 0
    var mob: Long = 0
    var email: String? = null
    var image: String? = null

    constructor(name: String, age: Int, mob: Long, email: String) : this() {
        this.name = name
        this.age = age
        this.mob = mob
        this.email = email
        this.image = image
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel!!.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeLong(mob)
        parcel.writeString(email)
        parcel.writeString(image)

    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun readIn(parcel: Parcel) {
        id = parcel.readInt()
        name = parcel.readString()
        age = parcel.readInt()
        mob = parcel.readLong()
        email = parcel.readString()
        image=parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            var user = User()
            user.readIn(parcel)
            return user
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }


}