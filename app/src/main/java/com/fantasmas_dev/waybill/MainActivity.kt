package com.fantasmas_dev.waybill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.cardList)

//        val images = listOf(
//            resources.getIdentifier("test_pic", "drawable", packageName),
//            resources.getIdentifier("test_pic2", "drawable", packageName),
//            resources.getIdentifier("test_pic3", "drawable", packageName)
//        )

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = CardAdapter(
            cards = listOf(Car.Largus, Car.Vesta, Car.Multiwan)
        )
    }

}
