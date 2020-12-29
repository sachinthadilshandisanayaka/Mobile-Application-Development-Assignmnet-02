package com.dissanayake.mobilelectureassigment_02

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private val FILE_PATH = "resourses"
    private val FILE_NAME = "text.txt"
    var externalFile: File? = null
    var sharedPreferences: SharedPreferences? = null

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
            externalFile = File(getExternalFilesDir(FILE_PATH), FILE_NAME)
            val editText = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            try{
                fileOutputStream = FileOutputStream(externalFile)
                try {
                    fileOutputStream!!.write(editText.toByteArray())
                    fileOutputStream!!.close()
                    showSnackBar("Date", it)
                } catch (e: Exception) {
                    Log.i("Error 01:", e.toString())
                }
            } catch (e: FileNotFoundException) {
                Log.i("Error 02:", e.toString())
            }
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            externalFile = File(getExternalFilesDir(FILE_PATH), FILE_NAME)
            var fileInputStream = FileInputStream(externalFile)
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = java.lang.StringBuilder()
            var text: String? = null
            while ({text = bufferedReader.readLine()}!= null) {
                stringBuilder.append(text)
                    }
            fileInputStream.close()
            if(text != null) {
                showToast("Done")
                findViewById<TextView>(R.id.textView).text = text
            } else {
                showToast("External Storage has no data")
            }
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














