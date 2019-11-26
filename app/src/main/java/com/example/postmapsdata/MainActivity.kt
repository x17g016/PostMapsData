package com.example.postmapsdata


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import okhttp3.RequestBody
import okhttp3.MediaType

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.log


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById<Button>(R.id.button_post)
        button.setOnClickListener(this)
    }

    //ボタンタップ
    override fun onClick(v: View?) {
        Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show()
        MapPostGet().execute()
    }

    private inner class MapPostGet() : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String): String {
            val client = OkHttpClient()

            //アクセスするURL
            val url = "https://kinako.cf/encount/MapsDataGet.php"

            val MIMEType = MediaType.parse("application/json; charset=utf-8")
            val requestBody = RequestBody.create(MIMEType, "{}")

            //リクエストを生成
            val request = Request.Builder().url(url).post(requestBody).build()

            try {
                //受信用
                val response = client.newCall(request).execute()
                return response.body()!!.string()
            } catch (e: IOException) {
                e.printStackTrace()
                return "Error"
            }
        }


        //結果表示
        override fun onPostExecute(result: String) {

            try {
                val messageView: TextView = findViewById(R.id.textview)

                var dataList = mutableListOf<MapsDataClassList>()
                val listType = object : TypeToken<List<MapsDataClassList>>() {}.type
                val postData = Gson().fromJson<List<MapsDataClassList>>(result, listType)//error
                messageView.text = "aaaa"
                for (i in postData) {
                    dataList.add(MapsDataClassList(i.mapsLat, i.mapsLng, i.imagePath))
                    Log.d("debug",i.mapsLat.toString())
                }


            } catch (e: Exception) {

            }
        }


    }
}