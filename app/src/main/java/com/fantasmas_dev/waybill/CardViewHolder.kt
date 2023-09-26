package com.fantasmas_dev.waybill

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val carImageView: ImageView
    val carCard: MaterialCardView
    init {
        carImageView = itemView.findViewById(R.id.car_card_image)
        carCard = itemView.findViewById(R.id.car_card)
    }

    fun bind(model: Car) {
        carImageView.setImageResource(model.carImage)
    }
}

//class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    private val userNumberView: TextView
//    private val commentTextView: TextView
//    private val userImageView: ImageView
//
//    init {
//        userNumberView = itemView.findViewById(R.id.userNumber)
//        commentTextView = itemView.findViewById(R.id.commentText)
//        userImageView = itemView.findViewById(R.id.userImage)
//    }

//    fun bind(model: Comment) {
//        userNumberView.text = "#${model.userNumber}"
//        commentTextView.text = model.commentText
//        userImageView.setImageResource(model.userImage)
//        // Генерируем новый уникальный ID
//
//
//    }
//}