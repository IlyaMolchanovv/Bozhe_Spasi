package com.example.karusel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private lateinit var txt_output: TextView

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
            txt_output.text = viewModel.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_edit = findViewById(R.id.btn_edit)
        btn_add = findViewById(R.id.btn_add)
        btn_delete = findViewById(R.id.btn_delete)
        btn_left = findViewById(R.id.btn_left)
        btn_right = findViewById(R.id.btn_right)
        txt_output = findViewById(R.id.txt_output)

        updateCars()
        btn_edit.setOnClickListener {
            if (viewModel.ListSize() != 0) {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("written_brand", viewModel.currentCarBrand)
                intent.putExtra("written_model", viewModel.currentCarModel)
                intent.putExtra("written_color", viewModel.currentCarColor)
                intent.putExtra("written_mileage", viewModel.currentCarMileage)
                intent.putExtra("written_cost", viewModel.currentCarCost)
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


