package com.pigo.spicehub.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pigo.spicehub.data.model.Recipe
import com.pigo.spicehub.databinding.FragmentHomeBinding
import com.pigo.spicehub.ui.adapters.HomeAdapter
import com.pigo.spicehub.ui.viewmodels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val adapter = HomeAdapter(requireContext())
        viewModel.getIndianFoods().observe(viewLifecycleOwner) {
            adapter.setList(it)
            binding.recyclerHome.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
/*
            binding.recyclerview.adapter = adapter
*/
            binding.recyclerHome.adapter = adapter

            val cuisineNames = it.distinctBy { it.totalTimeInMins }
                .joinToString("\n") { it.totalTimeInMins }

            Log.e("Cuisines", cuisineNames)
            Log.e("A7aaaaa",
                it.filter { it.cuisine == "Continental" }
                    .joinToString("\n") { it.ingredientCount })
        }
    }

    fun countVirginRecipes(recipes: List<Recipe>): Pair<Int, Int> {
        // Use the partition function to split the list into two based on the isVirginRecipe function
        val (virginRecipes, nonVirginRecipes) = recipes.partition { isVirginRecipe(it) }

        // Use the count function to get the number of recipes in each partition
        val numVirginRecipes = virginRecipes.count()
        val numNonVirginRecipes = nonVirginRecipes.count()

        // Return the counts as a pair of integers
        return Pair(numVirginRecipes, numNonVirginRecipes)
    }


    fun isVirginRecipe(recipe: Recipe): Boolean {
        // Split the translatedIngredients string into individual ingredients
        val ingredients = recipe.translatedIngredients.split("\n")

        // Check each ingredient for alcoholic content
        for (ingredient in ingredients) {
            if (isAlcoholicIngredient(ingredient)) {
                return false
            }
        }

        // If no alcoholic ingredients were found, the recipe is considered virgin
        return true
    }

    // Helper function to check if an ingredient is alcoholic
    fun isAlcoholicIngredient(ingredient: String): Boolean {
        val alcoholicIngredients = listOf(
            "gin",
            "vodka",
            "whiskey",
            "rum",
            "tequila",
            "vermouth",
            "brandy",
            "liqueur",
            "amaretto",
            "bitters",
            "campari",
            "cointreau",
            "curacao",
            "chartreuse",
            "absinthe",
            "sambuca",
            "pisco",
            "aperol",
            "ouzo",
            "sake",
            "kahlua",
            "baileys",
            "malibu",
            "midori",
            "creme de menthe"
        )

        // Check if any of the alcoholic ingredients are in the ingredient name
        for (alcoholicIngredient in alcoholicIngredients) {
            if (ingredient.contains(alcoholicIngredient, ignoreCase = true)) {
                return true
            }
        }

        // If no alcoholic ingredients were found, the ingredient is not considered alcoholic
        return false
    }

}