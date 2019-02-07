package com.example.android_pc.kotlinbasics

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var isEdit:Boolean =false
    private var user=User()
    private val REQUEST_CAMERA = 0
    private val SELECT_FILE = 1
    private var imageFilePath:String? =null
    private var userChosenTask: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sqLiteHelper: SQLiteHelper= SQLiteHelper(this)

        val edName=findViewById<EditText>(R.id.edName)
        val edAge=findViewById<EditText>(R.id.edAge)
        val edMob=findViewById<EditText>(R.id.edMob)
        val edEmail=findViewById<EditText>(R.id.edEmail)
        val button=findViewById<Button>(R.id.submit)
        val img=findViewById<ImageView>(R.id.img)
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
            user.image=imageFilePath


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

                    sqLiteHelper.updateRecord(user)
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
        img.setOnClickListener {
            val items = arrayOf<CharSequence>("Camera", "Gallery")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add Photo")
            builder.setItems(items) { dialog, item ->
                val result = Utility.checkPermission(this)
                if (items[item] == "Camera") {
                    userChosenTask = "Camera"
                    if (result)
                        cameraIntent()

                } else if (items[item] == "Gallery") {
                    userChosenTask = "Choose from Library"
                    if (result)
                        galleryIntent()
                }
            }
            builder.show()
        }

    }

    private fun setData() {
        user = intent.getParcelableExtra("Update")
        edName.setText(user.name)
        edEmail.setText(user.email)
        edAge.setText(user.age.toString())
        edMob.setText(user.mob.toString())
        imageFilePath=user.image
        Glide.with(this).load(imageFilePath).into(img)
    }

    private fun galleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE)
    }

    private fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            //Create a file to store the image
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(this, "com.example.android_pc.kotlinbasics.provider", photoFile)//applicationID.provider
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(intent, REQUEST_CAMERA)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = image.absolutePath
        Log.i("imagepath", "" + imageFilePath)
        return image
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_FILE && resultCode == Activity.RESULT_OK) {

            val selectedImageUri = data!!.data

            imageFilePath = getPath(this, selectedImageUri)
            Glide.with(this).load(imageFilePath).into(img)

        } else if (requestCode == REQUEST_CAMERA)
            Glide.with(this).load(imageFilePath).into(img)

    }

    fun getPath(context: Context, uri: Uri?): String {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri!!, proj, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }
        if (result == null) {
            result = "Not found"
        }
        return result
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {

            Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE ->
             if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (userChosenTask == "Camera")
                    cameraIntent()
                else if (userChosenTask == "Gallery")
                    galleryIntent()
            }
        }
    }
}
