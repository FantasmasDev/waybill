package com.fantasmas_dev.waybill

import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fantasmas_dev.waybill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var carFuelConsumption = 0.0
    private var isNotChecked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recycler = binding.cardList
        val selectedCar = binding.selectedCarText
        val button = binding.button
        val fuel = binding.fuelOfCar
        val issuedFuel = binding.fuelIssued
        val isRefill = binding.refill

        recycler.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = CardAdapter(
                listOf(Car.Largus, Car.Vesta, Car.Multiwan),
                selectedCar,
                this@MainActivity
            )
        }

        isRefill.setOnCheckedChangeListener { _, isChecked ->
            issuedFuel.isEnabled = isChecked
        }

        fuel.filters = arrayOf<InputFilter>(NumberInputFilter(5, 2))

        button.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        val mileageBefore: Int? = binding.carMileageBefore.text.toString().toIntOrNull()
        val mileageAfter: Int? = binding.carMileageAfter.text.toString().toIntOrNull()
        val carFuel: Double? = binding.fuelOfCar.text.toString().toDoubleOrNull()
        val issuedFuel = if (binding.refill.isChecked) {
            binding.fuelIssued.text.toString().toDoubleOrNull()
        } else {
            null
        }

        if (isNotChecked) {
            Toast.makeText(this, getString(R.string.error_select_car), Toast.LENGTH_LONG).show()
            return
        }

        if (mileageBefore == null) {
            Toast.makeText(this, getString(R.string.error_mileage_before), Toast.LENGTH_LONG).show()
            return
        }

        if (mileageAfter == null) {
            Toast.makeText(this, getString(R.string.error_mileage_after), Toast.LENGTH_LONG).show()
            return
        }

        if (mileageAfter < mileageBefore) {
            Toast.makeText(this, getString(R.string.error_mileage), Toast.LENGTH_LONG)
                .show()
            return
        }

        if (carFuel == null) {
            Toast.makeText(this, getString(R.string.error_fuel_issued), Toast.LENGTH_LONG).show()
            return
        }

        if (binding.refill.isChecked && issuedFuel == null) {
            Toast.makeText(
                this,
                getString(R.string.error_fuel_refill),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val totalMileage = mileageAfter - mileageBefore
        binding.carMileage.text =
            getString(R.string.car_mileage_calculated, totalMileage.toString())

        val usedFuel = (totalMileage * carFuelConsumption) / 100
        binding.fuelUsed.text =
            getString(R.string.fuel_used_calculated, String.format("%.2f", usedFuel))

        val totalReminder = if (binding.refill.isChecked) {
            carFuel - usedFuel + issuedFuel!!
        } else {
            carFuel - usedFuel
        }
        binding.remainder.text =
            getString(R.string.reminder_calculated, String.format("%.2f", totalReminder))
    }

    fun updateUsersInput(newFuelConsumption: Double) {
        carFuelConsumption = newFuelConsumption
        isNotChecked = false
    }
}