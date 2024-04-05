package com.ayein.bitebuddyadmin.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayein.bitebuddyadmin.databinding.ItemItemBinding
import com.ayein.bitebuddyadmin.model.AllMenu
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference

class AddItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    databaseReference: DatabaseReference,
    private val onDeleteClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<AddItemAdapter.AddItemViewHolder>() {

    private val itemQuantites = IntArray(menuList.size) { 1 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return menuList.size
    }


    inner class AddItemViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantites[position]
                val menuItem = menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)
                foodnameTextView.text = menuItem.foodName
                foodpriceTextView.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(foodImageView)
                quantityTextView.text = quantity.toString()
                minusButton.setOnClickListener {
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                trashButton.setOnClickListener {
                    //deleteQuantity(position)
                    onDeleteClickListener(position)
                }
            }
        }


        private fun increaseQuantity(position: Int) {
            if (itemQuantites[position] < 10) {
                itemQuantites[position]++
                binding.quantityTextView.text = itemQuantites[position].toString()
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantites[position] > 1) {
                itemQuantites[position]--
                binding.quantityTextView.text = itemQuantites[position].toString()
            }
        }

        private fun deleteQuantity(position: Int) {
            menuList.removeAt(position)
            menuList.removeAt(position)
            menuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, menuList.size)
        }


    }


}