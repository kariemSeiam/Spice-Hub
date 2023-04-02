package com.pigo.spicehub.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opencsv.CSVReader
import com.pigo.spicehub.data.model.Recipe
import com.pigo.spicehub.util.ColumnIndex
import java.io.InputStreamReader

object IndianFoodData {
    
    private const val CSV_FILE_NAME = "indian_food_v3.csv"
    private const val DEFAULT_VALUE = ""
    fun getIndianFoodList(context: Context): LiveData<List<Recipe>> {
        val indianFoods = MutableLiveData<List<Recipe>>()
        val csvReader = CSVReader(InputStreamReader(context.assets.open(CSV_FILE_NAME)))
        csvReader.use { reader ->
            var idCounter = 0 // Declare and initialize the id counter
            indianFoods.postValue(reader.readAll().map { nextLine ->
                createRecipeFromCSVLine(nextLine, idCounter++).also {
                    idCounter++
                }
            })
        }
        csvReader.close()
        return indianFoods
    }

    private fun createRecipeFromCSVLine(nextLine: Array<String>, idCounter: Int): Recipe {
        return Recipe(
            recipeId = idCounter,
            translatedRecipeName = nextLine.getOrNull(ColumnIndex.TRANSLATED_RECIPE_NAME)
                ?: DEFAULT_VALUE,
            translatedIngredients = nextLine.getOrNull(ColumnIndex.TRANSLATED_INGREDIENTS)
                ?: DEFAULT_VALUE,
            totalTimeInMins = nextLine.getOrNull(ColumnIndex.TOTAL_TIME_IN_MINS) ?: DEFAULT_VALUE,
            cuisine = nextLine.getOrNull(ColumnIndex.CUISINE) ?: DEFAULT_VALUE,
            translatedInstructions = nextLine.getOrNull(ColumnIndex.TRANSLATED_INSTRUCTIONS)
                ?: DEFAULT_VALUE,
            url = nextLine.getOrNull(ColumnIndex.URL) ?: DEFAULT_VALUE,
            cleanedIngredients = nextLine.getOrNull(ColumnIndex.CLEANED_INGREDIENTS)
                ?: DEFAULT_VALUE,
            imageUrl = nextLine.getOrNull(ColumnIndex.IMAGE_URL) ?: DEFAULT_VALUE,
            ingredientCount = nextLine.getOrNull(ColumnIndex.INGREDIENT_COUNT) ?: DEFAULT_VALUE
        )
    }
}