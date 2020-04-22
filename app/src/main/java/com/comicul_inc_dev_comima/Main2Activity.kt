package com.comicul_inc_dev_comima

//import org.apache.tools.ant.taskdefs.Zip
//import org.jszip.maven.JSZipMojo

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.AnimationDrawable
import android.os.*
import android.util.Log
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.cunoraz.loadingimages.LoadingImagesView
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


class Main2Activity : AppCompatActivity() {

    var bitmapsArray : ArrayList<Bitmap> = arrayListOf<Bitmap>()
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

    @SuppressLint("WrongThread")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bundle: Bundle? = intent.extras
        val id = bundle?.get("name")
        val language = bundle?.get("location")
        Log.d("++++++",language.toString())
        Log.d("++++++",Environment.getExternalStorageDirectory().absolutePath)
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


        val lFile = File(unzipPath+ id.toString())
        lFile.setExecutable(true)
        Log.d("+++size+++",lFile.extension)
        readFileUsingGetResource(lFile.name)

//        val zipFile = ZipFile(lFile.name)
//
//        val entries: Enumeration<out ZipEntry> = zipFile.entries()
//
//        while (entries.hasMoreElements()) {
//            val entry = entries.nextElement()
//            val stream: InputStream = zipFile.getInputStream(entry)
//            Log.d("+++sizeinput+++",stream.toString())
//        }

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



//        var zip = ZipFile(file)
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
//        ZipFile(unzipPath+ "STK.data").use { zip ->
//            zip.entries().asSequence().forEach { entry ->
//                zip.getInputStream(entry).use { input ->
//                    File(entry.name).outputStream().use { output ->
//                        input.copyTo(output)
//                    }
//                }
//            }
//        }

        //Check for permission
        Utility().checkPermission(this)
        unZipView(unzipPath+ id.toString())



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
            while (zis.nextEntry != null) {
                ze = zis.nextEntry
                var fileName = ze.name
                Log.d("+++fileName+++", fileName)

                fileName = fileName.substring(fileName.indexOf("/") + 1)
                val file = File(destinationFolder, fileName)


                if (fileName.contains("02.png")) {

                    val myBitmap = BitmapFactory.decodeStream(zis)
                    bitmapsArray.add(myBitmap)

                    var b = drawMultipleBitmapsOnImageView(myBitmap)
                    findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
                    //var frame : Drawable = BitmapDrawable(myBitmap)
                    //animationDrawable.addFrame(frame,250)
                    //findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)
                    //FileHelper.ImageViewAnimatedChange(this,findViewById<ImageView>(R.id.imageView),myBitmap)
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
                findViewById<ImageView>(R.id.imageView).setImageBitmap(value)
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


}


