package com.abnuj.foralltechekartapp.Nerwork

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductTable")
data class ProductModel(
    var category: String,
    var description: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int =1,
    var image: String,
    var price: Double,
    var title: String
)