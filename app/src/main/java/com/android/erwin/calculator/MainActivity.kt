package com.android.erwin.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_00.setOnClickListener { appendClick(true, "00") }
        btn_0.setOnClickListener { appendClick(true, "0") }
        btn_1.setOnClickListener { appendClick(true, "1") }
        btn_2.setOnClickListener { appendClick(true, "2") }
        btn_3.setOnClickListener { appendClick(true, "3") }
        btn_4.setOnClickListener { appendClick(true, "4") }
        btn_5.setOnClickListener { appendClick(true, "5") }
        btn_6.setOnClickListener { appendClick(true, "6") }
        btn_7.setOnClickListener { appendClick(true, "7") }
        btn_8.setOnClickListener { appendClick(true, "8") }
        btn_9.setOnClickListener { appendClick(true, "9") }
        btn_dot.setOnClickListener { appendClick(true, ".") }

        btn_plus.setOnClickListener { appendClick(false, "+") }
        btn_minus.setOnClickListener { appendClick(false, "-") }


        btn_clear.setOnClickListener {
            tvInput.text = ""
            tvOutput.text = ""
        }

        btn_result.setOnClickListener {
            try {
                val input = ExpressionBuilder(tvInput.text.toString()).build()
                val output = input.evaluate()
                val longresult = output.toLong()
                if (output == longresult.toDouble()) {
                    tvOutput.text = longresult.toString()
                } else {
                    tvOutput.text = output.toString()
                }

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

        btn_delete.setOnClickListener {
            val string = tvInput.text.toString()
            if (string.isNotEmpty()) {
                tvInput.text = string.substring(0, string.length - 1)
            }
            tvOutput.text = ""
        }
    }

    private fun appendClick(clear: Boolean, string: String) {
        if (clear) {
            tvOutput.text = ""
            tvInput.append(string)
        } else {
            tvInput.append(tvOutput.text)
            tvInput.append(string)
            tvOutput.text = ""
        }
    }
}