package com.example.millometeradmin

import android.os.AsyncTask
import com.example.millometeradmin.adapter.UserData
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class FetchMillData(private val listener: OnDataFetchedListener) :
    AsyncTask<Void, Void, List<UserData>>() {

    interface OnDataFetchedListener {
        abstract val recyclerView: Any

        fun onDataFetched(data: List<UserData>)
    }

    private val mongoDBApiUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-gacpq/endpoint/getusers"

    override fun doInBackground(vararg params: Void?): List<UserData>? {
        try {
            val url = URL(mongoDBApiUrl)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStreamReader = InputStreamReader(urlConnection.inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }

            val jsonArray = JSONArray(stringBuilder.toString())
            val dataList = mutableListOf<UserData>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val userData = UserData(jsonObject)
                dataList.add(userData)
            }

            return dataList
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the error, log it, or return null/empty list depending on your app's requirements.
            return null
        }
    }


    override fun onPostExecute(result: List<UserData>?) {
        super.onPostExecute(result)
        if (result != null) {
            listener.onDataFetched(result)
        }
    }
}
