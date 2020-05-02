package com.comicul_inc_dev_comima

//import org.apache.tools.ant.taskdefs.Zip
//import org.jszip.maven.JSZipMojo

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import org.json.JSONObject
import java.io.*
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap


class Main2Activity : AppCompatActivity() {

    val mapValue = LinkedHashMap<String,JSONObject>()
    val mapValueImages = LinkedHashMap<String,JSONObject>()

    var player = MediaPlayer()
    var ssk = ""
    var sc = ""

//    private var _imagView: ImageView? = null
//    private var _timer: Timer? = null
//    private var _index = 0
//    private var handler: MyHandler? = null

    private var timer: CountDownTimer? = null

    var bitmapsArray : ArrayList<Bitmap> = arrayListOf<Bitmap>()
    var fileNameString : ArrayList<String> = arrayListOf()
    var animationDrawable = AnimationDrawable()

    private lateinit var webView: WebView
    lateinit var bmp : Bitmap
    lateinit var canvas: Canvas
    lateinit var paint: Paint
    lateinit var path: Path
    private val SDPath = Environment.getExternalStorageDirectory().absolutePath
    private val SDPath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//    private val zipPath = "$SDPath/Downloads/"
    private val unzipPath = "$SDPath1/"
//    private val dataPath = "$SDPath/AndroidCodility/zipunzipFile/data/"
    var count:Int = 0
//    val context : Context = applicationContext
    var bitmapHashMap = HashMap<String,Bitmap>()
    lateinit var file2 : File

    @SuppressLint("WrongThread")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mapValue.clear()

        val bundle: Bundle? = intent.extras
        val id = bundle?.get("name")
        val language = bundle?.get("location")
        Log.d("+++language+++",language.toString())
        Log.d("+++name+++",id.toString())
        //findViewById<TextView>(R.id.fileName).text = "Name: " + id +"\n \nPath: "+language

        //this.contentResolver.openInputStream(MainActivity.abc)?.reader()?.readText()

        //content://com.google.android.apps.docs.storage/document/acc%3D4%3Bdoc%3Dencoded%3DYy8MM9deYGAi%2F5c%2F6GSZz6jR2yZo8xtTf59M%2F3BARa%2FvGkJxqOhJxaIMoRwh

//        webView = findViewById(R.id.webView)
//        var webViewSettings = webView.settings
//        webViewSettings.javaScriptEnabled
//        webViewSettings.allowFileAccessFromFileURLs
//        webViewSettings.allowUniversalAccessFromFileURLs
//        webView.settings.javaScriptEnabled
//        webView.webChromeClient = object : WebChromeClient() {}


        //val lFile = File(unzipPath+ id.toString())
        val lFile = File(language.toString())
        val dataFile = File(language.toString(),id.toString()+".zip")

        val index = lFile.name.lastIndexOf(".")
        val ext = lFile.name.substring(index)

        val name: String = lFile.name.substring(0, index)
                //use file.renameTo() to rename the file
                //use file.renameTo() to rename the file
        var file2 = File(lFile.parent+"/"+name+".zip")

        copy(lFile,file2)

        lFile.setExecutable(true)
        Log.d("+++size+++",file2.toString())
//        readFileUsingGetResource(lFile.name)

        val zipFile = ZipFile(file2.absolutePath)

        val entries: Enumeration<out ZipEntry> = zipFile.entries()

        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            val stream: InputStream = zipFile.getInputStream(entry)
            Log.d("+++sizeinput+++",stream.toString())
            Log.d("+++sizeinput+++",entry.name)
            var fileName = entry.name
            Log.d("+++fileName+++", fileName)

            //fileName = fileName.substring(fileName.indexOf("/") + 1)
            val file = File(file2.absolutePath, fileName)


            if (fileName.contains(".png")) {

                val myBitmap = BitmapFactory.decodeStream(stream)
                bitmapsArray.add(myBitmap)
                bitmapHashMap.put(fileName,myBitmap)

                var b = drawMultipleBitmapsOnImageView(myBitmap)
                //findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
                //var frame : Drawable = BitmapDrawable(myBitmap)
                //animationDrawable.addFrame(frame,250)
                //findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
                //FileHelper.ImageViewAnimatedChange(this,findViewById<ImageView>(R.id.imageView),myBitmap)
            }

