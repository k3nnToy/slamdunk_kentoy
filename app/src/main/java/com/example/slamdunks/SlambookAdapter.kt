package com.example.slamdunks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slamdunks.databinding.SlambookitemBinding

class SlambookAdapter(
    private var avatarList: MutableList<SlambookEntry>,  // List of all user entries
    private var filteredList: MutableList<SlambookEntry> = mutableListOf(),  // Filtered list to show in RecyclerView
    private val onAvatarClick: (SlambookEntry) -> Unit  // Callback function when avatar is clicked
) : RecyclerView.Adapter<SlambookAdapter.AvatarViewHolder>() {

    // ViewHolder that binds the data for each avatar item
    inner class AvatarViewHolder(private val binding: SlambookitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: SlambookEntry) {
            binding.name.text = user.fullname  // Bind the user's name to the TextView
            // Assuming you may want to set an image or avatar
            // Glide.with(itemView.context).load(user.avatar).into(binding.avatarImage) // Uncomment if needed

            binding.root.setOnClickListener {
                onAvatarClick(user)  // Trigger the callback when the avatar is clicked
            }
        }
    }

    // Inflates the item layout and returns the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val binding =
            SlambookitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AvatarViewHolder(binding)
    }

    // Binds data to the ViewHolder at the given position
    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.bind(filteredList[position])  // Bind the user at the current position
    }

    // Returns the size of the filtered list
    override fun getItemCount(): Int = filteredList.size

    // Updates the list and refreshes the RecyclerView
    fun updateList(newList: List<SlambookEntry>) {
        avatarList.clear()  // Clear the original list to avoid duplicating data
        avatarList.addAll(newList)  // Add the new data to the original list
        filteredList = newList.toMutableList()  // Update the filtered list
        notifyDataSetChanged()  // Notify the adapter that the data has changed
    }

    // Method to filter the list by a query
    fun filterList(query: String) {
        filteredList = if (query.isEmpty()) {
            avatarList  // If the query is empty, show all items
        } else {
            avatarList.filter { it.fullname.contains(query, ignoreCase = true) }
                .toMutableList()  // Filter the list based on the query
        }
        notifyDataSetChanged()  // Notify the adapter that the filtered list has been updated
    }
}
