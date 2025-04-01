package com.picpay.desafio.android.presentation.users

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.diogogr85.desafio_picpay.R
import com.diogogr85.desafio_picpay.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.users.adapters.UserListAdapter
import com.picpay.desafio.android.presentation.users.state.UsersState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: UserListAdapter by inject()
    private val viewModel: UsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        viewModel.usersState.observe(this) { state ->
            when(state) {
                is UsersState.Loading -> {
                    updateViewsVisibility(progressBarVisibility = VISIBLE)
                }
                is UsersState.Success -> {
                    updateViewsVisibility()
                    adapter.update(state.users)
                }
                is UsersState.Error -> {
                    updateViewsVisibility(recyclerViewVisibility = GONE)
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
                else -> {
                    updateViewsVisibility(recyclerViewVisibility = GONE)
                    Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateViewsVisibility(
        progressBarVisibility: Int = GONE,
        recyclerViewVisibility: Int = VISIBLE
    ) {
        with(binding) {
            userListProgressBar.visibility = progressBarVisibility
            recyclerView.visibility = recyclerViewVisibility
        }
    }
}
