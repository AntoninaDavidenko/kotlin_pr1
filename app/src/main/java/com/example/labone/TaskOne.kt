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
import kotlin.math.roundToInt

class TaskOne : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task_one)

        val answerTextView: TextView = findViewById(R.id.answer)
        val inputH: EditText = findViewById(R.id.inputH)
        val inputC: EditText = findViewById(R.id.inputC)
        val inputS: EditText = findViewById(R.id.inputS)
        val inputN: EditText = findViewById(R.id.inputN)
        val inputO: EditText = findViewById(R.id.inputO)
        val inputW: EditText = findViewById(R.id.inputW)
        val inputA: EditText = findViewById(R.id.inputA)

        val sumButton: Button = findViewById(R.id.button)
        val buttonBack: Button = findViewById(R.id.button_back)

        sumButton.setOnClickListener {
            val hydrogenH = inputH.text.toString().toDouble()
            val carbonC = inputC.text.toString().toDouble()
            val sulfurS = inputS.text.toString().toDouble()
            val nitrogenN = inputN.text.toString().toDouble()
            val oxygenO = inputO.text.toString().toDouble()
            val moistureW = inputW.text.toString().toDouble()
            val ashA = inputA.text.toString().toDouble()

            // Коефіцієнт переходу від робочої до сухої маси та коефіцієнт переходу від  робочої до горючої
            var rawToDry = 100 / (100 - moistureW)
            rawToDry = (rawToDry * 100).roundToInt() / 100.0
            val rawToCombustible = 100 / (100 - moistureW - ashA)

            // Склад сухої маси палива
            val hDry = hydrogenH * rawToDry
            val cDry = carbonC * rawToDry
            val sDry = sulfurS * rawToDry
            val nDry = nitrogenN * rawToDry
            val oDry = oxygenO * rawToDry
            val aDry = ashA * rawToDry
            // Перевірка
            val check = hDry + cDry + sDry + nDry + oDry + aDry

            // Склад горючої маси палива
            val hCombustible  = hydrogenH * rawToCombustible
            val cCombustible  = carbonC * rawToCombustible
            val sCombustible  = sulfurS * rawToCombustible
            val nCombustible  = nitrogenN * rawToCombustible
            val oCombustible  = oxygenO * rawToCombustible
            // Перевірка
            val check2 = hCombustible + cCombustible + sCombustible + nCombustible + oCombustible
            // Нижчка теплота згоряння
            val lowerHeatOfCombustion = (339.0 * carbonC + 1030.0 * hydrogenH - 108.8 * (oxygenO - sulfurS) - 25.0 * moistureW) / 1000
            // Перерахунок теплоти на суху та горючу масу
            val dryMass = (lowerHeatOfCombustion + 0.025 * moistureW) * (100 / (100 - moistureW))
            val combustibleMass = (lowerHeatOfCombustion + 0.025 * moistureW) * (100 / (100 - moistureW - ashA))

            answerTextView.text = "Для палива з компонентним складом: Н=${String.format("%.2f", hydrogenH)},  " +
                    "C=${String.format("%.2f", carbonC)}, " +
                    "S=${String.format("%.2f", sulfurS)}, N=${String.format("%.2f", nitrogenN)}, " +
                    "O=${String.format("%.2f", oxygenO)}, W=${String.format("%.2f", moistureW)}, " +
                    "A=${String.format("%.2f", ashA)}: \n" +
                    "Коефіцієнт переходу від робочої до сухої маси: ${String.format("%.2f", rawToDry)} \n" +
                    "Коефіцієнт переходу від робочої до горючої маси: ${String.format("%.2f", rawToCombustible)} \n" +
                    "Склад сухої маси палива: Н=${String.format("%.2f", hDry)}, C=${String.format("%.2f", cDry)}, " +
                    "S=${String.format("%.2f", sDry)}, N=${String.format("%.2f", nDry)}, O=${String.format("%.2f", oDry)}, " +
                    "A=${String.format("%.2f", aDry)} \n" +
                    "Склад горючої маси палива: Н=${String.format("%.2f", hCombustible)}, " +
                    "C=${String.format("%.2f", cCombustible)}, S=${String.format("%.2f", sCombustible)}, " +
                    "N=${String.format("%.2f", nCombustible)}, O=${String.format("%.2f", oCombustible)} \n" +
                    "Нижча теплота згоряння для робочої маси: ${String.format("%.4f", lowerHeatOfCombustion)}, МДж/кг \n" +
                    "Нижча теплота згоряння для сухої маси: ${String.format("%.2f", dryMass)}, МДж/кг \n" +
                    "Нижча теплота згоряння для горючої маси: ${String.format("%.2f", combustibleMass)}, МДж/кг \n"

        }

        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}