            if (fileName.contains(".mp3") || fileName.contains(".ogg"))
            {
                Log.d("+++fileName mp3+++", ""+SDPath1+"/STK.zip/"+fileName)
                Log.d("+++fileName mp3+++", file.absolutePath)
                fileNameString.add(file.absolutePath)
                Log.d("+++ fileNameString +++", fileNameString.size.toString())
//                    val player = MediaPlayer()
//
//                    try {
//                        player.setDataSource(file.absolutePath)
//                        player.prepare()
//                    } catch (e: IllegalArgumentException) {
//                        Log.d("+++fileName mp3+++", e.toString())
//                        e.printStackTrace()
//                    } catch (e: java.lang.Exception) {
//                        println("Exception of type : $e")
//                        Log.d("+++fileName mp3+++", e.toString())
//                        e.printStackTrace()
//                    }
////
//                    player.start()
//                    val expansionFile = ZipResourceFile("myZipFile.zip")
//                    val assetFileDescriptor: AssetFileDescriptor =
//                        expansionFile.getAssetFileDescriptor("myMusic.mp3")
//                    try {
//                        mediaPlayer.setDataSource(assetFileDescriptor.fileDescriptor)
//                        mediaPlayer.prepare()
//                        mediaPlayer.start()
//                    } catch (e: IOException) {
//                        // Handle exception
//                    }
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
//                var file1 = File(file2.absolutePath+"/"+fileName)
//                var file3 = File(file2.absolutePath+"/"+"main.txt")
//                copy(file1,file3)
                var ii = String(bytes)
                Log.d("++sskfile+++",ii)

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
//                var file1 = File(file2.absolutePath+"/"+fileName)
//                var file3 = File(file2.absolutePath+"/"+"main.txt")
//                copy(file1,file3)
                var ii = String(bytes)
                Log.d("++scfile+++",ii)

                sc = ii
                //Toast.makeText(this,builder.toString(),Toast.LENGTH_SHORT).show()

            }



            Log.d("+++bitmapsArray+++", bitmapsArray.size.toString())
        }

//        var zip = Zip()
//        zip.setZipfile(lFile)
//        zip.setFile(lFile)
//        zip.execute()


//        val zip = JSZipMojo()
//        window.fetch("/kitten-photos.zip")
//            .then { it.arraĀBuffer() }
//            .then { zip.loadAsĀnc(it) }
//            .then { it.file("lucĀ.jpg") }
//            .then { it.asĀnc("blob") as Promise<Blob> }
//            .then {
//                val objectUrl = URL.createObjectURL(it)
//                val img = document.querĀSelector("#kittenImage")
//                img as HTMLImageElement
//                img.src = objectUrl
//            }
//        val decodedAnimation = ImageDecoder.decodeDrawable(
//            // create ImageDecoder.Source object
//            ImageDecoder.createSource(lFile))
//// set the drawble as image source of ImageView
//        findViewById<ImageView>(R.id.imageView).setImageDrawable(decodedAnimation)
//// play the animation
//        (decodedAnimation as? AnimatedImageDrawable)?.start()
//
//        //webView.loadUrl("file:///" + lFile.absolutePath)
//
////        var animationDrawable = AnimationDrawable.createFromPath(unzipPath+ "STK.data")
////        animationDrawable?.setVisible(true,true)
//
//        var animation = Animation.START_ON_FIRST_FRAME


//        try {
//            val inputStream: InputStream = assets.open("STK.data")
//            val inputStreamReader = InputStreamReader(inputStream)
//            val sb = StringBuilder()
//            var line: String?
//            val br = BufferedReader(inputStreamReader)
//            line = br.readLine()
//            while (br.readLine() != null) {
//                sb.append(line)
//                line = br.readLine()
//            }
//            br.close()
//            Log.d("++++",sb.toString())
//        } catch (e:Exception){
//            Log.d("++++", e.toString())
//        }

        //Log.d("++++++",MainActivity.abc.toString())
        //Glide.with(this).asGif().load(lFile).into(findViewById(R.id.imageView));
        //Glide.with(this).asBitmap().load(R.drawable.ic_launcher_background).into(R.id.imageView)
        //Glide.with(this).load(lFile).into(findViewById<ImageView>(R.id.imageView))

//        findViewById<LottieAnimationView>(R.id.animation_view).addValueCallback(
//            KeyPath("Shape Layer", "Rectangle", "Fill"),
//            LottieProperty.COLOR_FILTER) { ColorFilter() }



        //webView.loadData(this.contentResolver.openInputStream(MainActivity.abc)?.reader()?.readText(), ".data", "utf-8")

        //findViewById<ImageView>(R.id.imageView).setImageBitmap(BitmapFactory.decodeFile(language.toString()))



        var file = File(language.toString())
        Log.d("++zz++", file.isFile.toString())
        Log.d("++zz++", readFileUsingGetResource(id.toString()).toString())




