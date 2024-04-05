package com.ayein.bitebuddyadmin.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ayein.bitebuddyadmin.databinding.DeliveryItemBinding

class DeliveryAdapter(
    private val customerNames: MutableList<String>,
    private val moneyStatus: MutableList<Boolean>
) : RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding =
            DeliveryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeliveryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return customerNames.size
    }


    inner class DeliveryViewHolder(private val binding: DeliveryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                customerName.text = customerNames[position]
                if (moneyStatus[position] == true){
                   statusMoney.text = "RECEIVED"
                }
                else {
                    statusMoney.text = "NOT RECEIVED"
                }
                val colorMap = mapOf(
                    true to Color.parseColor("#66BB6A"),
                    false to Color.parseColor("#EF5350"),
                    "PENDING" to Color.parseColor("#636363")
                )
                statusMoney.setTextColor(colorMap[moneyStatus[position]] ?: Color.BLACK)
                StatusColor.backgroundTintList =
                    ColorStateList.valueOf(colorMap[moneyStatus[position]] ?: Color.BLACK)
            }
        }

    }

}