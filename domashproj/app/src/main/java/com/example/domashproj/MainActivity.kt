package com.example.domashproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.ArithmeticException
import java.lang.IllegalArgumentException




class MainActivity : AppCompatActivity() {

    lateinit var clr_btn: TextView
    lateinit var div_btn: TextView
    lateinit var btn_7: TextView
    lateinit var btn_8: TextView
    lateinit var btn_9: TextView
    lateinit var mult_btn: TextView
    lateinit var btn_4: TextView
    lateinit var btn_5: TextView
    lateinit var btn_6: TextView
    lateinit var plus_btn: TextView
    lateinit var btn_1: TextView
    lateinit var btn_2: TextView
    lateinit var btn_3: TextView
    lateinit var min_btn: TextView
    lateinit var btn_dot: TextView
    lateinit var btn_0: TextView
    lateinit var btn_back: TextView
    lateinit var equal_btn: TextView
    lateinit var math_oper: TextView
    lateinit var result_text: TextView
    lateinit var symbol_text: TextView
    lateinit var prev_oper: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0 = findViewById(R.id.btn_0)
        btn_0.setOnClickListener {
            setTextFields("0")
        }

        btn_1 = findViewById(R.id.btn_1)
        btn_1.setOnClickListener {
            setTextFields("1")
        }

        btn_2 = findViewById(R.id.btn_2)
        btn_2.setOnClickListener {
            setTextFields("2")
        }

        btn_3 = findViewById(R.id.btn_3)
        btn_3.setOnClickListener {
            setTextFields("3")
        }

        btn_4 = findViewById(R.id.btn_4)
        btn_4.setOnClickListener {
            setTextFields("4")
        }

        btn_5 = findViewById(R.id.btn_5)
        btn_5.setOnClickListener {
            setTextFields("5")
        }

        btn_6 = findViewById(R.id.btn_6)
        btn_6.setOnClickListener {
            setTextFields("6")
        }

        btn_7 = findViewById(R.id.btn_7)
        btn_7.setOnClickListener {
            setTextFields("7")
        }

        btn_8 = findViewById(R.id.btn_8)
        btn_8.setOnClickListener {
            setTextFields("8")
        }

        btn_9 = findViewById(R.id.btn_9)
        btn_9.setOnClickListener {
            setTextFields("9")
        }

        min_btn = findViewById(R.id.min_btn)
        min_btn.setOnClickListener {
            minus()
        }

        plus_btn = findViewById(R.id.plus_btn)
        plus_btn.setOnClickListener {
            plus()
        }

        mult_btn = findViewById(R.id.mult_btn)
        mult_btn.setOnClickListener {
            mult()
        }

        div_btn = findViewById(R.id.div_btn)
        div_btn.setOnClickListener {
            division()
        }

        btn_dot = findViewById(R.id.btn_dot)
        btn_dot.setOnClickListener {
            setTextFields(".")
        }

        clr_btn = findViewById(R.id.clr_btn)
        result_text = findViewById(R.id.result_text)
        clr_btn.setOnClickListener {
            math_oper.text = ""
            result_text.text = ""
            prev_oper = ""
            symbol_text.text = ""
        }

        btn_back = findViewById(R.id.btn_back)
        btn_back.setOnClickListener {
            val str = math_oper.text.toString()
            if (str.isNotEmpty()) {
                math_oper.text = str.substring(0, str.length - 1)
            }

            //result_text.text = ""
        }

        symbol_text = findViewById(R.id.symbol_text)
        prev_oper = ""