//        launch {
//            val contents = withContext(Dispatchers.IO) {
//                FileInputStream("filename.txt").use { it.readBytes() }
//            }
//            processContents(contents)
//        }


//        var reader = FileReader(file)
//        reader.read()

        //val content = file.readText()
        //Log.d("++++", content.length.toString())
        findFolderInfo(file)

        File(language.toString()).walk().forEach {
            Log.d("++nn++",it.name)
        }



//        var zip = ZipFile(file2)
//        zip.size().toString()
//        Log.d("++zz++", zip.size().toString())




//        var zipManager = ZipFile(id.toString())
//        zipManager.getEntry("resource")
//        Log.d("+++++","resource"+zipManager.getEntry("resource"))


//        ProcessBuilder()
//            .command("unzip", unzipPath+ "STK.data")
//            .redirectError(ProcessBuilder.Redirect.INHERIT)
//            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
//            .start()
//            .waitFor()
//
//        var fileReader : FileReader

//
//        ZipFile(file2.absoluteFile).use { zip ->
//            zip.entries().asSequence().forEach { entry ->
//                zip.getInputStream(entry).use { input ->
//                    File(entry.name).outputStream().use { output ->
//                        input.copyTo(output)
//                        Log.d("+++++ziptry++++",input.copyTo(output).toString())
//                    }
//                }
//            }
//        }

        //Check for permission
        Utility().checkPermission(this)
        //unZipView(unzipPath+ id.toString())
        //unZipView(language.toString())
        Log.d("++++file2path++++",file2.absolutePath)
        //unZipView(file2.absolutePath)
        //unzipp(file2.absolutePath,file2.absolutePath)
        Log.d("++++bitmap size++++",bitmapsArray.size.toString())
        //Log.d("++++bitmap size++++",bitmapsArray.get(0).toString())

//        timer = object : CountDownTimer(2000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {}
//            override fun onFinish() {
//                try {
//                    val inFromRight: Animation = TranslateAnimation(
//                        Animation.RELATIVE_TO_PARENT, +1.0f,
//                        Animation.RELATIVE_TO_PARENT, 0.0f,
//                        Animation.RELATIVE_TO_PARENT, 0.0f,
//                        Animation.RELATIVE_TO_PARENT, 0.0f
//                    )
//                    inFromRight.duration = 500
//                    var imageView = ImageView(this@Main2Activity)
//                    imageView.startAnimation(inFromRight)
//                    imageView.setImageBitmap(bitmapsArray.get(bitmapsArray.size-1))
//                    findViewById<ConstraintLayout>(R.id.constraintLayout).addView(imageView)
////                    findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmapsArray.get(bitmapsArray.size-1))
////                    findViewById<ImageView>(R.id.imageView).startAnimation(inFromRight)
//
//                    yourMethod()
//                } catch (e: java.lang.Exception) {
//                    Log.e("Error", "Error: $e")
//                }
//            }
//        }.start()

//        if (fileNameString.size > 0) {
//            try {
//                player.setDataSource(fileNameString.get(count))
//                Log.d("+++fileName mp30+++", fileNameString.get(0))
//                player.prepare()
//            } catch (e: IllegalArgumentException) {
//                Log.d("+++fileName mp3+++", e.toString())
//                e.printStackTrace()
//            } catch (e: java.lang.Exception) {
//                println("Exception of type : $e")
//                Log.d("+++fileName mp3+++", e.toString())
//                e.printStackTrace()
//            }
////
//            player.start()
//        }

        //playerFunction()


//        player.setOnCompletionListener {
//            count++
//            it.stop()
//            it.reset()
//            if (fileNameString.size>count) {
//                it.setDataSource(fileNameString.get(count))
//                it.prepare()
//                it.start()
//            }
//            else
//            {
//                it.stop()
//            }
            //playerFunction()

