package com.ayein.bitebuddyadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayein.bitebuddyadmin.adapter.OrderDetailsAdapter
import com.ayein.bitebuddyadmin.databinding.ActivityOrderDetailsBinding
import com.ayein.bitebuddyadmin.model.OrderDetails

class OrderDetailsActivity : AppCompatActivity() {

    private val binding: ActivityOrderDetailsBinding by lazy {
        ActivityOrderDetailsBinding.inflate(layoutInflater)
    }

    private var userName: String? = null
    private var phoneNumber: String? = null
    private var totalPrice: String?= null
    private var foodNames: ArrayList<String> = arrayListOf()
    private var foodImages: ArrayList<String> = arrayListOf()
    private var foodQuantity: ArrayList<Int> = arrayListOf()
    private var foodPrices: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.goBackButton.setOnClickListener { finish() }

        getDataFromIntent()

    }

    private fun getDataFromIntent() {
        val recievedOrderDetails = intent.getSerializableExtra("UserOrderDetails") as OrderDetails
        recievedOrderDetails?.let {orderDetails ->
            if (recievedOrderDetails != null){
                userName = recievedOrderDetails.userName
                foodNames = recievedOrderDetails.foodNames as ArrayList<String>
                foodImages = recievedOrderDetails.foodImages as ArrayList<String>
                foodQuantity = recievedOrderDetails.foodQuantities as ArrayList<Int>
                foodPrices = recievedOrderDetails.foodPrices as ArrayList<String>
                phoneNumber = recievedOrderDetails.phoneNumber
                totalPrice = recievedOrderDetails.totalPrice

                setUserDetails()
                setAdapter()
            }

        }

    }

    private fun setAdapter() {
        binding.orderDetailsRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = OrderDetailsAdapter(this, foodNames, foodImages, foodQuantity, foodPrices)
        binding.orderDetailsRecyclerView.adapter = adapter
    }

    private fun setUserDetails() {
        binding.name.text = userName
        binding.phone.text = phoneNumber
        binding.totalPay.text = totalPrice

    }
}