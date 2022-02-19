package com.meeweel.delivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.meeweel.delivery.R
import com.meeweel.delivery.databinding.ActivityMainBinding
import com.meeweel.delivery.model.AppState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val adapter: MainRecyclerAdapter = MainRecyclerAdapter()
    private val observer = Observer<AppState> {
        renderData(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        binding.navBar.menu.findItem(R.id.bottom_menu_item_menu).setOnMenuItemClickListener {
            viewModel.getData(false)
            return@setOnMenuItemClickListener true
        }
    }

    private fun initViews() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainRecyclerView.adapter = adapter
        val model: MainViewModel by viewModel()
        viewModel = model
        viewModel.getLiveData().observe(this, observer)
        viewModel.getData(true)
    }

    private fun renderData(data: AppState) = when (data) {
        is AppState.Success -> {
            val list = data.data
            binding.progressBar.visibility = View.GONE
            adapter.setData(list)
        }
        is AppState.Loading -> {
            binding.progressBar.visibility = View.VISIBLE
        }
        is AppState.Error -> {
            binding.progressBar.visibility = View.GONE

        }
    }
}