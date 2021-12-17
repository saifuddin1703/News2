package com.example.news.datasource

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.news.models.News
import com.example.news.utils.API_KEY
import com.example.news.utils.BASE_URL
import com.example.news.utils.MySingleton
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap
import javax.inject.Inject

class RemoteDataSource @Inject constructor(){
     fun getNewsDataByCategory(
         context:Context,
         category:String?,
         onsuccess:(newslist:ArrayList<News>)->Unit,
         onerror:(error_message:String)->Unit
     ){
        val newslist = arrayListOf<News>()
        val url = category?.let {
            "$BASE_URL$category&apiKey=$API_KEY"
        }?: BASE_URL
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(Method.GET, url, null, Response.Listener { response ->
            try {
                val news = response.getJSONArray("articles")
                for (i in 0 until news.length()) {
                    val `object` = news.getJSONObject(i)
                    val title = `object`.getString("title")
                    val link = `object`.getString("url")
                    val image = `object`.getString("urlToImage")
                    val mynews = News(i,title, link, image)
                    newslist.add(mynews)
                }
                onsuccess(newslist)
            } catch (e: JSONException) {
                e.printStackTrace()
                Log.d("tag","error while fetching news ")
            }
        }, Response.ErrorListener { error -> // TODO: Handle error
            Log.d("tag","error while fetching news ")
            onerror(error.message!!)

        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(context)!!
            .addToRequestQueue<JSONObject>(jsonObjectRequest)
    }

    fun getAllNewsData(
        context: Context,
        onSuccess: (newslist: ArrayList<News>) -> Unit,
        onError: (error_message: String) -> Unit
    ){
        val newslist = arrayListOf<News>()

        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(Method.GET, "$BASE_URL&apiKey=$API_KEY", null, Response.Listener { response ->
            try {
                val news = response.getJSONArray("articles")
                for (i in 0 until news.length()) {
                    val `object` = news.getJSONObject(i)
                    val title = `object`.getString("title")
                    val link = `object`.getString("url")
                    val image = `object`.getString("urlToImage")
                    val mynews = News(i,title, link, image)
                    newslist.add(mynews)
                }
                onSuccess(newslist)
            } catch (e: JSONException) {
                e.printStackTrace()
                Log.d("tag","error while fetching news ")
            }
        }, Response.ErrorListener { error -> // TODO: Handle error
            Log.d("tag","error while fetching news ")
            onError(error.message.toString())

        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(context)!!
            .addToRequestQueue<JSONObject>(jsonObjectRequest)
    }
}