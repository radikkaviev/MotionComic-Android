package com.pickfilename

import android.graphics.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import java.util.zip.ZipFile


class Main2Activity : AppCompatActivity() {




    private lateinit var webView: WebView
    lateinit var bmp : Bitmap
    lateinit var canvas: Canvas
    lateinit var paint: Paint
    lateinit var path: Path

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bundle: Bundle? = intent.extras
        val id = bundle?.get("name")
        val language = bundle?.get("location")
        Log.d("++++++",language.toString())
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
//        webView.loadUrl("file:///android_asset/index.html")

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
        //Glide.with(this).asGif().load(MainActivity.abc).into(findViewById(R.id.imageView));
        //Glide.with(this).asBitmap().load(R.drawable.ic_launcher_background).into(R.id.imageView)
        //Glide.with(this).load("file:///android_asset/STK.data").into(findViewById(R.id.imageView))

        //webView.loadData(this.contentResolver.openInputStream(MainActivity.abc)?.reader()?.readText(), ".data", "utf-8")

        //findViewById<ImageView>(R.id.imageView).setImageBitmap(BitmapFactory.decodeFile(language.toString()))


        var file = File(language.toString())
        Log.d("++zz++", file.isFile.toString())
        Log.d("++zz++", readFileUsingGetResource(id.toString()).toString())


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
            = this::class.java.getResource(fileName)?.readText(Charsets.UTF_8)

//    external class JSZip {
//        fun file(name: String): Promise<ZipObject>
//        fun loadAsĀnc(data: ArraĀBuffer): Promise<JSZip>
//    }
//
//    external class ZipObject {
//        fun asĀnc(tĀpe: String): Promise<Any?>
//    }
}
