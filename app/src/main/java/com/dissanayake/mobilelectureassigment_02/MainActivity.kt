package com.dissanayake.mobilelectureassigment_02

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val MyPREFERENCE = "My preference"
    val KEY_VALUE = "KEY_VALUE"

    var fileOutputStream: FileOutputStream? = null
//    private val FILE_PATH = "C:\\Users\\sachi dissanayake\\Desktop\\New folder"
    private val FILE_NAME = "test.txt"
    var externalFile: File? = null
    var sharedPreferences: SharedPreferences? = null

    private val isExternalStorageReadOnly: Boolean get() {
        val extStorageStats = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED_READ_ONLY == extStorageStats
    }
    private val isExternalStorageAvailable: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == extStorageState
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(MyPREFERENCE, Context.MODE_PRIVATE)

        findViewById<Button>(R.id.button4).setOnClickListener {

            val textValue = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            val editor = sharedPreferences!!.edit()
            editor.putString(KEY_VALUE, textValue)
            editor.commit()
            showSnackBar(textValue, it)
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            val getString = sharedPreferences?.getString(KEY_VALUE, "Default Value")
            findViewById<TextView>(R.id.textView).text = getString
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            val editText = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            Log.i("Message to Editor 01: ", editText)
            try{
                val fileOutputStream: FileOutputStream
                try {
                    fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
                    fileOutputStream.write(editText.toByteArray())
                    fileOutputStream.close()
                    showSnackBar("Data", it)
                } catch (e: Exception) {
                    Log.i("Error 01:", e.toString())
                }
            } catch (e: FileNotFoundException) {
                Log.i("Error 02:", e.toString())
            }
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            try {
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(FILE_NAME)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text}() != null) {
                    Log.i("Developer message : ", text.toString())
                    stringBuilder.append(text)
                }
                fileInputStream.close()
                showToast("Done")
                findViewById<TextView>(R.id.textView).text = stringBuilder.toString()
            } catch (e:Exception) {
                Log.i("Error 03: ", e.toString())
            }
        }
        if (!isExternalStorageAvailable || isExternalStorageReadOnly) {
            findViewById<Button>(R.id.button5).isEnabled = false
        }
    }
private fun showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT ).show()
    }
private fun showSnackBar(text: String?, view: View) {
    Snackbar.make(view, "Saved $text", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
}
}














