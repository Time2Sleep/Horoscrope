package com.example.horoscrope

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupSpinner()
        setupSign()
    }

    fun setupSign() {
        val selectedSign = intent.getStringExtra("sign");
        println(selectedSign)
        if (selectedSign != null) {
            var signName: TextView = findViewById(R.id.signName)
            val signs = resources.getStringArray(R.array.signs)
            val id = selectedSign.toInt()
            signName.text = signs[id]
        }

    }


    fun setupSpinner() {
        val spinner: Spinner = findViewById(R.id.gender)
        val arrayAdapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayOf("M", "Ж"))
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("not selected")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                println("selected")
            }

        }

    }

    fun toastMe(view: View) {
        val myToast = Toast.makeText(this, "Hello, Toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun showDatePicker(view: View) {
        view as Button
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                view.text = "$day.$month.$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun showSignsScreen(view: View) {
        val intent = Intent(this, SignsSelectorActivity::class.java)
        startActivity(intent)
    }


}