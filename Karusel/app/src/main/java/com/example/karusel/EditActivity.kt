package com.example.karusel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.karusel.data.Cars
import com.example.karusel.models.MainActivityModel

class EditActivity : AppCompatActivity() {

    private lateinit var btn_save: Button
    private lateinit var txt_brand_car: TextView
    private lateinit var written_brand: TextView
    private lateinit var txt_model_car: TextView
    private lateinit var written_model: TextView
    private lateinit var txt_color_car: TextView
    private lateinit var written_color: TextView
    private lateinit var txt_mileage_car: TextView
    private lateinit var written_mileage: TextView
    private lateinit var txt_cost_car: TextView
    private lateinit var written_cost: TextView

    private val viewModel: MainActivityModel by lazy{
        val provider = ViewModelProvider(this)
        provider.get(MainActivityModel::class.java)
    }

    private fun CheckFields(): Boolean {
        var flag = true
        if (written_brand.text.isNullOrBlank()) {
            written_brand.error = "Заполните поле производителя"
            flag = false
        }
        else if (written_model.text.isNullOrBlank()) {
            written_model.error = "Заполните поле модели"
            flag = false
        }
        else if (written_color.text.isNullOrBlank()) {
            written_color.error = "Заполните поле цвета"
            flag = false
        }
        else if (written_mileage.text.isNullOrBlank()) {
            written_mileage.error = "Заполните поле пробега"
            flag = false
        } else if (written_mileage.text.toString()[0] == '0' && written_mileage.text.toString()[1] != '.') {
            written_mileage.error = "Не может начинаться с нуля"
            flag = false
        }
        else if (written_cost.text.isNullOrBlank()) {
            written_cost.error = "Заполните поле цены"
            flag = false
        }   else if (written_cost.text.toString()[0] == '0' && written_cost.text.toString()[1] != '.') {
            written_cost.error = "Не может начинаться с нуля"
            flag = false
        }
        return flag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        btn_save = findViewById(R.id.btn_save)
        written_brand = findViewById(R.id.written_brand)
        written_model = findViewById(R.id.written_model)
        written_color = findViewById(R.id.written_color)
        written_mileage = findViewById(R.id.written_mileage)
        written_cost = findViewById(R.id.written_cost)

        written_brand.setText(intent.getStringExtra("written_brand") ?: "")
        written_model.setText(intent.getStringExtra("written_model") ?: "")
        written_color.setText(intent.getStringExtra("written_color") ?: "")
        written_mileage.setText(intent.getStringExtra("written_mileage") ?: "")
        written_cost.setText(intent.getStringExtra("written_cost") ?: "")

        btn_save.setOnClickListener {
            if (CheckFields()) {
                val car = Cars(
                    written_brand.text.toString(),
                    written_model.text.toString(),
                    written_color.text.toString(),
                    written_mileage.text.toString(),
                    written_cost.text.toString()
                )
                val intent = Intent()
                intent.putExtra("car", car)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}