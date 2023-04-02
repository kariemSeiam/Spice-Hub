package com.pigo.spicehub.data.model

data class Recipe(
    val recipeId: Int,
    val translatedRecipeName: String,
    val translatedIngredients: String,
    val totalTimeInMins: String,
    val cuisine: String,
    val translatedInstructions: String,
    val url: String,
    val cleanedIngredients: String,
    val imageUrl: String,
    val ingredientCount: String
)