package edu.iesam.simpsons.features.simpsons.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import edu.iesam.simpsons.R
import edu.iesam.simpsons.databinding.CharacterItemLayoutBinding
import edu.iesam.simpsons.features.simpsons.domain.Character

class SimpsonsAdapter : RecyclerView.Adapter<SimpsonsAdapter.CharacterViewHolder>() {

    private var characters: List<Character> = mutableListOf()

    fun submitList(newCharacters: List<Character>) {
        characters = newCharacters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item_layout, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CharacterItemLayoutBinding.bind(view)

        fun bind(character: Character) {
            binding.ciTvName.text = character.name
            binding.ciTvOccupation.text = character.occupation
            binding.ciIvPhoto.load(character.imageUrl)
        }
    }
}