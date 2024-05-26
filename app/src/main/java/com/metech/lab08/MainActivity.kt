
package com.metech.lab08

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.enableEdgeToEdge
import com.metech.lab08.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val pizzaSize = arrayOf("Small", "Medium", "Large", "Extra Large")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.dateBtn.setOnClickListener {
            //get the current date
            val cDate = Calendar.getInstance()
            val day = cDate.get(Calendar.DAY_OF_MONTH)
            val month = cDate.get(Calendar.MONTH)
            val year = cDate.get(Calendar.YEAR)

            val myDatePicker = DatePickerDialog(
                this,   //dimana ia akan keluar => ia akan keluar di MainActivity
                android.R.style.ThemeOverlay,   // style datepicker (default)
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->  //listener selepas tarikh dipilih
                    binding.dateTV.text = "$dayOfMonth/${month+1}/$year"
                },
                year,   //default year
                month,  //defalut month
                day     //default date
            )
            myDatePicker.show()
        }

        binding.timeBtn.setOnClickListener {

            val cTime = Calendar.getInstance()
            val hour = cTime.get(Calendar.HOUR_OF_DAY)
            val minute = cTime.get(Calendar.MINUTE)

            val myTimePicker = TimePickerDialog(
                this,
                android.R.style.ThemeOverlay,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    val hourFormatted = String.format("%02d", hourOfDay)
                    val minuteFormatted = String.format("%02d", minute)
                    binding.timeTV.text = "$hourFormatted:$minuteFormatted"
                },
                hour,
                minute,
                true

            )
            myTimePicker.show()

        }


        binding.slider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            //bila seekbar bergerak atau berubah nilai
            //nilai yang diubah 1,2,3,4 -> progress [int]
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.sliderStatus.text = pizzaSize[progress]
            }

            //bila mula ditekan
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                binding.sliderStatus.text = "start tekan"
            }

            //bila habis ditekan
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        binding.scheduleBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", binding.nameEditText.text.toString())
            intent.putExtra("phone", binding.phoneEditText.text.toString())
            intent.putExtra("date", binding.dateTV.text.toString())
            intent.putExtra("time", binding.timeTV.text.toString())
            intent.putExtra("pizzaSize", binding.sliderStatus.text.toString())
            startActivity(intent)
        }
    }
}