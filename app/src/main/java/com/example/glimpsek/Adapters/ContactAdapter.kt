package com.example.glimpsek.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.glimpsek.R
import com.example.glimpsek.model.Users
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(private val context: Context, private val contacts: List<Users>)
    : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private val TAG = "ContactAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false))
    }

    // Returns the total count of items in the list
    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val contact = contacts[position]
        holder.bind(contact)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: Users) {
            itemView.tvName.text = contact.name
            itemView.tvAge.text = "Age: ${contact.age}"
            Glide.with(context).load(contact.imageUrl).into(itemView.ivProfile)
        }
    }
}