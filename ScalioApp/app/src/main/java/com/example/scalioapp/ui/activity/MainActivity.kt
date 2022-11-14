package com.example.scalioapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
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
import com.example.scalioapp.source.UsersRepositoryImpl
import com.example.scalioapp.ui.adapter.UsersAdapter
import com.example.scalioapp.utils.ApplicationUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var usersAdapter: UsersAdapter
    private lateinit var binding: ActivityMainBinding
    private val usersViewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()
        setAdapter()
        clickListener()
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

    private fun clickListener() {
        binding.searchButton.setOnClickListener(this)
        binding.searchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val query = binding.searchView.text.toString()
                validateAndGetUsersList(query)
                true
            }
            false
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
        if (query.isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_text_to_search), Toast.LENGTH_SHORT)
                .show()
        } else if (!ApplicationUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, getString(R.string.internet_not_available), Toast.LENGTH_SHORT)
                .show()
        } else {
            getUsersList(query)
        }
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
        if(usersAdapter.itemCount == 0){
            binding.placeholderText.visibility = View.VISIBLE
        }
    }

    private fun showProgressBar() {
        binding.placeholder.visibility = View.VISIBLE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_button -> {
                val query = binding.searchView.text.toString()
                validateAndGetUsersList(query)
            }
        }
    }

}