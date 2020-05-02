package com.comicul_inc_dev_comima

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class SelectFIle : AppCompatActivity() {

    private val REQUEST_FILE = 103

    companion object {

        lateinit var abc : Uri
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hasStoragePermission(11)
        hasStoragePermissionRead(111)

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
            if (selectedFile != null) {
                abc = selectedFile
            }
            val selectedFilePath: String? = FileUtils.getRealPath(this, selectedFile)
            val uri = data?.getData()
            val file = File(uri?.getPath())
            Log.d("+++selectedFilePath++",file.exists().toString())
            file.getName()

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

                intent = Intent(applicationContext, ShowAnimation::class.java)
                intent.setAction(Intent.ACTION_VIEW)
                intent.putExtra("name",cursor.getString(nameIndex))
                intent.putExtra("location",selectedFilePath)
                intent.setDataAndType(selectedFile, "/*")
                startActivity(intent)
            }
        }

    }

    private fun hasStoragePermission(requestCode: Int): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    requestCode
                )
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    private fun hasStoragePermissionRead(requestCode: Int): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    requestCode
                )
                false
            } else {
                true
            }
        } else {
            true
        }
    }
}