//            if (fileNameString.size>=count) {
//                try {
//                    player = MediaPlayer()
//                    Log.d("+++fileName mp3+++", fileNameString.get(count))
//                    player.setDataSource(fileNameString.get(count))
//                    player.prepare()
//                } catch (e: IllegalArgumentException) {
//                    Log.d("+++fileName mp3+++", e.toString())
//                    e.printStackTrace()
//                } catch (e: java.lang.Exception) {
//                    println("Exception of type : $e")
//                    Log.d("+++fileName mp3+++", e.toString())
//                    e.printStackTrace()
//                }
////
//                player.start()
//                player.setOnCompletionListener {
//                    count++
//                    it.stop()
//
//                    if (fileNameString.size >= count) {
//                        try {
//                            player = MediaPlayer()
//                            Log.d("+++fileName mp3+++", fileNameString.get(count))
//                            player.setDataSource(fileNameString.get(count))
//                            player.prepare()
//                        } catch (e: IllegalArgumentException) {
//                            Log.d("+++fileName mp3+++", e.toString())
//                            e.printStackTrace()
//                        } catch (e: java.lang.Exception) {
//                            println("Exception of type : $e")
//                            Log.d("+++fileName mp3+++", e.toString())
//                            e.printStackTrace()
//                        }
////
//                        player.start()
//
//                    }
//
//                }

//            }
//            else
//            {
//                player.stop()
//            }
       // }

        //Toast.makeText(this,ssk,Toast.LENGTH_LONG).show()
        //Toast.makeText(this,sc,Toast.LENGTH_LONG).show()

        var json_sc = JSONObject(sc)
        //var json_ssk = JSONArray(ssk)


        Log.d("+++ jsonsse +++",json_sc.toString())
        //Log.d("+++ json_ssk +++",json_ssk.toString())
        val data = json_sc["data"]

        var json_data = JSONObject(data.toString())
        Log.d("+++json_data+++",json_data.toString())

        val keys: Iterator<String> = json_data.keys()
        val map = LinkedHashMap<String,JSONObject>()
        val mapDefaultColor = HashMap<String,DefaultColor>()


        while (keys.hasNext()) {
            val key = keys.next()
            Log.d("++value+++",json_data.get(key).toString())
            map.put(key,JSONObject(json_data.get(key).toString()))
            Log.d("++++key++++",key.toString())
        }

        Log.d("+++mapsize+++",map.size.toString())

        for (mapValue in map)
        {
            Log.d("+++mapVal+++",map.size.toString())
            Log.d("++++mapValue++++",mapValue.toString())
        }

       for (mapVal in map)
       {
           Log.d("++++mapValuetagName++++",mapVal.value["tagName"].toString())
           if (mapVal.value["tagName"].equals("defaultColor")) {
               Log.d("+++ defaultColor +++", mapVal.value.toString())
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key,mapVal.value)

           }
           if (mapVal.value["tagName"].equals("sfade")) {
               Log.d("+++ sfade +++", mapVal.value.toString())
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
           }

           if (mapVal.value["tagName"].equals("chara_show")) {
               Log.d("+++ chara_show +++", mapVal.value.toString())
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
               mapValueImages.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
           }
//
           if (mapVal.value["tagName"].equals("wait")) {
               Log.d("+++ wait +++", mapVal.value.toString())
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
           }
//
           if (mapVal.value["tagName"].equals("playse")) {
               Log.d("+++ playse +++", mapVal.value.toString())
               mapValue.put(mapVal.value["tagName"].toString()+mapVal.key, mapVal.value)
           }
       }

        Log.d("+++ mapValue +++",mapValue.size.toString())
        setImageView()

//        for (dataJson in mapValue)
//        {
//            Log.d("+++ tagName mapValue+++",dataJson.key)
//            if (dataJson.key.contains("chara_show"))
//            {
//                Log.d("+++ tagName image+++",dataJson.value.get("image").toString())
//                if (dataJson.value.get("image").toString().length>0)
//                {
//                    val anchorXX = JSONObject(dataJson.value.get("image").toString())
//                    val anchorValue = anchorXX["value"]
//                    Log.d("+++ anchorValue +++",anchorValue.toString())
//
//                }
//            }
//            Log.d("+++ tagName mapValue+++",dataJson.value.toString())
//        }
//
//
//
//        timer = object : CountDownTimer(2000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {}
//            override fun onFinish() {
//                try {
//                    val inFromRight: Animation = TranslateAnimation(
//                        Animation.RELATIVE_TO_PARENT, +1.0f,
//                        Animation.RELATIVE_TO_PARENT, 0.0f,
//                        Animation.RELATIVE_TO_PARENT, 0.0f,
//                        Animation.RELATIVE_TO_PARENT, 0.0f
//                    )
//                    inFromRight.duration = 500
//                    var imageView = ImageView(this@Main2Activity)
//                    imageView.startAnimation(inFromRight)
//                    imageView.setImageBitmap(bitmapsArray.get(bitmapsArray.size-1))
//                    findViewById<ConstraintLayout>(R.id.constraintLayout).addView(imageView)
////                    findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmapsArray.get(bitmapsArray.size-1))
////                    findViewById<ImageView>(R.id.imageView).startAnimation(inFromRight)
//
//                    yourMethod()
//                } catch (e: java.lang.Exception) {
//                    Log.e("Error", "Error: $e")
//                }
//            }
//        }.start()


        Log.d("+++ jsonsse data +++",json_data.toString())
    }




