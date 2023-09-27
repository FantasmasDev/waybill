package com.fantasmas_dev.waybill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fantasmas_dev.waybill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var carFuelConsumption = 0.0
    var isNotChecked = true

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
            cards = listOf(Car.Largus, Car.Vesta, Car.Multiwan), selectedCar, this
        )

        isRefill.setOnCheckedChangeListener { buttonView, isChecked ->
            issuedFuel.isEnabled = isChecked
        }

        button.setOnClickListener {
            calculate(carFuelConsumption)
        }
    }

    private fun calculate(carFuelConsumption: Double) {
        val mileageBefore = binding.carMileageBefore.text.toString().toIntOrNull() ?: return
        val mileageAfter = binding.carMileageAfter.text.toString().toIntOrNull() ?: return
        val carFuel = binding.fuelOfCar.text.toString().toDoubleOrNull() ?: return
        var issuedFuel = 0.0

        if(binding.refill.isChecked){
            issuedFuel = binding.fuelIssued.text.toString().toDoubleOrNull() ?: return
        }

        if (mileageAfter < mileageBefore || isNotChecked) {
            return
        }

        val totalMileage = mileageAfter - mileageBefore
        binding.carMileage.text = totalMileage.toString()

        val usedFuel = totalMileage * carFuelConsumption / 100
        binding.fuelUsed.text = usedFuel.toString()

        val totalReminder = if(binding.refill.isChecked){
            carFuel - usedFuel + issuedFuel
        }else{
            carFuel - usedFuel
        }
        binding.remainder.text = totalReminder.toString()
    }

    fun updateUsersInput(newFuelConsumption: Double) {
        carFuelConsumption = newFuelConsumption
        isNotChecked = false
    }
}
