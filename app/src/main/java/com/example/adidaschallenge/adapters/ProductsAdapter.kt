package com.example.adidaschallenge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.adidaschallenge.R
import com.example.adidaschallenge.models.Product
import kotlinx.android.synthetic.main.layout_single_product.view.*


/**
 * Created by Ahmad Mansour on 5/4/21
 * Dubai, UAE.
 */


class ProductsAdapter(val listener: ProductActionListener, val context: Context) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var products: MutableList<Product> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_single_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products, position)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setData(products: MutableList<Product>) {
        this.products = products
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        private val title: TextView = view.title_product as TextView
        private val name: TextView = view.name_product as TextView
        private val description: TextView = view.description_text as TextView
        private val price: TextView = view.price_text as TextView
        private val img: ImageView = view.product_img as ImageView

        fun bind(products: MutableList<Product>, position: Int) {
            title.text = products[position].id
            name.text = products[position].name
            description.text = products[position].description
            price.text = products[position].price.toString()
            Glide.with(context).load(products[position].imgUrl)
                .apply(RequestOptions().centerInside())
                .into(img)
        }

        override fun onClick(view: View?) {
            when (view?.id) {
                R.id.item_container -> listener.open(products[adapterPosition].id.toString())
            }
        }
    }

    interface ProductActionListener {
        fun open(id: String)
    }
}