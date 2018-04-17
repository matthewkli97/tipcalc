package edu.washington.mkl.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import java.text.NumberFormat
import java.util.*
import android.R.attr.button
import android.widget.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById(R.id.button) as Button
        val spinner = findViewById(R.id.spinner) as Spinner
        val amountTotal = findViewById(R.id.totalAmountValue) as TextView
        val tipTotal = findViewById(R.id.tipAmountValue) as TextView
        val inputValue = findViewById(R.id.editTextAmount) as EditText


        inputValue.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                button.isEnabled = true;
                val re = Regex("[^0-9 ]")
                var userInput = s.toString().replace(re, "") // works

                val sb = StringBuilder(userInput)

                Log.i("Main Activity", sb.toString())

                inputValue.removeTextChangedListener(this)

                while(sb.length > 3 && sb.get(0) == '0') {
                    sb.deleteCharAt(0)
                }

                while(sb.length < 3) {
                    sb.insert(0, '0')
                }

                sb.insert(sb.length - 2, '.')

                val output = "$" + NumberFormat.getNumberInstance(Locale.US).format(sb.toString().toDouble())

                inputValue.setText(output);
                inputValue.setSelection(output.length);
                inputValue.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })





        val adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        button.isEnabled = false
        button.setOnClickListener { view ->

            val re = Regex("[^0-9.]")
            var initial = inputValue.text.toString().replace(re, "").toDouble() // works

            var sPercent = spinner.getSelectedItem().toString()
            sPercent = sPercent.substring(0, sPercent.length - 1)
            var nPercent:Double = sPercent.toDouble() / 100

            val tipAmount = ("%.2f".format(initial * nPercent)).toString().toDouble()
            val total = tipAmount + initial

            amountTotal.text =  "$" + NumberFormat.getNumberInstance(Locale.US).format(total)
            tipTotal.text =  "$" + NumberFormat.getNumberInstance(Locale.US).format(tipAmount)
        }

    }
}
