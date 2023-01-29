package com.example.party_lojo_game.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.party_lojo_game.R
import com.example.party_lojo_game.utils.findUserResource
import com.example.party_lojo_game.utils.gone
import com.example.party_lojo_game.utils.show

class ConfigImageAddImageAdapter(
    var actualImage: String,
    private val onImageSelected: (String) -> Unit
) : ListAdapter<String,
        ConfigImageAddImageAdapter.ConfigImageAddImageAdapterHolder>(ImageComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfigImageAddImageAdapterHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_config_player_object_image, parent, false)
        return ConfigImageAddImageAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: ConfigImageAddImageAdapterHolder, position: Int) {
        val image: String = getItem(position)
        holder.bind(image, actualImage, onImageSelected)
    }

    class ConfigImageAddImageAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagePreview: ImageView =
            itemView.findViewById(R.id.row_config_player_object_image)
        private val imageSelected: ImageView =
            itemView.findViewById(R.id.row_config_player_object_selected)

        fun bind(
            image: String,
            actualImage: String,
            onImageSelected: (String) -> Unit
        ) {
            imagePreview.setImageDrawable(itemView.context.findUserResource(image))
            if (image == actualImage) {
                imageSelected.show()
            } else {
                imageSelected.gone()
            }
            imagePreview.setOnClickListener { onImageSelected(image) }
        }
    }

    class ImageComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
