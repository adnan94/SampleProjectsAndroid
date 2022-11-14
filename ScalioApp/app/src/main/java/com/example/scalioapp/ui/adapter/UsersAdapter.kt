package com.example.scalioapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.scalioapp.model.User
import com.example.scalioapp.databinding.ListItemUserBinding
import com.example.scalioapp.ui.viewholder.UserItemViewHolder
import javax.inject.Inject

class UsersAdapter @Inject constructor() : PagingDataAdapter<User, UserItemViewHolder>(UsersComparator) {

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemUserBinding.inflate(inflater, parent, false)
        return UserItemViewHolder(binding)
    }

    object UsersComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.login == newItem.login
        }
    }
}

