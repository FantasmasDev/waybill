package com.fantasmas_dev.waybill

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(
    private val cards: List<Car>,
    private val viewForEdit: TextView
) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cards[position])
        holder.carCard.setOnClickListener {
            when (cards[position]) {
                is Car.Largus -> viewForEdit.text = "Lada Largus"
                is Car.Vesta -> viewForEdit.text = "Lada Vesta"
                is Car.Multiwan -> viewForEdit.text = "VW Multiwan"
            }
        }//TODO листенер
    }

    override fun getItemCount() = cards.size
}


//class CommentAdapter(
//    private val comments: List<Car>
//) : RecyclerView.Adapter<CardViewHolder> () {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
//        return CardViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
//        holder.bind(comments[position])
//    }
//
//    override fun getItemCount() = comments.size
//}