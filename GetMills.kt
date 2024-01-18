package com.example.millometeradmin

import android.os.AsyncTask
import com.example.millometeradmin.adapter.MillData
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GetMills(private val listener: OnDataFetchedListener) :
    AsyncTask<Void, Void, List<MillData>>() {

    interface OnDataFetchedListener {
        fun onDataFetched(data: List<MillData>)
    }

    private val mongoDBApiUrl =
        "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-gacpq/endpoint/getmills"

    override fun doInBackground(vararg params: Void?): List<MillData> {
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
            val dataList = ArrayList<MillData>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val millId = jsonObject.getString("PiID")
                val millStatus = jsonObject.getString("status")

                dataList.add(MillData(millPiID,millStatus))
            }
            return dataList
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList()
    }

    override fun onPostExecute(result: List<MillData>) {
        super.onPostExecute(result)
        listener.onDataFetched(result) }
}