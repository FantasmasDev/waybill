package com.fantasmas_dev.waybill

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val carImageView: ImageView
    private val carNameView: TextView
    val carCard: MaterialCardView
    init {
        carImageView = itemView.findViewById(R.id.car_card_image)
        carNameView = itemView.findViewById(R.id.car_name)
        carCard = itemView.findViewById(R.id.car_card)
    }

    fun bind(model: Car) {
        carImageView.setImageResource(model.carImage)
        carNameView.text = model.carName
    }
}