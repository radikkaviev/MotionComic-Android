package com.comicul_inc_dev_comima

import android.annotation.SuppressLint
import android.graphics.*
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.tapadoo.alerter.Alerter
import org.json.JSONObject
import java.io.*
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap


class ShowAnimation : AppCompatActivity() {

    val mapValue = LinkedHashMap<String,JSONObject>()
    val mapValueImages = LinkedHashMap<String,JSONObject>()

    var player = MediaPlayer()
    var ssk = ""
    var sc = ""

    private var timer: CountDownTimer? = null

    var bitmapsArray : ArrayList<Bitmap> = arrayListOf<Bitmap>()
    var fileNameString : ArrayList<String> = arrayListOf()

    var count:Int = 0
    var bitmapHashMap = HashMap<String,Bitmap>()

    @SuppressLint("WrongThread")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mapValue.clear()

        val bundle: Bundle? = intent.extras
        val id = bundle?.get("name")
        val language = bundle?.get("location")
        val lFile = File(language.toString())
        val index = lFile.name.lastIndexOf(".")
        val name: String = lFile.name.substring(0, index)
        var file2 = File(lFile.parent+"/"+name+".zip")

        copy(lFile,file2)

        lFile.setExecutable(true)

        val zipFile = ZipFile(file2.absolutePath)

        val entries: Enumeration<out ZipEntry> = zipFile.entries()
        Alerter.create(this@ShowAnimation)
            .setTitle("Loading file")
            .setText("Please wait...")
            .show()

        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            val stream: InputStream = zipFile.getInputStream(entry)
            var fileName = entry.name
            val file = File(file2.absolutePath, fileName)
            if (fileName.contains(".png")) {

                val myBitmap = BitmapFactory.decodeStream(stream)
                bitmapsArray.add(myBitmap)
                bitmapHashMap.put(fileName,myBitmap)

            }

            if (fileName.contains(".mp3") || fileName.contains(".ogg"))
            {
                fileNameString.add(file.absolutePath)
            }

            if (fileName.contains(".ssk"))
            {
                val bytes = ByteArray(entry.size.toInt())
                var i = 0
                while (i < bytes.size) {
                    // .read doesn't always fill the buffer we give it.
                    // Keep calling it until we get all the bytes for this entry.
                    i += stream.read(bytes, i, bytes.size - i)
                }
                var ii = String(bytes)
                ssk = ii
            }

            if (fileName.contains(".sc"))
            {
                val bytes = ByteArray(entry.size.toInt())
                var i = 0
                while (i < bytes.size) {
                    // .read doesn't always fill the buffer we give it.
                    // Keep calling it until we get all the bytes for this entry.
                    i += stream.read(bytes, i, bytes.size - i)
                }
                var ii = String(bytes)
                sc = ii

            }
        }

        // Media player code begins

