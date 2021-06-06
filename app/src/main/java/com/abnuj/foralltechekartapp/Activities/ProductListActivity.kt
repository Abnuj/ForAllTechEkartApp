package com.abnuj.foralltechekartapp.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abnuj.foralltechekartapp.Adapters.ProductAdapter
import com.abnuj.foralltechekartapp.Nerwork.ProductModel
import com.abnuj.foralltechekartapp.Nerwork.RetrofitInstance
import com.abnuj.foralltechekartapp.R
import kotlinx.coroutines.*
import retrofit2.Response

class ProductListActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var productAdapter: ProductAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productAdapter = ProductAdapter()
        recyclerView = findViewById(R.id.product_list_recyclerVIew)


       GlobalScope.launch(Dispatchers.IO) {
            val products: Response<List<ProductModel>> = RetrofitInstance.api.getallProducts()
            if (products.isSuccessful) {
                withContext(Dispatchers.Main) {
                    productAdapter!!.submitList(products.body())
                }
            }
        }
        recyclerView?.apply {
            layoutManager =
                GridLayoutManager(this@ProductListActivity, 2)
            Log.d("TAG", "Adapter is called ")
            adapter = productAdapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.kartmenu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when (item.itemId) {
            R.id.menuKart -> {
                startActivity(Intent(this, KartActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
