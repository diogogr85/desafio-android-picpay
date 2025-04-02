package com.picpay.desafio.android.presentation.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diogogr85.desafio_picpay.databinding.ListItemUserBinding
import com.picpay.desafio.android.data.models.User

class UserListAdapter(usersList: List<User> = emptyList()) : RecyclerView.Adapter<UserListItemViewHolder>() {

    private var users = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val itemUserBinding = ListItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return UserListItemViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun update(usersList: List<User>) {
        users = usersList
    }
}