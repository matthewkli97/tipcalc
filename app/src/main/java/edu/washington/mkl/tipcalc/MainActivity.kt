package edu.washington.mkl.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputValue = findViewById(R.id.editTextAmount) as EditText
        inputValue.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                val re = Regex("[^0-9 ]")
                var userInput = s.toString().replace(re, "") // works

                val sb = StringBuilder(userInput)

                inputValue.removeTextChangedListener(this)

                Log.i("MainActivity", sb.toString())
                while(sb.length > 3 && sb.get(0) == '0') {
                    sb.deleteCharAt(0)
                }

                while(sb.length < 3) {
                    sb.insert(0, '0')
                }



                sb.insert(sb.length - 2, '.')
                val output = "$" + NumberFormat.getNumberInstance(Locale.US).format(sb.toString().toFloat());

                inputValue.setTextKeepState(output);

                inputValue.setSelection(output.length);
                inputValue.addTextChangedListener(this)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


    }
}
