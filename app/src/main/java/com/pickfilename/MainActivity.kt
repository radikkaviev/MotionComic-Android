package com.pickfilename

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.data
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.provider.OpenableColumns
import android.widget.TextView
import java.io.File


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.pickFileButton).setOnClickListener(View.OnClickListener {

            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            Log.d("+++++",selectedFile.toString())
            val uri = data?.getData()
            val file = File(uri?.getPath())
            file.getName()
            Log.d("+++++",file.getName())
            data?.data?.let { returnUri ->
                contentResolver.query(returnUri, null, null, null, null)
            }?.use { cursor ->
                /*
                 * Get the column indexes of the data in the Cursor,
                 * move to the first row in the Cursor, get the data,
                 * and display it.
                 */
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                Log.d("+++++",cursor.getString(nameIndex))
                Log.d("+++++",cursor.getLong(sizeIndex).toString())
                //findViewById<TextView>(R.id.fileName).text = cursor.getString(nameIndex)
                intent = Intent(applicationContext, Main2Activity::class.java)
                intent.putExtra("name",cursor.getString(nameIndex))
                intent.putExtra("location",selectedFile.toString())
                startActivity(intent)
            }
        }

    }
}
