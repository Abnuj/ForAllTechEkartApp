package com.abnuj.foralltechekartapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abnuj.foralltechekartapp.Nerwork.ProductModel

@Database(entities = arrayOf(ProductModel::class), version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao():ProductDao
    companion object {
        @Volatile
        private var Instance: ProductDatabase? = null
        fun getdatabaseInstance(context: Context): ProductDatabase {
            val tempinstance = Instance
            if (tempinstance != null) {
                return tempinstance
            }
            kotlin.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "ProductDatabase"
                ).build()
                Instance = instance
                return instance
            }
        }
    }
}