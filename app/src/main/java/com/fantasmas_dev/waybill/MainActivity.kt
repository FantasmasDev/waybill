package com.fantasmas_dev.waybill

import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        binding.fuelOfCar.filters = arrayOf<InputFilter>(NumberInputFilter(5, 2))

        button.setOnClickListener {
            calculate(carFuelConsumption)
        }
    }

    private fun calculate(carFuelConsumption: Double) {
        val mileageBefore: Int? = binding.carMileageBefore.text.toString().toIntOrNull()
        val mileageAfter: Int? = binding.carMileageAfter.text.toString().toIntOrNull()
        val carFuel: Double? = binding.fuelOfCar.text.toString().toDoubleOrNull()
        var issuedFuel: Double? = null

        if (isNotChecked) {
            Toast.makeText(this, "Выберете автомобиль", Toast.LENGTH_LONG).show()
            return
        }

        if (mileageBefore == null) {
            Toast.makeText(this, "Запаолните пробег перед выездом", Toast.LENGTH_LONG).show()
            return
        }

        if (mileageAfter == null) {
            Toast.makeText(this, "Запаолните пробег после выезда", Toast.LENGTH_LONG).show()
            return
        }

        if (mileageAfter < mileageBefore) {
            Toast.makeText(this, "пробег после не может быть меньше чем до", Toast.LENGTH_LONG)
                .show()
            return
        }

        if (carFuel == null) {
            Toast.makeText(this, "Запаолните топливо на начало выезда", Toast.LENGTH_LONG).show()
            return
        }

        if (binding.refill.isChecked) {
            issuedFuel = binding.fuelIssued.text.toString().toDoubleOrNull()
            if (issuedFuel == null) {
                Toast.makeText(
                    this,
                    "Запаолните поле, сколько было заправлено топлива в прошлый выезд",
                    Toast.LENGTH_LONG
                ).show()
                return
            }
        }

        val totalMileage = mileageAfter - mileageBefore
        binding.carMileage.text = totalMileage.toString()

        val usedFuel = totalMileage * carFuelConsumption / 100
        binding.fuelUsed.text = usedFuel.toString()

        val totalReminder = if (binding.refill.isChecked) {
            carFuel - usedFuel + issuedFuel!!
        } else {
            carFuel - usedFuel
        }
        binding.remainder.text = totalReminder.toString()
    }

    fun updateUsersInput(newFuelConsumption: Double) {
        carFuelConsumption = newFuelConsumption
        isNotChecked = false
    }
}