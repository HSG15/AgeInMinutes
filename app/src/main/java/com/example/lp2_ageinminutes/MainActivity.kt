package com.example.lp2_ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tv_selectedDate : TextView? = null
    private var tv_inMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_selectedDate = findViewById(R.id.tv_selectedDate)
        tv_inMinutes = findViewById(R.id.tv_inMinutes)

        val btn_selectDate = findViewById<Button>(R.id.btn_selectDate)
        btn_selectDate.setOnClickListener {
            datePicker()
        }

    }

    private fun datePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val date = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_,year,month,date ->
                Toast.makeText(this,"Year was $year , Month was ${month + 1} , Date was $date"
                    ,Toast.LENGTH_LONG).show()

                val selectedDate = "$date/${month + 1}/$year"
                tv_selectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate.time / 60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                val ageInMinutes = "$differenceInMinutes"
                tv_inMinutes?.text = ageInMinutes



            },year, month,date)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}