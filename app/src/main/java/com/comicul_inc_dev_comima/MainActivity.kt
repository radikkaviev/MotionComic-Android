package com.comicul_inc_dev_comima

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidbuffer.kotlinfilepicker.KotConstants
import com.androidbuffer.kotlinfilepicker.KotRequest
import com.androidbuffer.kotlinfilepicker.KotResult
import java.io.File


class MainActivity : AppCompatActivity() {

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
            //openFile(true)
        })
    }

    fun createDetailsFromResult(kotResult: KotResult) {
        //this function creates the details dialog from the result
        Toast.makeText(this,kotResult.toString(),Toast.LENGTH_LONG).show()
        Log.d("++++++++",kotResult.location)
        val selectedFilePath: String = FileUtility.getRealPath(this, kotResult.uri)
        intent = Intent(applicationContext, Main2Activity::class.java)
        intent.setAction(Intent.ACTION_VIEW)
        intent.putExtra("name",kotResult.name)
        intent.putExtra("location",kotResult.location)
        //intent.setDataAndType(selectedFile, "/*")
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (REQUEST_FILE == requestCode && resultCode == Activity.RESULT_OK) {

            val result = data?.getParcelableArrayListExtra<KotResult>(KotConstants.EXTRA_FILE_RESULTS)
            createDetailsFromResult(result!!.get(0))

        }


        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            if (selectedFile != null) {
                abc = selectedFile
            }
            //val selectedFilePath: String = FileHelper.getPath(this, selectedFile)
            val selectedFilePath: String = FileUtility.getRealPath(this, selectedFile)

            Log.d("+++selectedFilePath++",selectedFilePath)
            val uri = data?.getData()
            val file = File(uri?.getPath())
            Log.d("+++selectedFilePath++",file.exists().toString())
            file.getName()



            //Log.d("++name++",file.listFiles().size.toString())
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
//                intent = Intent(applicationContext, Main2Activity::class.java)
//                intent.putExtra("name",cursor.getString(nameIndex))
//                intent.putExtra("location",selectedFile.toString())
//                //intent.putExtra("location",uri)
//                startActivity(intent)

//                val index = cursor.getString(nameIndex).lastIndexOf(".")
//                Log.d("+++++",index.toString())
//                //print filename
//                //System.out.println(file.getName().substring(0, index));
//                //print extension
//                //System.out.println(file.getName().substring(index));
//                //print filename
//                //System.out.println(file.getName().substring(0, index));
//                //print extension
//                //System.out.println(file.getName().substring(index));
////            val ext = file.name.substring(index)
//                val name: String = file.getName().substring(0, index)
//                //use file.renameTo() to rename the file
//                //use file.renameTo() to rename the file
//                val file2 = File(file.parent+"/"+name+".zip")
//
//                Log.d("++file2+++",file2.getName())

                intent = Intent(applicationContext, Main2Activity::class.java)
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

    private fun openFile(isMultiple: Boolean) {
        //opens a file intent
        KotRequest.File(this, REQUEST_FILE).isMultiple(isMultiple).setMimeType(KotConstants.FILE_TYPE_FILE_ALL).pick()
    }
}
