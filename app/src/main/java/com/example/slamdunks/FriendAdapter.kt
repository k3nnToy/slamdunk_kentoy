package com.example.slamdunks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendAdapter(
    private val friends: List<SlambookEntry>,
    private val onFriendClicked: ((SlambookEntry) -> Unit)? = null,
    private val onFriendDeleted: ((Int) -> Unit)? = null,
    private val onFriendEdited: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    inner class FriendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fullNameTextView: TextView = view.findViewById(R.id.name)
        val deleteButton: ImageView = view.findViewById(R.id.delete)
        val editButton: ImageView = view.findViewById(R.id.edit)

        fun bind(friend: SlambookEntry, position: Int) {
            fullNameTextView.text = friend.fullname

            // Handle item click
            itemView.setOnClickListener {
                onFriendClicked?.invoke(friend)
            }

            // Handle delete button click
            deleteButton.setOnClickListener {
                onFriendDeleted?.invoke(position)
            }

            // Handle edit button click
            editButton.setOnClickListener {
                onFriendEdited?.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slambookitem, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(friends[position], position)
    }

    override fun getItemCount(): Int = friends.size
}
