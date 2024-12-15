package com.example.slamdunks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SlambookAdapter(
    private val slambookList: MutableList<SlambookEntry>,  // Use MutableList to modify the data
    private val onAvatarClick: (SlambookEntry) -> Unit,
    private val onEditClick: (SlambookEntry) -> Unit,
    private val onDeleteClick: (SlambookEntry) -> Unit
) : RecyclerView.Adapter<SlambookAdapter.SlambookViewHolder>() {

    // ViewHolder to hold views for each item
    inner class SlambookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileImageView: ImageView = view.findViewById(R.id.profileImageView)
        val fullNameTextView: TextView = view.findViewById(R.id.fullNameTextView)
        val editButton: ImageButton = view.findViewById(R.id.edit)
        val deleteButton: ImageButton = view.findViewById(R.id.delete)

        init {
            // Set the avatar click listener
            profileImageView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onAvatarClick(slambookList[position])
                }
            }

            // Set the edit button click listener
            editButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onEditClick(slambookList[position])
                }
            }

            // Set the delete button click listener
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClick(slambookList[position])
                }
            }
        }
    }

    // Inflate the layout and return the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlambookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slambook_item, parent, false)
        return SlambookViewHolder(view)
    }

    // Bind the data to the views in the ViewHolder
    override fun onBindViewHolder(holder: SlambookViewHolder, position: Int) {
        val slambookEntry = slambookList[position]
        holder.fullNameTextView.text = slambookEntry.fullname
        Glide.with(holder.itemView.context)
            .load(slambookEntry.avatarId) // Assuming `avatarId` is a drawable resource ID
            .into(holder.profileImageView)
    }

    // Return the size of the dataset
    override fun getItemCount(): Int = slambookList.size

    // Update the list when necessary (useful for delete operations)
    fun updateList(newList: List<SlambookEntry>) {
        // You could use a more efficient update method (e.g., DiffUtil) here if needed.
        slambookList.clear()  // Clear the existing list
        slambookList.addAll(newList)  // Add new items
        notifyDataSetChanged()  // Notify adapter that data has changed
    }

    // Handle item removal efficiently by notifying only the item removed
    fun removeItem(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            slambookList.removeAt(position)
            notifyItemRemoved(position) // Only notify the item that was removed
        }
    }

    // Handle item insertion efficiently by notifying only the item added
    fun addItem(position: Int, item: SlambookEntry) {
        slambookList.add(position, item)
        notifyItemInserted(position) // Notify item inserted
    }
}
