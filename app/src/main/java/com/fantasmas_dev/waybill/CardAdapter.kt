package com.fantasmas_dev.waybill

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(
    private val cards: List<Car>,
    private val viewForEdit: TextView,
    private val mainActivity : MainActivity
) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cards[position])
        holder.carCard.setOnClickListener {
            var newFuelConsumption = 0.0
            when (cards[position]) {
                is Car.Largus -> {
                    viewForEdit.text = Car.Largus.carName
                    newFuelConsumption = Car.Largus.carMileage
                    mainActivity.updateUsersInput(newFuelConsumption)
                }
                is Car.Vesta -> {
                    viewForEdit.text = Car.Vesta.carName
                    newFuelConsumption = Car.Vesta.carMileage
                    mainActivity.updateUsersInput(newFuelConsumption)
                }
                is Car.Multiwan -> {
                    viewForEdit.text = Car.Multiwan.carName
                    newFuelConsumption = Car.Multiwan.carMileage
                    mainActivity.updateUsersInput(newFuelConsumption)
                }
            }
        }
    }

    override fun getItemCount() = cards.size
}

