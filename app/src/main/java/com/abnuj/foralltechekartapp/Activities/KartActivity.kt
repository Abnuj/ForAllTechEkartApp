package com.abnuj.foralltechekartapp.Activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abnuj.foralltechekartapp.Adapters.KartListAdapter
import com.abnuj.foralltechekartapp.R
import com.abnuj.foralltechekartapp.Utils.DatabaseUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kart)
        val recyclerView = findViewById<RecyclerView>(R.id.KartListRecyclerView)
        val kartApater = KartListAdapter()
        recyclerView.apply {
            layoutManager =
                GridLayoutManager(this@KartActivity, 2)
            Log.d("TAG", "Adapter is called ")
            adapter = kartApater
        }

        GlobalScope.launch(Dispatchers.IO) {
            val list = DatabaseUtils.getAllKartItem(applicationContext)
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "list size : ${list?.size}", Toast.LENGTH_SHORT)
                    .show()
                kartApater.submitList(list)
            }
        }

    }
}