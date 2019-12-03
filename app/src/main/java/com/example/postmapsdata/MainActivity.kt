package com.example.postmapsdata

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.IOException

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*



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

            //Formを作成
            val formBuilder = FormBody.Builder()

            //formに要素を追加

            //リクエストの内容にformを追加
            val form = formBuilder.build()

            //リクエストを生成
            val request = Request.Builder().url(url).post(form).build()

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

                var postList = mutableListOf<MapsList>()
                val listType = object : TypeToken<List<MapsDataClassList>>() {}.type
                val postData = Gson().fromJson<List<MapsDataClassList>>(result, listType)

                for (i in postData) {
                    postList.add(
                        MapsList(
                            i.imgPath,
                            i.imgLat,
                            i.imgLng
                        )
                    )
                }
                messageView.text = postList[1].imgpath
            } catch (e: Exception) {

            }
        }


    }
}