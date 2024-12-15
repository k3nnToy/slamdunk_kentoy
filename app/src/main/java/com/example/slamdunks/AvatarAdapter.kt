package com.example.slamdunks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slamdunks.databinding.ItemAvatarBinding

class AvatarAdapter(
    private val avatarList: List<Avatar>,
    private val onAvatarClick: (Avatar) -> Unit
) : RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {

    inner class AvatarViewHolder(private val binding: ItemAvatarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(avatar: Avatar) {
            binding.avatarImageView.setImageResource(avatar.imageResId)
            binding.avatarNameTextView.text = avatar.username
            binding.root.setOnClickListener {
                onAvatarClick(avatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val binding = ItemAvatarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AvatarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        val avatar = avatarList[position]
        holder.bind(avatar)
    }

    override fun getItemCount(): Int = avatarList.size
}
