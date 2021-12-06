package com.easyapp.hero_es.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.easyapp.hero_es.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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

            val db = Firebase.database.reference
            val dataRef = db.child("bienvenido")
            getData(dataRef)
            insertData(dataRef)

        }
    }

    private fun insertData(dataRef: DatabaseReference) {
        binding.btnSend.setOnClickListener {
            dataRef
                .setValue(binding.etData.text.toString())
                .addOnSuccessListener { mostrarToast("Enviando...") }
                .addOnFailureListener { mostrarToast("Error al enviar.") }
                .addOnCompleteListener { mostrarToast("Terminado.") }

            binding.etData.setText("")
            binding.etData.setHint("Enviar algo...")
        }
    }

    private fun getData(dataRef: DatabaseReference) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val data = snapshot.getValue(String::class.java)
                    binding.tvGetInfo.text = "$data"
                } else {
                    binding.tvGetInfo.text = "Ruta sin datos"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                mostrarToast("Error al leer datos")
            }
        }
        dataRef.addValueEventListener(listener)

    }

    fun mostrarToast(mensaje: String) {
        Toast.makeText(this@MainActivity, mensaje, Toast.LENGTH_LONG).show()
    }

    fun consumoSimple() {

        val url = "https://akabab.github.io/superhero-api/api/work/1.json"

        val queue = Volley.newRequestQueue(this)
        val solicitud =
            StringRequest(Request.Method.GET, url, { response ->
                try {
                    //val data = Gson().fromJson(response, model::class.java)
                    Log.w(TAG, "consumoSimple: $response")
                } catch (e: Exception) {
                    Log.e(TAG, "solicitudHTTPVolley: ${e.stackTrace}")
                }
                loader(false)
            }, {

            })

        queue.add(solicitud)
    }

    fun loader(mostrar: Boolean) {
        binding.progressBar.visibility = if (mostrar) View.VISIBLE else View.INVISIBLE
    }
}