package com.meeweel.delivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.meeweel.delivery.databinding.ActivityMainBinding
import com.meeweel.delivery.model.AppState

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter: MainRecyclerAdapter = MainRecyclerAdapter()
    private val observer = Observer<AppState> {
        renderData(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainRecyclerView.adapter = adapter
        viewModel.getData().observe(this, observer)
        viewModel.findList()
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