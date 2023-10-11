package com.example.karusel.models

import androidx.lifecycle.ViewModel
import com.example.karusel.data.Cars

class MainActivityModel : ViewModel() {

    val carsBank = mutableListOf<Cars>()
    private var currentIndex = -1

    val currentCarBrand: String
        get() = if (currentIndex >= 0 && currentIndex < carsBank.size) {
            carsBank[currentIndex].carBrand
        } else {
            ""
        }

    val currentCarModel: String
        get() = if (currentIndex >= 0 && currentIndex < carsBank.size) {
            carsBank[currentIndex].carModel
        } else {
            ""
        }


    val currentCarColor: String
        get() = if (currentIndex >= 0 && currentIndex < carsBank.size) {
            carsBank[currentIndex].color
        } else {
            ""
        }

    val currentCarCost: String
        get() = if (currentIndex >= 0 && currentIndex < carsBank.size) {
            carsBank[currentIndex].cost
        } else {
            ""
        }

    val currentCarMileage: String
        get() = if (currentIndex >= 0 && currentIndex < carsBank.size) {
            carsBank[currentIndex].mileage
        } else {
            ""
        }

    fun moveToNext() {
        if (carsBank.isNotEmpty()) {
            currentIndex = (currentIndex + 1) % carsBank.size
        }
    }

    fun moveToPrev() {
        if (carsBank.isNotEmpty()) {
            currentIndex = (carsBank.size + currentIndex - 1) % carsBank.size
        }
    }

    fun ListSize(): Int {
        return carsBank.size
    }

    fun addCar(car: Cars) {
        if (carsBank.isEmpty()) {
            carsBank.clear()
            carsBank.add(car)
            currentIndex = 0
        } else {
            carsBank.add(car)
            currentIndex++
        }
    }

    fun updateCar(car: Cars) {
        if (currentIndex >= 0 && currentIndex < carsBank.size) {
            carsBank[currentIndex] = car
        }
    }

    fun deleteCar() {
        if (currentIndex >= 0 && currentIndex < carsBank.size) {
            carsBank.removeAt(currentIndex)
            if (carsBank.isEmpty()) {
                currentIndex = -1
            } else {
                currentIndex = currentIndex % carsBank.size
            }
        }
    }
    override fun toString(): String {
        return "Бренд\n$currentCarBrand\nМодель\n$currentCarModel\nЦвет\n$currentCarColor\nПробег\n$currentCarMileage\nЦена\n$currentCarCost"
    }
}
