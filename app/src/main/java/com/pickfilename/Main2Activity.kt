package com.pickfilename

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bundle: Bundle? = intent.extras
        val id = bundle?.get("name")
        val language = bundle?.get("location")
        findViewById<TextView>(R.id.fileName).text = "Name: " + id +"\n \nPath: "+language
    }
}
