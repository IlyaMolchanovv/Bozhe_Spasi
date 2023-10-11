package com.example.karusel.data

import java.io.Serializable

data class Cars(
    val carBrand: String,
    val carModel: String,
    val color: String,
    val cost: String,
    val mileage: String
) : Serializable {
//    override fun toString(): String {
//        return "Бренд\n$carBrand\nМодель\n$carModel\nЦвет\n$color\nПробег\n$mileage\nЦена\n$cost"
//    }
}
