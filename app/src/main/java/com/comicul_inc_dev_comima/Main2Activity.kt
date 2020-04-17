package com.comicul_inc_dev_comima

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.util.*
import java.util.zip.ZipInputStream


class Main2Activity : AppCompatActivity() {

    private lateinit var webView: WebView
    lateinit var bmp : Bitmap
    lateinit var canvas: Canvas
    lateinit var paint: Paint
    lateinit var path: Path
    private val SDPath = Environment.getExternalStorageDirectory().absolutePath
    private val SDPath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    private val zipPath = "$SDPath/Downloads/"
    private val unzipPath = "$SDPath1/"
    private val dataPath = "$SDPath/AndroidCodility/zipunzipFile/data/"

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


        val lFile = File(unzipPath+ "STK.data")
        lFile.setExecutable(true)
        Log.d("+++size+++",lFile.canExecute().toString())
        readFileUsingGetResource(lFile.name)
        //webView.loadUrl("file:///" + lFile.absolutePath)

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
        //Glide.with(this).load("file:///android_asset/STK.data").into(findViewById(R.id.imageView))

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
        unZipView(unzipPath+ "STK.data")



    }

    fun zipView(view: View) {
        if (FileHelper.zip(dataPath, zipPath, "dummy.zip", true)) {
            Toast.makeText(applicationContext, "Zip successfully.", Toast.LENGTH_LONG).show()
        }
    }

    fun unZipView(toString: String) {

        var zipInputStream : ZipInputStream = ZipInputStream(BufferedInputStream(FileInputStream(toString)))
        Log.d("++zipInputStream++",zipInputStream.toString())
        zipInputStream.getNextEntry();

        var sc = Scanner(zipInputStream);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
            Log.d("++++", sc.nextLine())
        }

        if (FileHelper.unzip( toString, unzipPath)) {
            Toast.makeText(applicationContext, "Unzip successfully.", Toast.LENGTH_LONG).show()
        }
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
}
