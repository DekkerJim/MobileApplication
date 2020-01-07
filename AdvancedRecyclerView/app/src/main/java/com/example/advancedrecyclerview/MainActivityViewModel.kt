package com.example.advancedrecyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){
    private val colorRepository = ColorItemRepository()

    val colorItems = MutableLiveData<List<ColorItem>>().apply {
        value = colorRepository.getColorItems()
    }
}