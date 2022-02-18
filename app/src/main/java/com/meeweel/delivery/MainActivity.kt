package com.meeweel.delivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.meeweel.delivery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter: MainRecyclerAdapter = MainRecyclerAdapter()
    private val observer = Observer<List<String>> {
        adapter.setData(it)
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

}