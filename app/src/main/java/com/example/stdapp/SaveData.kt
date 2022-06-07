package com.example.stdapp
import android.os.Environment
import java.io.*

private lateinit var NOMBRE_ARCHIVO: String
private lateinit var archivo: File
private lateinit var ruta: String
private lateinit var dataSet: String
private var directory =
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

fun file(name: String){
    NOMBRE_ARCHIVO = name
    ruta = "$directory/$NOMBRE_ARCHIVO"
    archivo = File(ruta)
}

fun readFile(): String {
    dataSet = ""
    if (archivo.exists()) {
        val fileR = FileReader(archivo)
        val br = BufferedReader(fileR)
        var cadena: String? = null
        while ({ cadena = br.readLine(); cadena }() != null) {
            dataSet += "$cadena\n"
        }
        br.close()
        fileR.close()
        return dataSet;
    }
    return dataSet;
}

fun guardarDatos(data: String): String {
    if (!archivo.exists()) {
        archivo.createNewFile()
    }
    val fileWrite = FileWriter(archivo, true)
    val write = BufferedWriter(fileWrite)
    write.write(data)
    write.close()
    fileWrite.close()
    return data
}