package com.example.cyoapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HeroAdapter(private val heroes: List<Hero>) :
    RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivHero: ImageView = itemView.findViewById(R.id.ivHero)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hero, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = heroes[position]
        holder.tvName.text = hero.name
        holder.tvStatus.text = hero.status
        Glide.with(holder.itemView.context).load(hero.imageUrl).into(holder.ivHero)
    }

    override fun getItemCount(): Int = heroes.size
}
