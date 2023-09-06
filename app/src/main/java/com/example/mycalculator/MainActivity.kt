package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var Display_text : TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Display_text = findViewById(R.id.display_text)

    }


    fun onDigit(view : View){

        Display_text?.append((view as Button).text)
        lastNumeric= true
        lastDot= false



    }


    fun onClear(view: View){
        Display_text?.text =""

    }


    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            Display_text?.append(".")
            lastNumeric=  false
            lastDot = true
        }


    }



    fun onOperator(view: View){
        Display_text?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                Display_text?.append((view as Button).text)
                lastNumeric = false
                lastDot = false

            }
        }


    }

    fun onEqual(view: View){
        if(lastNumeric){
            var display_value = Display_text?.text.toString()
            var prefix = ""

            try {

                if(display_value.startsWith("-")){
                    prefix = "-"
                    display_value = display_value.substring(1)

                }

                if(display_value.contains("-")){
                    val splitvalue = display_value.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }


                    Display_text?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                } else if(display_value.contains("+")){
                    val splitvalue = display_value.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }


                    Display_text?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                } else if(display_value.contains("*")){
                    val splitvalue = display_value.split("*")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }


                    Display_text?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                } else if(display_value.contains("/")){
                    val splitvalue = display_value.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }


                    Display_text?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }





            }catch (e: ArithmeticException){
                e.printStackTrace()
            }






        }


    }



    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0,result.length-2)


        return value
    }




    private fun isOperatorAdded(value:String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")
        }

    }



}