//    fun zipView(view: View) {
//        if (FileHelper.zip(dataPath, zipPath, "dummy.zip", true)) {
//            Toast.makeText(applicationContext, "Zip successfully.", Toast.LENGTH_LONG).show()
//        }
//    }

    fun unZipView(toString: String) {

//        var zipInputStream : ZipInputStream = ZipInputStream(BufferedInputStream(FileInputStream(toString)))
//        Log.d("++zipInputStream++",zipInputStream.toString())
//        zipInputStream.getNextEntry();
//
//        var sc = Scanner(zipInputStream);
//        while (sc.hasNextLine()) {
//            System.out.println(sc.nextLine());
//            Log.d("++++", sc.nextLine())
//        }
//
//        if (FileHelper.unzip( toString, unzipPath)) {
//            Toast.makeText(applicationContext, "Unzip successfully.", Toast.LENGTH_LONG).show()
//        }

        unzip(toString,unzipPath)
//        handler = MyHandler()
//        _imagView = findViewById<ImageView>(R.id.imageView)
//
//        _index = 0
//        _timer = Timer()
//        _timer.schedule(TickClass(), 500, 200)
        //findViewById<ImageView>(R.id.imageView).setBackgroundDrawable(animationDrawable)
        //val msg = Message()
        //startAnimation.sendMessage(msg)
//        var imageViewWithAnimation = ImageViewWithAnimation(this,bitmapsArray)
//        imageViewWithAnimation.launchAnimation()

    }

    fun findFolderInfo(file: File) {

        Log.d("++f++", file.canExecute().toString())


//        if (file.listFiles().size>0)
//        {
//            for (filee in file.listFiles())
//            {
//                Log.d("++++", filee.name)
//                Log.d("++++", filee.toString())
//            }
//        }
    }

    fun readFileUsingGetResource(fileName: String)
            = this::class.java.getResource(fileName)?.openStream()

