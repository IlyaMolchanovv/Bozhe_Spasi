package com.example.karusel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.karusel.data.Cars
import com.example.karusel.models.MainActivityModel

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var btn_add: Button
    private lateinit var btn_edit: Button
    private lateinit var btn_delete: Button
    private lateinit var btn_left: Button
    private lateinit var btn_right: Button
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

    private val viewModel: MainActivityModel by lazy {
        ViewModelProvider(this).get(MainActivityModel::class.java)
    }

    private val addCarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val car = data?.getSerializableExtra("car") as? Cars
            car?.let {
                viewModel.addCar(it)
                updateCars()
            }
        }
    }

    private val editCarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val car = data?.getSerializableExtra("car") as? Cars
            car?.let {
                viewModel.updateCar(it)
                updateCars()
            }
        }
    }

    private fun updateCars() {
        written_brand.text = viewModel.currentCarBrand
        written_model.text = viewModel.currentCarModel
        written_color.text = viewModel.currentCarColor
        written_mileage.text = viewModel.currentCarMileage
        written_cost.text = viewModel.currentCarCost
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_edit = findViewById(R.id.btn_edit)
        btn_add = findViewById(R.id.btn_add)
        btn_delete = findViewById(R.id.btn_delete)
        btn_left = findViewById(R.id.btn_left)
        btn_right = findViewById(R.id.btn_right)
        written_brand = findViewById(R.id.written_brand)
        written_model = findViewById(R.id.written_model)
        written_color = findViewById(R.id.written_color)
        written_mileage = findViewById(R.id.written_mileage)
        written_cost = findViewById(R.id.written_cost)

        updateCars()
        btn_edit.setOnClickListener {
            if (viewModel.ListSize() != 0) {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("written_brand", written_brand.text.toString())
                intent.putExtra("written_model", written_model.text.toString())
                intent.putExtra("written_color", written_color.text.toString())
                intent.putExtra("written_mileage", written_mileage.text.toString())
                intent.putExtra("written_cost", written_cost.text.toString())
                editCarLauncher.launch(intent)
            }
        }
        btn_add.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            addCarLauncher.launch(intent)
        }
        btn_right.setOnClickListener {
            viewModel.moveToNext()
            updateCars()
        }
        btn_left.setOnClickListener {
            viewModel.moveToPrev()
            updateCars()
        }
        btn_delete.setOnClickListener {
            viewModel.deleteCar()
            updateCars()
        }
    }

}

