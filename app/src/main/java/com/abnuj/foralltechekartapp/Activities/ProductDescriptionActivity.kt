package com.abnuj.foralltechekartapp.Activities

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.abnuj.foralltechekartapp.Nerwork.ProductModel
import com.abnuj.foralltechekartapp.Nerwork.RetrofitInstance
import com.abnuj.foralltechekartapp.R
import com.abnuj.foralltechekartapp.Utils.DatabaseUtils
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDescriptionActivity : AppCompatActivity() {
    var product: ProductModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)
        val productImage = findViewById<ImageView>(R.id.ProductImageview)
        val titletv = findViewById<TextView>(R.id.des_productTitletv)
        val pricetv = findViewById<TextView>(R.id.des_pricetv)
        val categorytv = findViewById<TextView>(R.id.des_categorytv)
        val descriptiontv = findViewById<TextView>(R.id.des_discriptiontv)
        val kartBtn = findViewById<Button>(R.id.addcartbtn)
        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val id = intent.getIntExtra("_id", 1)


        // set room data base here
        kartBtn.setOnClickListener {
            DatabaseUtils.addDatatoDb(product = product!!,context = this.applicationContext)
        }

        GlobalScope.launch(Dispatchers.IO) {
            val ProductResponse = RetrofitInstance.api.getSingleProduct(id)
            if (ProductResponse.isSuccessful) {
                withContext(Dispatchers.Main) {
                    product = ProductResponse.body()
                    progressBar.visibility = View.GONE
                    nestedScrollView.visibility = View.VISIBLE
                    kartBtn.visibility = View.VISIBLE

                    Glide.with(applicationContext).load(Uri.parse(product?.image))
                        .placeholder(R.drawable.productplaceholder).into(productImage)
                    titletv.text = product?.title
                    pricetv.text = product?.price.toString()
                    categorytv.text = product?.category
                    descriptiontv.text = product?.description
                }
            }
        }

    }
}
