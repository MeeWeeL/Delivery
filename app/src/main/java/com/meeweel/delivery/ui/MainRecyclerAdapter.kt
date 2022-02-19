package com.meeweel.delivery.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meeweel.delivery.R
import com.meeweel.delivery.databinding.MainMenuRecyclerItemBinding
import com.meeweel.delivery.model.entities.DataModel

class MainRecyclerAdapter :
    RecyclerView.Adapter<MainRecyclerAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = listOf()

    fun setData(data: List<DataModel>?) {
        if (data != null) {
            this.data = data
        } else {
            this.data = listOf()
        }
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

        @SuppressLint("SetTextI18n")
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.itemTitle.text = data.title
                binding.itemDescription.text = data.description
                binding.itemBuyButton.text = "от ${data.price} р"
                Glide.with(this.binding.itemPicture.context)
                    .load(data.picture)
                    .error(R.drawable.ic_no_internet)
                    .placeholder(R.drawable.ic_loading)
                    .into(this.binding.itemPicture)
            }
        }
    }
}