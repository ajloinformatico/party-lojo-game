package com.example.party_lojo_game.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.party_lojo_game.R
import com.example.party_lojo_game.utils.getImage

class ConfigImageAddImageAdapter(listener: ConfigImageSelectedImage,
     var actualImage: String): ListAdapter<String,
     ConfigImageAddImageAdapter.ConfigImageAddImageAdapterHolder> (ImageComparator()){

    interface ConfigImageSelectedImage {
        fun onItemSelect(image: String)
    }

    private val listenerConfigImageSelectedImage: ConfigImageSelectedImage = listener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfigImageAddImageAdapterHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_config_player_object_image, parent, false)
        return ConfigImageAddImageAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: ConfigImageAddImageAdapterHolder, position: Int) {
        val image: String = getItem(position)
        holder.bind(image, actualImage, listenerConfigImageSelectedImage)
    }

    class ConfigImageAddImageAdapterHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imagePreview: ImageView = itemView.findViewById(R.id.row_config_player_object_image)
        private val imageSelected: ImageView = itemView.findViewById(R.id.row_config_player_object_selected)

        fun bind(image: String, actualImage: String, listenerConfigImageSelectedImage: ConfigImageSelectedImage) {
            imagePreview.setImageDrawable(getImage(image, itemView.context))
            if (image == actualImage) {
                imageSelected.visibility = View.VISIBLE
            } else {
                imageSelected.visibility = View.GONE
            }
            imagePreview.setOnClickListener { listenerConfigImageSelectedImage.onItemSelect(image) }
        }
    }

    class ImageComparator: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}