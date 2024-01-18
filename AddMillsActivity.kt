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

class AddMillsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addmill) // Assuming you have add_mills.xml as your layout file

        // Call addMill function when the "Add" button is clicked
        val addButton = findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            addMill()
           }
        }
    private fun addMill() {

        val PiIDInput = findViewById<EditText>(R.id.piid)
        val PiID = PiIDInput.text.toString()

        val millNameInput = findViewById<EditText>(R.id.factory_name)
        val millName = millNameInput.text.toString()

        val statusInput = findViewById<EditText>(R.id.status)
        val status = statusInput.text.toString()

        val streamInput = findViewById<EditText>(R.id.stream)
        val stream = streamInput.text.toString()

        val steamPressureInput = findViewById<EditText>(R.id.steam_pressure)
        val steamPressure = steamPressureInput.text.toString()

        val steamFlowInput = findViewById<EditText>(R.id.steam_flow)
        val steamFlow = steamFlowInput.text.toString()

        val waterLevelInput = findViewById<EditText>(R.id.water_level)
        val waterLevel = waterLevelInput.text.toString()

        val powerFrequencyInput = findViewById<EditText>(R.id.power_frequency)
        val powerFrequency = powerFrequencyInput.text.toString()

        val modeInput = findViewById<EditText>(R.id.mode)
        val mode = modeInput.text.toString()

        val proxyPortInput = findViewById<EditText>(R.id.proxy_port)
        val proxyPort = proxyPortInput.text.toString()

        val tunnelPortInput = findViewById<EditText>(R.id.tunnel_port)
        val tunnelPort = tunnelPortInput.text.toString()

        val url =
            "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-gacpq/endpoint/add_mills" +
                    "?PiID=$PiID" +
                    "&addMillName=$millName" +
                    "&addMillStatus=$status" +
                    "&addMillStream=$stream" +
                    "&addMillSteamPressure=$steamPressure" +
                    "&addMillSteamFlow=$steamFlow" +
                    "&addMillWaterLevel=$waterLevel" +
                    "&addMillPowerFrequency=$powerFrequency" +
                    "&addMode=$mode" +
                    "&addProxyPort=$proxyPort" +
                    "&addTunnelPort=$tunnelPort"

        val formBody = FormBody.Builder()
            .add("PiID", PiID)
            .add("addMillName", millName)
            .add("addMillStatus", status)
            .add("addMillStream", stream)
            .add("addMillSteamPressure", steamPressure)
            .add("addMillSteamFlow", steamFlow)
            .add("addMillWaterLevel", waterLevel)
            .add("addMillPowerFrequency", powerFrequency)
            .add("addMode", mode)
            .add("addProxyPort", proxyPort)
            .add("addTunnelPort", tunnelPort)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                println("Update successfully")
                finish()

                val intent = Intent(this@AddMillsActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Update failed")
            }
        })
    }
}