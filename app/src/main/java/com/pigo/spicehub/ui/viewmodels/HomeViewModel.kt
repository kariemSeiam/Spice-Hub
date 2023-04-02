package com.pigo.spicehub.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pigo.spicehub.data.IndianFoodData
import com.pigo.spicehub.data.model.Recipe

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val indianFoodData = IndianFoodData.getIndianFoodList(application)

    fun getIndianFoods(): LiveData<List<Recipe>> = indianFoodData
}