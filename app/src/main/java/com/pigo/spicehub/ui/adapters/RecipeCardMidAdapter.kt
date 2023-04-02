package com.pigo.spicehub.ui.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pigo.spicehub.R
import com.pigo.spicehub.data.model.Recipe
import com.pigo.spicehub.databinding.RowRecipeMidBinding

class RecipeCardMidAdapter : RecyclerView.Adapter<RecipeCardMidAdapter.ViewHolder>() {

    private var list = listOf<Recipe>()
    var a = 0
    fun setList(list: List<Recipe>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRecipeMidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position + 1])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: RowRecipeMidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {

            with(binding) {
                // bind the data to the views here
                tvRecipeNameMidCard.text = recipe.translatedRecipeName
                Log.e("test", a.toString())
                tvRecipeCuasine.text = recipe.cuisine + " Cuisine"
                binding.ivBookmarkRecipeMidCard.setOnClickListener {
                    when (a) {
                        0 -> {
                            a++
                            Glide.with(cardMid.context).load(R.drawable.ic_bookmark_on)
                                .into(ivBookmarkRecipeMidCard)
                        }
                        else -> {
                            a--
                            Glide.with(cardMid.context).load(R.drawable.ic_bookmark_off)
                                .into(ivBookmarkRecipeMidCard)
                        }
                    }
                }
                when (a) {
                    0 -> {
                        a++
                        Glide.with(cardMid.context).load(R.drawable.ic_bookmark_on)
                            .into(ivBookmarkRecipeMidCard)
                    }
                    else -> {
                        a--
                        Glide.with(cardMid.context).load(R.drawable.ic_bookmark_off)
                            .into(ivBookmarkRecipeMidCard)
                    }
                }
                // Use Glide to load the image from the URL
                Glide.with(cardMid.context).load(recipe.imageUrl).into(ivRecipeImageMidCard)


            }
        }
    }
}

