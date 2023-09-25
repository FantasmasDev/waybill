package com.fantasmas_dev.waybill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(
    private val cards: List<Car>
) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cards[position])
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