//    external class JSZip {
//        fun file(name: String): Promise<ZipObject>
//        fun loadAsĀnc(data: ArraĀBuffer): Promise<JSZip>
//    }
//
//    external class ZipObject {
//        fun asĀnc(tĀpe: String): Promise<Any?>
//    }

    @Throws(IOException::class)
    private fun loadzip(folder: String, inputStream: InputStream) {
        val zipIs = ZipInputStream(inputStream)
        var ze: ZipEntry? = null
        while (zipIs.nextEntry.also { ze = it } != null) {
            val fout = FileOutputStream(folder + "/" + ze!!.getName())
            val buffer = ByteArray(1024)
            var length = 0
            while (zipIs.read(buffer).also { length = it } > 0) {
                fout.write(buffer, 0, length)
            }
            zipIs.closeEntry()
            fout.close()
        }
        zipIs.close()
    }


    fun unzip(sourceFile: String?, destinationFolder: String?): Boolean? {
        var zis: ZipInputStream? = null
        try {
            zis = ZipInputStream(
                BufferedInputStream(
                    FileInputStream(sourceFile)
                )
            )
            var ze: ZipEntry
            var count: Int
            val buffer = ByteArray(8192)
            //val buffer = ByteArray(100000000)
            while (zis.nextEntry != null) {
                ze = zis.nextEntry
                var fileName = ze.name
                Log.d("+++fileName+++", fileName)

                    fileName = fileName.substring(fileName.indexOf("/") + 1)
                    val file = File(destinationFolder, fileName)


                if (fileName.contains(".png")) {

                    val myBitmap = BitmapFactory.decodeStream(zis)
                    bitmapsArray.add(myBitmap)
                    bitmapHashMap.put(fileName,myBitmap)

                    var b = drawMultipleBitmapsOnImageView(myBitmap)
                    //findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
                    //var frame : Drawable = BitmapDrawable(myBitmap)
                    //animationDrawable.addFrame(frame,250)
                    //findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
                    //FileHelper.ImageViewAnimatedChange(this,findViewById<ImageView>(R.id.imageView),myBitmap)
                }

                if (fileName.contains(".mp3") || fileName.contains(".ogg"))
                {
                    Log.d("+++fileName mp3+++", ""+SDPath1+"/STK.zip/"+fileName)
                    Log.d("+++fileName mp3+++", file.absolutePath)
                    fileNameString.add(file.absolutePath)
                    Log.d("+++ fileNameString +++", fileNameString.size.toString())
//                    val player = MediaPlayer()
//
//                    try {
//                        player.setDataSource(file.absolutePath)
//                        player.prepare()
//                    } catch (e: IllegalArgumentException) {
//                        Log.d("+++fileName mp3+++", e.toString())
//                        e.printStackTrace()
//                    } catch (e: java.lang.Exception) {
//                        println("Exception of type : $e")
//                        Log.d("+++fileName mp3+++", e.toString())
//                        e.printStackTrace()
//                    }
////
//                    player.start()
//                    val expansionFile = ZipResourceFile("myZipFile.zip")
//                    val assetFileDescriptor: AssetFileDescriptor =
//                        expansionFile.getAssetFileDescriptor("myMusic.mp3")
//                    try {
//                        mediaPlayer.setDataSource(assetFileDescriptor.fileDescriptor)
//                        mediaPlayer.prepare()
//                        mediaPlayer.start()
//                    } catch (e: IOException) {
//                        // Handle exception
//                    }
                }

                if (fileName.contains(".ssk"))
                {
                    Log.d("++sskfile+++",fileName.reader().readText())
                    val bufferedReader = BufferedReader(FileReader(file.absoluteFile))
                    var read: String?
                    val builder = StringBuilder("")

                    while (bufferedReader.readLine().also { read = it } != null) {
                        builder.append(read)
                    }
                    Log.d("++sskfile+++", builder.toString())
                    ssk = builder.toString()
                    //Toast.makeText(this,builder.toString(),Toast.LENGTH_SHORT).show()
                    bufferedReader.close()
                }

                if (fileName.contains(".sc"))
                {
                    Log.d("++scfile+++",fileName.reader().readText())
                    val bufferedReader = BufferedReader(FileReader(file.absoluteFile))
                    var read: String?
                    val builder = StringBuilder("")

                    while (bufferedReader.readLine().also { read = it } != null) {
                        builder.append(read)
                    }
                    Log.d("++scfile+++", builder.toString())
                    sc = builder.toString()
                    //Toast.makeText(this,builder.toString(),Toast.LENGTH_SHORT).show()
                    bufferedReader.close()
                }



                Log.d("+++bitmapsArray+++", bitmapsArray.size.toString())
                //Glide.with(this).load(""+SDPath1+"/STK.zip/"+"resource/character/01コマ目_01.png").into(findViewById<ImageView>(R.id.imageView))
//                Glide.with(this)
//                    .asBitmap()
//                    .load(""+SDPath1+"/STK.zip/"+"resource/character/01コマ目_01.png")
//                    .into(findViewById<ImageView>(R.id.imageView))
//                Glide.with(this).asBitmap().load(""+SDPath1+"/STK.zip/"+"resource/character/01コマ目_01.png").error(android.R.mipmap.sym_def_app_icon).into(findViewById<ImageView>(R.id.imageView));
//                if (fileName.contains(".") && fileName.split(".").size>0 && fileName.split(".")[1].equals("png")) {
//                    Glide.with(this).load(fileName).into(findViewById<ImageView>(R.id.imageView))
//                    findViewById<ImageView>(R.id.imageView).setImageURI(Uri.parse(""+SDPath1+"/STK.zip/"+"puneet.png"))
//                }

//                val imgFile = File(""+SDPath1+"/STK.zip/"+"resource/character/01コマ目_01.png")
//
//                if (imgFile.exists()) {
//                    val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
//                    findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
//                }

                //val bmp = BitmapFactory.decodeFile(file.absolutePath)
                //findViewById<TextView>(R.id.fileName).text = ""+findViewById<TextView>(R.id.fileName).text+""+fileName

                val dir = if (ze.isDirectory) file else file.parentFile
                if (!dir.isDirectory && !dir.mkdirs()) throw FileNotFoundException("Invalid path: " + dir.absolutePath)
                if (ze.isDirectory) continue
                val fout = FileOutputStream(file)
                Log.d("+++fout+++", fout.toString())
                try {
                    while (zis.read(buffer).also { count = it } != -1) fout.write(
                        buffer,
                        0,
                        count
                    )

                } finally {
                    fout.close()
                }
            }
        } catch (ioe: IOException) {
            Log.d("++++++", ioe.message)
            return false
        } finally {
            if (zis != null) try {
                zis.close()
            } catch (e: IOException) {
            }
        }

        return true
    }


    fun delayedImageView()
    {
        if (bitmapsArray.size>0)
        {
            for (value in bitmapsArray)
            {
                Log.d("++++++", value.toString())
                //findViewById<ImageView>(R.id.imageView).setImageBitmap(value)
            }

            Thread.sleep(2000)
        }
    }


    var startAnimation: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            animationDrawable.start()
        }
    }

    fun drawMultipleBitmapsOnImageView(b: Bitmap?): Bitmap? {
        var drawnBitmap: Bitmap? = null
        try {
            drawnBitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(drawnBitmap)
            // JUST CHANGE TO DIFFERENT Bitmaps and coordinates .
//            canvas.drawBitmap(b, 100, 100, null)
//            canvas.drawBitmap(b, 200, 300, null)
//            canvas.drawBitmap(b, 100, 200, null)
//            canvas.drawBitmap(b, 300, 350, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return drawnBitmap
    }

//    private class TickClass : TimerTask() {
//        fun run() {
//            handler.sendEmptyMessage(_index)
//            _index++
//
//        }
//    }

//    private class MyHandler : Handler() {
//        override fun handleMessage(msg: Message?) {
//
//            super.handleMessage(msg)
//            try {
//                val bmp = BitmapFactory.decodeStream(this@MainActivity.getAssets().open("drum_$_index.png"))
//                _imagView.setImageBitmap(bmp)
//                Log.v("Loaing Image: ", _index.toString() + "")
//            } catch (e: IOException) {
//                // TODO Auto-generated catch block
//                Log.v("Exception in Handler ", e.getMessage())
//            }
//        }
//    }

    fun yourMethod() {
        //do what you want
        if (bitmapsArray.size>=1) {
            bitmapsArray.removeAt(bitmapsArray.size - 1)
            //timer!!.start()
            //Thread.sleep(3000)
        }
        setImageView()
    }

    fun playerFunction (){

        if (fileNameString.size>=count) {

            if (fileNameString.size > 0) {
                try {
                    player.setDataSource(fileNameString.get(count))
                    Log.d("+++fileName mp30+++", fileNameString.get(0))
                    player.prepare()
                } catch (e: IllegalArgumentException) {
                    Log.d("+++fileName mp3+++", e.toString())
                    e.printStackTrace()
                } catch (e: java.lang.Exception) {
                    println("Exception of type : $e")
                    Log.d("+++fileName mp3+++", e.toString())
                    e.printStackTrace()
                }
//
                player.start()
            }
        }
        else
        {
            player.stop()
        }
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
                                    var imageView = ImageView(this@Main2Activity)
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
//                    findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmapsArray.get(bitmapsArray.size-1))
//                    findViewById<ImageView>(R.id.imageView).startAnimation(inFromRight)
                                                mapValueImages.remove(dataJson.key)
                                                yourMethod()
                                                bitmapHashMap.remove(imageValue.key)
                                            }
//                                        else
//                                        {
//                                            Log.d("+++file.absolute+++",file2.path+" name "+newStringImage)
//                                            val image = File(file2.absolutePath, "/resource/character/"+newStringImage)
//                                            val bmOptions = BitmapFactory.Options()
//                                            var bitmap = BitmapFactory.decodeFile(image.absolutePath, bmOptions)
//                                            imageView.setImageBitmap(bitmap)
//                                            findViewById<ConstraintLayout>(R.id.constraintLayout).addView(imageView)
//                                            Log.d("+++ No +++",imageValue.key+" No "+newStringImage)
//                                            mapValueImages.remove(dataJson.key)
//                                            yourMethod()
//                                        }
                                        }


