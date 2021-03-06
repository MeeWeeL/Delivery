package com.meeweel.delivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.ChipGroup
import com.meeweel.delivery.R
import com.meeweel.delivery.databinding.ActivityMainBinding
import com.meeweel.delivery.model.AppState
import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.utils.OnlineLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var isNetworkAvailable: Boolean = true

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var listener: ChipGroup.OnCheckedChangeListener

    private val adapter: MainRecyclerAdapter = MainRecyclerAdapter()
    private val observer = Observer<AppState> {
        renderData(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeToNetworkState()
        initViews()

        listener = initCategoryListener()
        binding.mainCategoriesChipGroup.setOnCheckedChangeListener(listener)
    }

    private fun initViews() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainRecyclerView.adapter = adapter
        val model: MainViewModel by viewModel()
        viewModel = model
        viewModel.getLiveData().observe(this, observer)
        viewModel.getData(isNetworkAvailable, DataModel.Form.PIZZA)
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

    private fun subscribeToNetworkState() {
        OnlineLiveData(this).observe(
            this,
            {
                isNetworkAvailable = it
                if (!isNetworkAvailable) {
                    Toast.makeText(this, "No internet", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    private fun initCategoryListener() = ChipGroup.OnCheckedChangeListener { _, checkedId ->
        when (checkedId) {
            R.id.main_recycler_pizza_category -> viewModel.getData(true, DataModel.Form.PIZZA)
            R.id.main_recycler_water_category -> viewModel.getData(true, DataModel.Form.WATER)
            R.id.main_recycler_desert_category -> viewModel.getData(true, DataModel.Form.DESSERT)
        }
    }
}