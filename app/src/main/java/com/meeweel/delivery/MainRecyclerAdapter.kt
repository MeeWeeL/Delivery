package com.meeweel.delivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.delivery.databinding.MainMenuRecyclerItemBinding

class MainRecyclerAdapter :
    RecyclerView.Adapter<MainRecyclerAdapter.RecyclerItemViewHolder>() {

    private var data: List<String> = listOf()

    fun setData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            MainMenuRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: MainMenuRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.itemTitle.text = data
            }
        }
    }
}