package com.wordpress.lonelytripblog.ui

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wordpress.lonelytripblog.R
import com.wordpress.lonelytripblog.data.Recipe

class RecipeListAdapter(private val items: List<Recipe>) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListAdapter.RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view.findViewById(R.id.recipe_title), view.findViewById(R.id.food_image), view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecipeListAdapter.RecipeViewHolder, position: Int) {
        val recipe = items[position]
        if (recipe.image_url.isNotEmpty()) {
            Picasso.get().load(recipe.image_url).into(holder.imageView)
        }
        holder.textView.text =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(recipe.title, FROM_HTML_MODE_LEGACY).toString()
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(recipe.title).toString()
        }
    }

    class RecipeViewHolder(val textView: TextView, val imageView: ImageView, itemView: View)
        : RecyclerView.ViewHolder(itemView)
}