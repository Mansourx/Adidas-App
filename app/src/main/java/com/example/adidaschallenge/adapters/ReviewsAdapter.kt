package com.example.adidaschallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adidaschallenge.R
import com.example.adidaschallenge.models.Review
import kotlinx.android.synthetic.main.layout_review.view.*


/**
 * Created by Ahmad Mansour on 5/5/21
 * Dubai, UAE.
 */


class ReviewsAdapter(private val reviews: List<Review>?) :
    RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_review, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewsAdapter.ViewHolder, position: Int) {
        holder.bind(reviews?.get(position)?.text)
    }

    override fun getItemCount(): Int {
        return reviews?.size ?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val reviewText: TextView = view.review_text as TextView

        fun bind(review: String?) {
            reviewText.text = review ?: ""
        }
    }

}