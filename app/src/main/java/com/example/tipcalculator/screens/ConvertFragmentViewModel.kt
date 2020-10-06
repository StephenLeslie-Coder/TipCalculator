package com.example.tipcalculator.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConvertFragmentViewModel:ViewModel() {
    var number:Int= 1



    fun minusOne(){
        if(number!=1){
            number--

        }

    }
    fun addOne(){
        if(number!=99){
            number++
        }

    }

}