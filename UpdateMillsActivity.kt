package com.example.millometeradmin.adapter
import android.widget.EditText

import okhttp3.*
import java.io.IOException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.millometeradmin.MainActivity
import com.example.millometeradmin.R

class UpdateMillsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updatemilldata) // Assuming you have add_mills.xml as your layout file

        // Call addMill function when the "Add" button is clicked
        val addButton = findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            AddMillsActivity()
        }
    }
private fun updateMillData() {
    val PiID = intent.getStringExtra("PiID")
    val millNameInput = findViewById<EditText>(R.id.update_mill_name)
    val millName = millNameInput.text.toString()

    val statusSpinner = findViewById<EditText>(R.id.update_status)
    val status = statusSpinner.text.toString()

    val streamInput = findViewById<EditText>(R.id.update_stream)
    val stream = streamInput.text.toString()

    val steamPressureInput = findViewById<EditText>(R.id.update_steam_pressure)
    val steamPressure = steamPressureInput.text.toString()

    val steamFlowInput = findViewById<EditText>(R.id.update_steam_flow)
    val steamFlow = steamFlowInput.text.toString()

    val waterLevelInput = findViewById<EditText>(R.id.update_water_level)
    val waterLevel = waterLevelInput.text.toString()

    val powerFrequencyInput = findViewById<EditText>(R.id.update_power_frequency)
    val powerFrequency = powerFrequencyInput.text.toString()

    val modeInput = findViewById<EditText>(R.id.update_mode)
    val mode = modeInput.text.toString()

    val proxyPortInput = findViewById<EditText>(R.id.update_proxy_port)
    val proxyPort = proxyPortInput.text.toString()

    val tunnelPortInput = findViewById<EditText>(R.id.update_tunnel_port)
    val tunnelPort = tunnelPortInput.text.toString()

    val url = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-gacpq/endpoint/update_mills" +
            "?PiID=$PiID" +
            "&updateMillName=$millName" +
            "&updateMillStatus=$status" +
            "&updateMillStream=$stream" +
            "&updateMillSteamPressure=$steamPressure" +
            "&updateMillSteamFlow=$steamFlow" +
            "&updateMillWaterLevel=$waterLevel" +
            "&updateMillPowerFrequency=$powerFrequency" +
            "&updateMode=$mode" +
            "&updateProxyPort=$proxyPort" +
            "&updateTunnelPort=$tunnelPort"

    val request = Request.Builder()
        .url(url)
        .post(FormBody.Builder().build())
        .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val responseData = response.body?.string()
            println("Update successfully")
            finish()

            val intent = Intent(this@UpdateMillsActivity, MainActivity::class.java)
            startActivity(intent)
        }

        override fun onFailure(call: Call, e: IOException) {
            println("Update failed")
        }
        })
    }
}