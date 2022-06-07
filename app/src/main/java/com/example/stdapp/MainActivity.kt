package com.example.stdapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stdapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var data: String

    private lateinit var nc: String
    private lateinit var nombre: String
    private lateinit var carrera: String
    private lateinit var semestre: String
    private lateinit var grupoAB: String
    private var NOMBRE_ARCHIVO = "estudiante.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        file(NOMBRE_ARCHIVO)
        with(binding) {
            if (!requestPermission(this@MainActivity)) {
                readFile().let { tvResultados.text = it }
            }
            btnRegistrar.setOnClickListener {
                if (!requestPermission(this@MainActivity)) {
                    obtenerDatos()
                    if (!validarDatos()) enviarDatos() else message("Ingresa todos los datos")
                }
            }
        }
    }

    private fun obtenerDatos() = with(binding) {
        nc = edtNC.text.toString()
        nombre = edtNombreC.text.toString()
        carrera = cbxCarrera.selectedItem.toString()
        semestre = cbxSemestre.selectedItem.toString()
        grupoAB = cbxGrupo.selectedItem.toString()
    }

    private fun enviarDatos() {
        data = "$nc, $nombre, $carrera, $semestre, $grupoAB\n"
        binding.tvResultados.append(guardarDatos(data))
    }

    private fun validarDatos(): Boolean {
        return nc.isEmpty() || nombre.isEmpty() ||
                carrera.isEmpty() || semestre.isEmpty() ||
                grupoAB.isEmpty()
    }

    private fun message(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

}