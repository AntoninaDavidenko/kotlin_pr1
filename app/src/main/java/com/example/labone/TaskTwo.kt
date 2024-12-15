package com.example.labone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TaskTwo : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task_two)
        val answerTextView: TextView = findViewById(R.id.answer2)
        val inputH: EditText = findViewById(R.id.inputH2)
        val inputC: EditText = findViewById(R.id.inputC2)
        val inputS: EditText = findViewById(R.id.inputS2)
        val inputV: EditText = findViewById(R.id.inputV2)
        val inputO: EditText = findViewById(R.id.inputO2)
        val inputW: EditText = findViewById(R.id.inputW2)
        val inputA: EditText = findViewById(R.id.inputA2)
        val inputQ: EditText = findViewById(R.id.inputQ)

        val sumButton2: Button = findViewById(R.id.button2)
        val buttonBack2: Button = findViewById(R.id.button_back2)

        buttonBack2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sumButton2.setOnClickListener {
            val hydrogenH = inputH.text.toString().toDouble()
            val carbonC = inputC.text.toString().toDouble()
            val sulfurS = inputS.text.toString().toDouble()
            val oxygenO = inputO.text.toString().toDouble()
            val vanadiumV = inputV.text.toString().toDouble()
            val moistureW = inputW.text.toString().toDouble()
            val ashA = inputA.text.toString().toDouble()
            val lowerHeatOfCombustion = inputQ.text.toString().toDouble()
            // Склад робочої маси мазуту
            val hRaw = hydrogenH * (100 - moistureW - ashA) / 100
            val cRaw = carbonC * (100 - moistureW - ashA) / 100
            val sRaw = sulfurS * (100 - moistureW - ashA) / 100
            val oRaw = oxygenO * (100 - moistureW - ashA) / 100
            val vRaw = vanadiumV * (100 - moistureW) / 100
            val aRaw = ashA * (100 - moistureW) / 100
            // Перерахунок теплоти згоряння з горючої маси на робочу
            val heatCombustibleToRaw = lowerHeatOfCombustion * ((100 - moistureW - ashA) / 100) - 0.025 * moistureW

            answerTextView.text = "Для складу горючої маси мазуту, що задано наступними параметрами: Hг=${hydrogenH}%;\n" +
                    "Cг=${carbonC}%; Sг=${sulfurS}%; Oг=${oxygenO}%; Vг=${vanadiumV} мг/кг; Wг=${moistureW}%; Aг=${ashA}; " +
                    "та нижчою теплотою згоряння горючої маси мазуту Q =${lowerHeatOfCombustion} МДж/кг:\n" +
                    "Склад робочої маси мазуту становитиме: Hр=${hRaw}%; Cр=${cRaw}%; Sр=${sRaw}%; Oр=${oRaw}%," +
                    "Vр=${vRaw} мг/кг; Ар=${aRaw}%;\n" +
                    "Нижча теплота згоряння мазуту на робочу масу для робочої маси за заданим складом " +
                    "компонентів палива становить: $heatCombustibleToRaw МДж/кг."
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}