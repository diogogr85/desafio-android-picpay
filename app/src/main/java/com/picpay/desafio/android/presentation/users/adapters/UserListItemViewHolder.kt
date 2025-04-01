package com.picpay.desafio.android.presentation.users.adapters

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.diogogr85.desafio_picpay.R
import com.diogogr85.desafio_picpay.databinding.ListItemUserBinding
import com.picpay.desafio.android.data.models.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            name.text = user.name
            username.text = user.username
            progressBar.visibility = VISIBLE
            Picasso.get()
                .load(user.profileImage)
                .error(R.drawable.ic_round_account_circle)
                .into(picture, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = GONE
                    }

                    override fun onError(e: Exception?) {
                        progressBar.visibility = GONE
                    }
                })
        }
    }
}