//                                    imageView.setImageBitmap(bitmapsArray.get(bitmapsArray.size-1))
//                                    findViewById<ConstraintLayout>(R.id.constraintLayout).addView(imageView)
////                    findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmapsArray.get(bitmapsArray.size-1))
////                    findViewById<ImageView>(R.id.imageView).startAnimation(inFromRight)
//                                    mapValue.remove(dataJson.key)
//                                    yourMethod()
                                } catch (e: java.lang.Exception) {
                                    Log.e("Error", "Error: $e")
                                }
                            }
                        }.start()

                        break@loop

                    }
                }
                Log.d("+++ tagName mapValue+++", dataJson.value.toString())
                //break@loop

            }
        }
    }

    fun unzipp(_zipFile: String, _location: String) {
        try {
            val fin = FileInputStream(_zipFile)
            val zin = ZipInputStream(fin)
            var ze: ZipEntry? = null
            while (zin.nextEntry.also { ze = it } != null) {
                Log.v("Decompress", "Unzipping " + ze!!.name)
                if (ze!!.isDirectory) {
                    val f = File(_location + ze!!.name)
                    if (!f.isDirectory) {
                        f.mkdirs()
                    }
                } else {
                    val fout =
                        FileOutputStream(_location + ze!!.name)
                    var c = zin.read()
                    while (c != -1) {
                        fout.write(c)
                        c = zin.read()
                    }
                    zin.closeEntry()
                    fout.close()
                }
                var fileName = ze!!.name
                Log.d("+++fileName+++", fileName)

                fileName = fileName.substring(fileName.indexOf("/") + 1)
                val file = File(_location, fileName)


                if (fileName.contains(".png")) {

                    val myBitmap = BitmapFactory.decodeStream(zin)
                    bitmapsArray.add(myBitmap)
                    bitmapHashMap.put(fileName,myBitmap)

                    var b = drawMultipleBitmapsOnImageView(myBitmap)
                    //findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
                    //var frame : Drawable = BitmapDrawable(myBitmap)
                    //animationDrawable.addFrame(frame,250)
                    //findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
                    //FileHelper.ImageViewAnimatedChange(this,findViewById<ImageView>(R.id.imageView),myBitmap)
                }

                if (fileName.contains(".mp3") || fileName.contains(".ogg"))
                {
                    Log.d("+++fileName mp3+++", ""+SDPath1+"/STK.zip/"+fileName)
                    Log.d("+++fileName mp3+++", file.absolutePath)
                    fileNameString.add(file.absolutePath)
                    Log.d("+++ fileNameString +++", fileNameString.size.toString())
//                    val player = MediaPlayer()
//
//                    try {
//                        player.setDataSource(file.absolutePath)
//                        player.prepare()
//                    } catch (e: IllegalArgumentException) {
//                        Log.d("+++fileName mp3+++", e.toString())
//                        e.printStackTrace()
//                    } catch (e: java.lang.Exception) {
//                        println("Exception of type : $e")
//                        Log.d("+++fileName mp3+++", e.toString())
//                        e.printStackTrace()
//                    }
////
//                    player.start()
//                    val expansionFile = ZipResourceFile("myZipFile.zip")
//                    val assetFileDescriptor: AssetFileDescriptor =
//                        expansionFile.getAssetFileDescriptor("myMusic.mp3")
//                    try {
//                        mediaPlayer.setDataSource(assetFileDescriptor.fileDescriptor)
//                        mediaPlayer.prepare()
//                        mediaPlayer.start()
//                    } catch (e: IOException) {
//                        // Handle exception
//                    }
                }

                if (fileName.contains(".ssk"))
                {
                    Log.d("++sskfile+++",fileName.reader().readText())
                    val bufferedReader = BufferedReader(FileReader(file))
                    var read: String?
                    val builder = StringBuilder("")

                    while (bufferedReader.readLine().also { read = it } != null) {
                        builder.append(read)
                    }
                    Log.d("++sskfile+++", builder.toString())
                    ssk = builder.toString()
                    //Toast.makeText(this,builder.toString(),Toast.LENGTH_SHORT).show()
                    bufferedReader.close()
                }

                if (fileName.contains(".sc"))
                {
                    Log.d("++scfile+++",fileName.reader().readText())
                    val bufferedReader = BufferedReader(FileReader(file))
                    var read: String?
                    val builder = StringBuilder("")

                    while (bufferedReader.readLine().also { read = it } != null) {
                        builder.append(read)
                    }
                    Log.d("++scfile+++", builder.toString())
                    sc = builder.toString()
                    //Toast.makeText(this,builder.toString(),Toast.LENGTH_SHORT).show()
                    bufferedReader.close()
                }



                Log.d("+++bitmapsArray+++", bitmapsArray.size.toString())
            }
            zin.close()
        } catch (e: java.lang.Exception) {
            Log.e("Decompress", "unzip", e)
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


