package com.ioinsiders.marvelheroes.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ioinsiders.marvelheroes.databinding.ItemHeroBinding
import com.ioinsiders.marvelheroes.helpers.load
import com.ioinsiders.marvelheroes.models.Character

class HeroesAdapter(
    private val context: Context
): RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return  oldItem.id == newItem.id
        }
    }

    private val characterDiffer = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<Character>) = characterDiffer.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemHeroBinding.inflate(inflater, parent, false)
        return HeroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val character =  characterDiffer.currentList[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characterDiffer.currentList.size
    }

    inner class HeroesViewHolder(private val binding: ItemHeroBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                tvHeroName.text = character.name
                tvHeroDescription.text =  if (character.description.isNotEmpty()) character.description
                else "${character.name} is a fictional character appearing in American comic books published by Marvel ..."
                tvHeroImage.load(character.thumbnail.url)
            }

        }
    }
}