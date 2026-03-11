package com.example.mobile_smart_pantry_project_iv.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_smart_pantry_project_iv.databinding.ItemProductBinding
import com.example.mobile_smart_pantry_project_iv.model.Product

class ProductViewHolder(
    val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(item: Product, onClick: (Product) -> Unit ) {
        binding.root.setOnClickListener { onClick(item) }
    }
}