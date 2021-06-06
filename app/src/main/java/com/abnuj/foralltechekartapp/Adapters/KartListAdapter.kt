package com.abnuj.foralltechekartapp.Adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abnuj.foralltechekartapp.Activities.ProductDescriptionActivity
import com.abnuj.foralltechekartapp.Nerwork.ProductModel
import com.abnuj.foralltechekartapp.R
import com.abnuj.foralltechekartapp.Utils.DatabaseUtils
import com.bumptech.glide.Glide


class KartListAdapter :
    ListAdapter<ProductModel, KartListAdapter.ProductViewholder>(ProductItemCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewholder {
        return ProductViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.kart_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewholder, position: Int) {
        val ProductItem = currentList[position]
        Log.d("TAG", "manageProduct Title : ${ProductItem?.title}")
        holder.manageProduct(ProductItem)
        holder.cardView.setOnClickListener {
            holder.cardviewClicklistener(ProductItem)
        }
        holder.removebtn.setOnClickListener {
            holder.removeProduct(ProductItem)
        }
    }


    class ProductViewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val productImageView = itemview.findViewById<ImageView>(R.id.kartproductImage)
        val productTitle = itemview.findViewById<TextView>(R.id.kartprodutTitleTv)
        val productCategory = itemview.findViewById<TextView>(R.id.kartprodutCategoryTv)
        val productPrice = itemview.findViewById<TextView>(R.id.kartProductPriceTv)
        val cardView = itemview.findViewById<CardView>(R.id.kartProductCardview)
        val removebtn = itemview.findViewById<Button>(R.id.removeFromKartbtn)

        // PI -> Current Product Item
        fun manageProduct(pi: ProductModel?) {
            Glide.with(itemView.context).load(Uri.parse(pi?.image))
                .placeholder(R.drawable.productplaceholder).into(productImageView)
            productTitle.text = pi?.title
            Log.d("TAG", "manageProduct Title : ${pi?.title}")
            productCategory.text = pi?.category
            productPrice.text = pi?.price.toString()
        }

        fun cardviewClicklistener(pi: ProductModel) {
            val intent = Intent(itemView.context, ProductDescriptionActivity::class.java)
            intent.putExtra("_id", pi.id)
            itemView.context.startActivity(intent)
        }

        fun removeProduct(pi:ProductModel) {
            DatabaseUtils.removeFromKart(itemView.context.applicationContext,pi)
        }


    }


    class ProductItemCallBack : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return newItem == oldItem
        }

    }

}