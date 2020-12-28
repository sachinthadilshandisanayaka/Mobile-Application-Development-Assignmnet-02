package com.dissanayake.mobilelectureassigment_02

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val MyPREFERENCE = "My preference"
    val KEY_VALUE = "KEY_VALUE"
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(MyPREFERENCE, Context.MODE_PRIVATE)
        val getString = sharedPreferences?.getString(KEY_VALUE, "Default Value")
        findViewById<TextView>(R.id.textView).text = getString

        findViewById<Button>(R.id.button4).setOnClickListener {

            val textValue = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            val editor = sharedPreferences!!.edit()
            editor.putString(KEY_VALUE, textValue)
            editor.commit()

            Snackbar.make(it, textValue, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}














