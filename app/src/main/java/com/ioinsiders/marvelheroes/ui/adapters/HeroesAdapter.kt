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
import timber.log.Timber

class HeroesAdapter(
    private val context: Context,
    private val characters: List<Character>
): RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem.hashCode() == newItem.hashCode()
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
            binding.tvHeroName.text = character.name
            binding.tvHeroDescription.text =  if (character.description.isNotEmpty()) character.description
            else "${character.name} is a fictional character appearing in American comic books published by Marvel ..."

            val imageUrl = character.thumbnail.path + "." + character.thumbnail.extension
            binding.tvHeroImage.load(imageUrl)

        }
    }
}