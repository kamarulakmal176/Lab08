package com.metech.lab08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metech.lab08.databinding.ActivityMainBinding
import com.metech.lab08.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.nameTextView.text = "Name :  " + intent.getStringExtra("name")
        binding.phoneTextView.text = "Phone Number :  " + intent.getStringExtra("phone")
        binding.sizeTextView.text = "Size :  " + intent.getStringExtra("pizzaSize")
        binding.dateTextView.text = "Pickup Date :  " + intent.getStringExtra("date")
        binding.timeTextView.text = "Pickup Time :  " + intent.getStringExtra("time")

        binding.rateBtn.setOnClickListener {
            binding.rateTV.text = binding.ratingBar.rating.toString()
        }


    }
}