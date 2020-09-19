package com.example.shiftlab

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val UserName:String = intent.getStringExtra("UserName").toString()
        val UserFamely:String = intent.getStringExtra("UserFamely").toString()
        val UserBirthDay:String = intent.getStringExtra("UserBirthDay").toString()
        val ButtonHello: ImageView = findViewById(R.id.buttonhello)


        ButtonHello.setOnClickListener {

            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Приветствие")
                .setMessage("Здраствуй " + UserName.toString() + " " + UserFamely.toString() + " "+UserName.toString()+" " +UserFamely.toString() + " "+ UserBirthDay.toString())
                .setCancelable(false)
                .setPositiveButton("Круто") { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
            var alert: AlertDialog = builder.create()
            alert.show()
        }


    }


}