        equal_btn = findViewById(R.id.equal_btn)
        equal_btn.setOnClickListener {
            calculate()
        }
    }

    /* fun clickListen(){
        if (symbol_text.text == "+") {
            min_btn.setOnClickListener {
                symbol_text.text = "-"
                minus()
            }
            mult_btn.setOnClickListener {
                symbol_text.text = "*"
                mult()
            }
            div_btn.setOnClickListener {
                symbol_text.text = "/"
                division()
            }
            plus_btn.setOnClickListener {
                symbol_text.text = "+"
                plus()
            }
        }

    }
     */
    fun setTextFields(str: String){
        math_oper = findViewById(R.id.math_oper)
        math_oper.append(str)
    }

    fun setTextFieldsSym(str: String) {
        symbol_text = findViewById(R.id.symbol_text)
        symbol_text.text = str

    }

    private fun performOper(operation: String, num1: String, num2: String): Double {
        val operand1 = num1.toDouble()
        val operand2 = num2.toDouble()
        return when (operation){
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> {
                if (operand2 != 0.0){
                    operand1 / operand2
                } else {
                    throw ArithmeticException("Division by 0")
                }
            } else -> throw IllegalArgumentException("Unsupported operation: $operation")
        }
    }

    fun plus(){
        plus_btn.setOnClickListener {
            setTextFieldsSym("+")
            if (result_text.text.isNotEmpty()){
                val result = performOper(prev_oper, result_text.text.toString(), math_oper.text.toString())
                if (math_oper.text.contains(".", ignoreCase = true) || result_text.text.contains(".", ignoreCase = true)) {
                    if (result.toString().contains(".0", ignoreCase = false)){
                        result_text.text = result.toInt().toString()
                    }
                    else{ result_text.text = result.toString() }
                    math_oper.text = ""
                } else {
                    result_text.text = result.toInt().toString()
                    math_oper.text=""
                }
            } else {
                result_text.text = math_oper.text
                math_oper.text = ""
            }
            prev_oper = "+"
        }
    }

    fun minus(){
        min_btn.setOnClickListener {
            setTextFieldsSym("-")
            if (result_text.text.isNotEmpty()){
                val result = performOper(prev_oper, result_text.text.toString(), math_oper.text.toString())
                if (math_oper.text.contains(".", ignoreCase = true) || result_text.text.contains(".", ignoreCase = true)) {
                    if (result.toString().contains(".0", ignoreCase = false)){
                        result_text.text = result.toInt().toString()
                    }
                    else{ result_text.text = result.toString() }
                    math_oper.text = ""
                } else {
                    result_text.text = result.toInt().toString()
                    math_oper.text=""
                }
            } else {
                result_text.text = math_oper.text
                math_oper.text = ""
            }
            prev_oper = "-"
        }
    }

    fun mult(){
        mult_btn.setOnClickListener {
            setTextFieldsSym("*")
            if (result_text.text.isNotEmpty()){
                val result = performOper(prev_oper, result_text.text.toString(), math_oper.text.toString())
                if (math_oper.text.contains(".", ignoreCase = true) || result_text.text.contains(".", ignoreCase = true)) {
                    if (result.toString().contains(".0", ignoreCase = false)){
                        result_text.text = result.toInt().toString()
                    }
                    else{ result_text.text = result.toString() }
                    math_oper.text = ""
                } else {
                    result_text.text = result.toInt().toString()
                    math_oper.text=""
                }
            } else {
                result_text.text = math_oper.text
                math_oper.text = ""
            }
            prev_oper = "*"
        }
    }

    fun division(){
        div_btn.setOnClickListener {
            setTextFieldsSym("/")
            if (result_text.text.isNotEmpty()){
                val result = performOper(prev_oper, result_text.text.toString(), math_oper.text.toString())
                if (math_oper.text.contains(".", ignoreCase = true) || result_text.text.contains(".", ignoreCase = true)) {
                    if (result.toString().contains(".0", ignoreCase = false)){
                        result_text.text = result.toInt().toString()
                    }
                    else{ result_text.text = result.toString() }
                    math_oper.text = ""
                } else {
                    result_text.text = result.toInt().toString()
                    math_oper.text=""}
            } else {
                result_text.text = math_oper.text
                math_oper.text = ""
            }
            prev_oper = "/"
        }
    }

    private fun calculate() {
        val operation = symbol_text.text.toString()
        val num1 = result_text.text.toString()
        val num2 = math_oper.text.toString()

        if (num1.isNotEmpty() && num2.isEmpty()){
            if (math_oper.text.contains(".", ignoreCase = true) || result_text.text.contains(".", ignoreCase = true)) {
                math_oper.text = result_text.text
            } else { math_oper.text = num1.toInt().toString() }
            result_text.text = ""
            symbol_text.text = ""
        }
        if (operation.isNotEmpty() && num1.isNotEmpty() && num2.isNotEmpty()) {
            try {
                val result = performOper(operation, num1, num2)
                if (math_oper.text.contains(".", ignoreCase = true) || result_text.text.contains(".", ignoreCase = true)) {
                    if (result.toString().contains(".0", ignoreCase = false)){
                            math_oper.text = result.toInt().toString()
                        }
                    else{math_oper.text = result.toString()}
                } else { math_oper.text = result.toInt().toString() }
                result_text.text = ""
                symbol_text.text = ""
                prev_oper = ""
            } catch (e: ArithmeticException) {
                math_oper.text = "На 0 делить нельзя!"
            }
        }
    }
}