package com.example.tipcalculator.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tipcalculator.R
import com.example.tipcalculator.databinding.FragmentConvertBinding
import kotlinx.android.synthetic.main.fragment_convert.*
import java.lang.Exception
import java.util.*


class ConvertFragment : Fragment() {
   private lateinit   var binding:FragmentConvertBinding
    private lateinit var viewModel: ConvertFragmentViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentConvertBinding.inflate(inflater,container,false)
        val view= binding.root


        viewModel=ViewModelProvider(this).get(ConvertFragmentViewModel::class.java)

        binding.myChipGroup.setOnCheckedChangeListener { group, checkedId ->
            Toast.makeText(activity,""+checkedId,Toast.LENGTH_SHORT).show()
            val tipText=when(checkedId){

                R.id.chip25 -> "0.25"
                R.id.chip20 -> "0.20"
                R.id.chip15 -> "0.15"
                R.id.chip10 -> "0.10"
                else -> "0.05"
            }

            binding.tipMultiplierResult.text=tipText
            if(binding.amountEditText.text.toString()!="" && binding.amountEditText.text.toString()!=".") {
                refresh()
            }
        }

        binding.amountEditText.addTextChangedListener( object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (binding.amountEditText.text.toString()=="."){
                   return
                }
                binding.billResultTextView.text=""+p0
                if(binding.amountEditText.length()>0  && binding.amountEditText.text.toString()!=".") {
                    splitBill()
                    calculateTip()

                }else{
                    binding.totalPerPersonResult.text="0.0"
                }





            }

            override fun afterTextChanged(p0: Editable?) {
                refresh()

            }

        })


        binding.removeButton.setOnClickListener {
           minusOne()

            binding.splitResult.text=viewModel.number.toString()
            refresh()

        }
        binding.addButton.setOnClickListener {
          addOne()
            binding.splitResult.text=viewModel.number.toString()
            refresh()
        
        }




        return view

    }
    fun refresh(){
        splitBill()
        calculateTip()
    }

    fun splitBill(){
        if (binding.amountEditText.text.toString()!="") {
            try {


                var amount = binding.billResultTextView.text.toString().toDouble()
                var split = binding.splitResult.text.toString().toInt()
                var totalPerPerson: Double = amount / split
                val answer: Double = Math.round(totalPerPerson * 10.0) / 10.0
                binding.totalPerPersonResult.text = answer.toString()
            }
            catch (e: Exception ){
                return

            }
        }
    }
    fun calculateTip(){
        if(binding.amountEditText.text.toString()!="") {
            try {


                val bill = binding.billResultTextView.text.toString().toDouble()
                val percentage = binding.tipMultiplierResult.text.toString().toDouble()
                var tipResult = (bill * percentage)
                val answer: Double = Math.round(tipResult * 10.0) / 10.0
                binding.tipResult.text = answer.toString()
            }
            catch (e: Exception){
                return
            }
        }
    }
    fun addOne(){
        viewModel.addOne()
        refresh()
    }
    fun minusOne(){
        viewModel.minusOne()
        refresh()
    }



}