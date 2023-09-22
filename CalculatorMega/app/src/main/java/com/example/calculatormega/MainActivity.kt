package com.example.calculatormega

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btn_0 : Button
    private lateinit var btn_1 : Button
    private lateinit var btn_2 : Button
    private lateinit var btn_3 : Button
    private lateinit var btn_4 : Button
    private lateinit var btn_5 : Button
    private lateinit var btn_6 : Button
    private lateinit var btn_7 : Button
    private lateinit var btn_8 : Button
    private lateinit var btn_9 : Button
    private lateinit var btn_dot : Button
    private lateinit var btn_mult : Button
    private lateinit var btn_minus : Button
    private lateinit var btn_plus : Button
    private lateinit var btn_div : Button
    private lateinit var btn_clr : Button
    private lateinit var btn_equal : Button
    private lateinit var btn_back : Button
    private lateinit var input1 : EditText
    private lateinit var input2 : EditText
    private lateinit var result : TextView
    private lateinit var symbol : TextView
    private lateinit var editvalue: String

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0 = findViewById(R.id.btn_0)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)
        btn_4 = findViewById(R.id.btn_4)
        btn_5 = findViewById(R.id.btn_5)
        btn_6 = findViewById(R.id.btn_6)
        btn_7 = findViewById(R.id.btn_7)
        btn_8 = findViewById(R.id.btn_8)
        btn_9 = findViewById(R.id.btn_9)
        btn_dot = findViewById(R.id.btn_dot)
        btn_minus = findViewById(R.id.btn_minus)
        btn_plus = findViewById(R.id.btn_plus)
        btn_mult = findViewById(R.id.btn_mult)
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        btn_div = findViewById(R.id.btn_div)
        btn_clr = findViewById(R.id.btn_clr)
        btn_back = findViewById(R.id.btn_back)
        result = findViewById(R.id.result)
        symbol = findViewById(R.id.symbol)
        btn_equal = findViewById(R.id.btn_equal)

        fun back(input: EditText){

            btn_back.setOnClickListener {
                var str: String = input.getText().toString().trim()

                if (str.length != 0) {
                    str = str.substring(0, str.length - 1)
                    input.setText(str)
                }
            }
        }

        input1.setOnClickListener {
            inputText(input1)
            back(input1)
        }

        input2.setOnClickListener{
            inputText(input2)
            back(input2)
        }


        btn_clr.setOnClickListener {
            input1.text.clear()
            input2.text.clear()
            result.text = ""
            symbol.text = ""
        }

        fun setTFSymbol(str: String) {
            symbol = findViewById(R.id.symbol)
            if (input2.getText().toString().trim().isNotEmpty()) {
                input1.setText(calcRes(symbol.text.toString()).toString())
                input2.setText("")
            }
            symbol.text = str
            input2.requestFocus()
        }

        btn_plus.setOnClickListener{
            setTFSymbol("+")
            if (result.text.toString().isNotEmpty()){
                input1.setText(result.text)
                result.text=""
            }
            inputText(input2)
            back(input2)
        }
        btn_minus.setOnClickListener{
            setTFSymbol("-")
            if (result.text.toString().isNotEmpty()){
                input1.setText(result.text)
                result.text=""
            }
            inputText(input2)
            back(input2)
        }
        btn_mult.setOnClickListener{
            setTFSymbol("*")
            if (result.text.toString().isNotEmpty()){
                input1.setText(result.text)
                result.text=""
            }
            inputText(input2)
            back(input2)
        }
        btn_div.setOnClickListener{
            setTFSymbol("/")
            if (result.text.toString().isNotEmpty()){
                input1.setText(result.text)
                result.text=""
            }
            inputText(input2)
            back(input2)
        }

        btn_equal.setOnClickListener{
            if (input1.getText().toString().trim().isNotEmpty() && input2.getText().toString().trim().isNotEmpty())
                result.text = calcRes(symbol.text.toString()).toString()
            else if (input1.getText().toString().trim().isNotEmpty())
                result.text = input1.getText().toString()
            else if (input2.getText().toString().trim().isNotEmpty())
                result.text = input2.getText().toString()
            input1.setText("")
            input2.setText("")
            symbol.text = ""
            inputText(input1)
            back(input1)
        }
    }

    fun inputText(input: EditText){
        val cl = View.OnClickListener {
            val str = "${input.text.toString()}${(it as Button).text}"
            input.setText(str)
        }

        btn_0.setOnClickListener(cl)
        btn_1.setOnClickListener(cl)
        btn_2.setOnClickListener(cl)
        btn_3.setOnClickListener(cl)
        btn_4.setOnClickListener(cl)
        btn_5.setOnClickListener(cl)
        btn_6.setOnClickListener(cl)
        btn_7.setOnClickListener(cl)
        btn_8.setOnClickListener(cl)
        btn_9.setOnClickListener(cl)
        btn_dot.setOnClickListener(cl)
    }

    fun calcRes(operation: String): Any{
        val oper1 = input1.text.toString()
        val oper2 = input2.text.toString()
        return when(operation){
            "+" -> {
                if (oper1.contains(".", ignoreCase = true) || oper2.contains(".", ignoreCase = true))
                    oper1.toDouble() + oper2.toDouble()
                 else
                    oper1.toInt() + oper2.toInt()

            }
            "-" -> {
                if (oper1.contains(".", ignoreCase = true) || oper2.contains(".", ignoreCase = true))
                    oper1.toDouble() - oper2.toDouble()
                else
                    oper1.toInt() - oper2.toInt()

            }
            "*" -> {
                if (oper1.contains(".", ignoreCase = true) || oper2.contains(".", ignoreCase = true))
                    oper1.toDouble() * oper2.toDouble()
                else
                    oper1.toInt() * oper2.toInt()

            }
            "/" -> {
                if (oper1.contains(".", ignoreCase = true) || oper2.contains(".", ignoreCase = true))
                    if (oper2.toDouble() != 0.0){
                        oper1.toDouble() / oper2.toDouble()
                    } else {
                        throw ArithmeticException("Division by 0")
                    }
                else
                    if (oper2.toInt() != 0){
                        oper1.toInt() / oper2.toInt()
                    } else {
                    throw ArithmeticException("Division by 0")
                }
            } else -> {0}
        }
    }


}