//        if (fileNameString.size > 0) {
//            try {
//                player.setDataSource(fileNameString.get(count))
//                //player.setDataSource(file2.absolutePath+"/"+fileNameString.get(0))
//                Log.d("+++fileName mp30+++", fileNameString.get(count))
//                player.prepare()
//            } catch (e: IllegalArgumentException) {
//                Log.d("+++fileName mp3+++", e.toString())
//                e.printStackTrace()
//            } catch (e: java.lang.Exception) {
//                println("Exception of type : $e")
//                Log.d("+++fileName mp3+++", e.toString())
//                e.printStackTrace()
//            }
//            player.start()
//        }
//
//        player.setOnCompletionListener {
//            count++
//            it.stop()
//            it.reset()
//            if (fileNameString.size>count) {
//                Log.d("++filenameString++",fileNameString.get(count))
//                it.setDataSource(fileNameString.get(count))
//                //it.setDataSource(file2.absolutePath+"/"+fileNameString.get(count))
//                it.prepare()
//                it.start()
//            }
//            else
//            {
//                it.stop()
//            }
//        }

        // Media player code stops


        var json_sc = JSONObject(sc)
        val data = json_sc["data"]

        var json_data = JSONObject(data.toString())

        val keys: Iterator<String> = json_data.keys()
        val map = LinkedHashMap<String,JSONObject>()

        while (keys.hasNext()) {
            val key = keys.next()
            map.put(key,JSONObject(json_data.get(key).toString()))
        }

       for (mapVal in map)
       {
           if (mapVal.value["tagName"].equals("defaultColor")) {
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key,mapVal.value)

           }
           if (mapVal.value["tagName"].equals("sfade")) {
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
           }

           if (mapVal.value["tagName"].equals("chara_show")) {
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
               mapValueImages.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
           }
           if (mapVal.value["tagName"].equals("wait")) {
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
           }
           if (mapVal.value["tagName"].equals("playse")) {
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
           }
       }
        if (Alerter.isShowing)
        {
            Alerter.hide()
        }
        setImageView()

    }


    fun yourMethod() {
        if (bitmapsArray.size>=1) {
            bitmapsArray.removeAt(bitmapsArray.size - 1)
        }
        setImageView()
    }

    fun setImageView()
    {

        if (mapValueImages.size>0) {
            loop@ for (dataJson in mapValueImages) {
                Log.d("+++ tagName mapValue+++", dataJson.value.toString())
                if (dataJson.key.contains("chara_show")) {
                    Log.d("+++ tagName image+++", dataJson.value.get("image").toString())
                    if (dataJson.value.get("image").toString().length > 0) {
                        var anchorXX = JSONObject(dataJson.value.get("anchorX").toString())
                        var anchorValue = anchorXX["value"].toString()
                        Log.d("+++ anchorValue +++", anchorValue.toString())
                        var imageXX = JSONObject(dataJson.value.get("name").toString())
                        var imageValue = imageXX["value"]
                        Log.d("+++imageValue+++", imageValue.toString())
                        var posXX = JSONObject(dataJson.value["posX"].toString())
                        var posXXValue = posXX["value"].toString()
                        var posYY = JSONObject(dataJson.value["posY"].toString())
                        var posYYValue = posYY["value"].toString()
                        var anchorYY = JSONObject(dataJson.value.get("anchorY").toString())
                        var anchorYYValue = anchorYY["value"].toString()
                        var waitXX = JSONObject(dataJson.value["wait"].toString())
                        var waitXXValue = waitXX["value"]
                        var timeXX = JSONObject(dataJson.value["time"].toString())
                        var timeXXValue = timeXX["value"].toString()
                        if (waitXXValue.equals("0"))
                        {
                            waitXXValue = "3000"
                        }
                        var scaleXX = JSONObject(dataJson.value["scale"].toString())
                        var scaleXXValue = scaleXX["value"]

                        timer = object : CountDownTimer(3000, 1000) {
                            override fun onTick(millisUntilFinished: Long) {}
                            override fun onFinish() {
                                try {
                                    val inFromRight: Animation = TranslateAnimation(
                                        Animation.START_ON_FIRST_FRAME, posXXValue.toFloat(),
                                        Animation.START_ON_FIRST_FRAME, anchorValue.toFloat(),
                                        Animation.START_ON_FIRST_FRAME, 0f,
                                        Animation.START_ON_FIRST_FRAME, 0f
                                    )
                                    inFromRight.duration = 3000
                                    var imageView = ImageView(this@ShowAnimation)
                                    imageView.startAnimation(inFromRight)

                                    val indexValue = imageValue.toString().lastIndexOf("/")
                                    val newStringImage = imageValue.toString().substring(indexValue+1)
                                    Log.d("+++ newStringImage +++","Yes "+imageValue.toString().substring(indexValue+1))

                                        for (imageValue in bitmapHashMap) {
                                            if (imageValue.key.contains(newStringImage)) {
                                                Log.d(
                                                    "+++ yes +++",
                                                    imageValue.key + " Yes " + newStringImage
                                                )
                                                imageView.setImageBitmap(imageValue.value)
                                                findViewById<ConstraintLayout>(R.id.constraintLayout).addView(
                                                    imageView
                                                )
                                                mapValueImages.remove(dataJson.key)
                                                yourMethod()
                                                bitmapHashMap.remove(imageValue.key)
                                            }
                                        }
                                } catch (e: java.lang.Exception) {
                                    Log.e("Error", "Error: $e")
                                }
                            }
                        }.start()

                        break@loop

                    }
                }
                Log.d("+++ tagName mapValue+++", dataJson.value.toString())
            }
        }
    }

    @Throws(IOException::class)
    fun copy(src: File?, dst: File?) {
        val `in`: InputStream = FileInputStream(src)
        val out: OutputStream = FileOutputStream(dst)

        // Transfer bytes from in to out
        val buf = ByteArray(1024)
        var len: Int
        while (`in`.read(buf).also { len = it } > 0) {
            out.write(buf, 0, len)
        }
        `in`.close()
        out.close()
    }
}


