package com.example.scalioapp.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.scalioapp.model.User
import com.example.scalioapp.databinding.ListItemUserBinding

class UserItemViewHolder (private val listItemClassifiedBinding: ListItemUserBinding) :
    RecyclerView.ViewHolder(listItemClassifiedBinding.root) {

    fun bind(user: User) {
        listItemClassifiedBinding.login.text = "$adapterPosition ${user.login}"
        listItemClassifiedBinding.type.text = user.type
        listItemClassifiedBinding.image.setImageURI(user.avatar_url)

    }
}