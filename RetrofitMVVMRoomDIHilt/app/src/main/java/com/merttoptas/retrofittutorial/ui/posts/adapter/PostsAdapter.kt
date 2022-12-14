package com.merttoptas.retrofittutorial.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merttoptas.retrofittutorial.data.model.Post
import com.merttoptas.retrofittutorial.data.model.PostDTO
import com.merttoptas.retrofittutorial.databinding.ItemPostLayoutBinding

class PostsAdapter(private val listener: OnPostClickListener) : ListAdapter<PostDTO, PostsAdapter.PostViewHolder>(PostsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       return PostViewHolder(
            ItemPostLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), listener, this.currentList)
    }

    class PostViewHolder(private val binding: ItemPostLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostDTO, listener: OnPostClickListener, postDTOList: List<PostDTO>) {
            binding.dataHolder = post

            binding.ivPostImage.setOnClickListener {

                postDTOList.map {
                    if(it.id == post.id){
                        it.isFavorite = !it.isFavorite
                        binding.dataHolder = it
                    }
                }

                listener.onPostClick(post, true)

            }
            binding.cardView.setOnClickListener {
                listener.onPostClick(post, false)
            }
            binding.executePendingBindings()
        }
    }

    class PostsDiffUtil : DiffUtil.ItemCallback<PostDTO>() {
        override fun areItemsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnPostClickListener {
    fun onPostClick(post: PostDTO, favorite: Boolean)
}
