package com.pigo.spicehub.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pigo.spicehub.data.model.Recipe
import com.pigo.spicehub.databinding.RowHomeBinding

class HomeAdapter(private val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var list = listOf<Recipe>()
    private var temp = 0

    fun setList(list: List<Recipe>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowHomeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = 5

    inner class ViewHolder(private val binding: RowHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recipe) {
            with(binding) {
                val adapter = RecipeCardMidAdapter()
                // bind the data to the views here
                adapter.setList(list)
                when (temp) {
                    0 -> {
                        tvTitleHomeCard.text = "Recent Recips"
                        temp++
                    }
                    1 -> {
                        tvTitleHomeCard.text = "Your Recips"
                        temp++
                    }
                    2 -> {
                        tvTitleHomeCard.text = "Your Bookmark"
                        temp++
                    }
                    else -> temp = 0
                }
                recyclerRecipesHomeCard.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


                recyclerRecipesHomeCard.adapter = adapter
            }
        }
    }
}

data class RowHomeCard(
    val titleCategory: String, val model: List<Recipe>
)
