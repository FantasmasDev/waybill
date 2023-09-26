package com.fantasmas_dev.waybill

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fantasmas_dev.waybill.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler = binding.cardList
        val selectedCar = binding.selectedCarText
        val button = binding.button
        val mileageBefore = binding.carMileageBefore
        val mileageAfter = binding.carMileageAfter
        val fuel = binding.fuelOfCar
        val issuedFuel = binding.fuelIssued
        val isRefill = binding.refill

        val totalMileage = binding.carMileage
        val fuelUsed = binding.fuelUsed
        val reminder = binding.remainder

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = CardAdapter(
            cards = listOf(Car.Largus, Car.Vesta, Car.Multiwan), selectedCar
        )

        isRefill.setOnClickListener {
            if(isRefill.isChecked){
                issuedFuel.visibility = View.VISIBLE
            } else {
                issuedFuel.visibility = View.GONE
            }
        }

        button.setOnClickListener {
            calculate()
        }
    }
    private fun calculate(){
        val before = binding.carMileageBefore.text.toString().toDoubleOrNull() ?: return
        val after = binding.carMileageAfter.text.toString().toDoubleOrNull() ?: return
        val fuel = binding.fuelOfCar.text.toString().toDoubleOrNull() ?: return

        if(after < before){
            return
        }

        val total = after - before
        binding.carMileage.text = total.toString()
    }
}
