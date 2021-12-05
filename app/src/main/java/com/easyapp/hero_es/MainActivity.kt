package com.easyapp.hero_es

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.easyapp.hero_es.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            verificarConsumo.setOnClickListener {
                consumoSimple()
                loader(true)
            }
        }
    }

    fun consumoSimple() {

        val url = "https://akabab.github.io/superhero-api/api/work/1.json"

        val queue = Volley.newRequestQueue(this)
        val solicitud =
            StringRequest(Request.Method.GET, url, { response ->
                try {
                    //val data = Gson().fromJson(response, model::class.java)
                    Log.w(TAG, "consumoSimple: $response" )
                } catch (e: Exception) {
                    Log.e(TAG, "solicitudHTTPVolley: ${e.stackTrace}")
                }
                loader(false)
            }, {

            })

        queue.add(solicitud)
    }

    fun loader(mostrar:Boolean){
        binding.progressBar.visibility = if (mostrar)View.VISIBLE else View.INVISIBLE
    }
}