package com.abnuj.foralltechekartapp.Utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.abnuj.foralltechekartapp.Database.ProductDatabase
import com.abnuj.foralltechekartapp.Nerwork.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DatabaseUtils {

    fun addDatatoDb(context: Context, product: ProductModel) {
        val database =
            ProductDatabase.getdatabaseInstance(context.applicationContext).getProductDao()
        GlobalScope.launch(Dispatchers.IO) {
            database.addProduct(product)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Product Added to Kart ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun getAllKartItem(context: Context): List<ProductModel>? {
        var alldata: List<ProductModel>? = null
        val database =ProductDatabase.getdatabaseInstance(context.applicationContext).getProductDao()
        alldata = database.readallData()
        Log.d("anuj", "onCreate: ${alldata!!.size} ")
        return alldata
    }

    fun getSingleProduct(context: Context, product: ProductModel) {
        val database =
            ProductDatabase.getdatabaseInstance(context.applicationContext).getProductDao()
        GlobalScope.launch(Dispatchers.IO) {
            database.getSingleProduct(product.id)
        }
    }

    fun removeFromKart(context: Context, product: ProductModel) {
        val database =
            ProductDatabase.getdatabaseInstance(context.applicationContext).getProductDao()
        GlobalScope.launch(Dispatchers.IO) {
            database.deleteProduct(product)
        }
    }


}

