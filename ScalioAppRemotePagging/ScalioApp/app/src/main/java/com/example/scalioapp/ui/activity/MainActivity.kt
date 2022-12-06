package com.example.scalioapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scalioapp.R
import com.example.scalioapp.ui.viewmodels.UsersViewModel
import com.example.scalioapp.listeners.OnSuccessListener
import com.example.scalioapp.model.User
import com.example.scalioapp.databinding.ActivityMainBinding
import com.example.scalioapp.ui.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var usersAdapter: UsersAdapter
    private lateinit var binding: ActivityMainBinding
    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()
        setAdapter()
        validateAndGetUsersList("Adnan")
        loadStateListener()
    }

    private fun loadStateListener() {
        usersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                showProgressBar()
            else {
                hideProgressBar()
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }

    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.searchResultsList.layoutManager = linearLayoutManager
    }

    private fun setAdapter() {
        binding.searchResultsList.adapter = usersAdapter
    }

    private fun validateAndGetUsersList(query: String) {
        getUsersList(query)
    }


    private fun getUsersList(query: String) {
        usersViewModel.getUsersList(query, object : OnSuccessListener {
            override fun onSuccess(list: LiveData<PagingData<User>>) {
                list.observe(this@MainActivity, Observer {
                    usersAdapter.submitData(lifecycle, it)
                })
            }
        })
    }


    private fun hideProgressBar() {
        binding.placeholderText.visibility = View.GONE
        binding.placeholder.visibility = View.INVISIBLE
        if (usersAdapter.itemCount == 0) {
            binding.placeholderText.visibility = View.VISIBLE
        }
    }

    private fun showProgressBar() {
        binding.placeholder.visibility = View.VISIBLE
    }
}