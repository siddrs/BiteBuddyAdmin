package com.ayein.bitebuddyadmin.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayein.bitebuddyadmin.databinding.OrderDetailItemBinding
import com.bumptech.glide.Glide


class OrderDetailsAdapter(
    private var context: Context,
    private var foodNames: ArrayList<String>,
    private var foodImages: ArrayList<String>,
    private var foodQuantities: ArrayList<Int>,
    private var foodPrices: ArrayList<String>

) : RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsViewHolder {
        val binding =
            OrderDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodNames.size
    }

    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class OrderDetailsViewHolder(private val binding: OrderDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
           binding.apply {
               foodName.text = foodNames[position]
               foodQuantityIndicatorDigit.text = foodQuantities[position].toString()
                val uriString = foodImages[position]
               val uri = Uri.parse(uriString)
               Glide.with(context).load(uri).into(foodImage)
               foodPrice.text = foodPrices[position]
           }
        }

    }
}