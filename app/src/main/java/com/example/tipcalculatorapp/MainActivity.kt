
package com.example.tipcalculatorapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculatorapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener(){
            calculateButton()
        }
    }
    @SuppressLint("StringFormatInvalid")
    private fun calculateButton(){
        val stringInTextField = binding.cost.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null || cost == 0.0){
            displayTip(0.0)
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when(selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        //val roundUp = binding.roundUpSwitch.isChecked
        if(binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }
    private fun displayTip(tip : Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }


}