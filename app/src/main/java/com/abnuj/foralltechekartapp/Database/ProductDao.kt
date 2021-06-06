package com.abnuj.foralltechekartapp.Database

import androidx.room.*
import com.abnuj.foralltechekartapp.Nerwork.ProductModel

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product:ProductModel)

    @Query("select * from ProductTable")
    suspend fun readallData():List<ProductModel>

    @Query("select * from ProductTable where id=:pid")
    suspend fun getSingleProduct(pid:Int):ProductModel

    @Delete
    suspend fun deleteProduct(product: ProductModel)
}