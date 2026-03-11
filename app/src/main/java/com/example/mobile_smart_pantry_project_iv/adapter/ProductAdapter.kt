package com.example.mobile_smart_pantry_project_iv.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_smart_pantry_project_iv.adapter.ProductViewHolder
import com.example.mobile_smart_pantry_project_iv.R
import com.example.mobile_smart_pantry_project_iv.databinding.ItemProductBinding
import com.example.mobile_smart_pantry_project_iv.model.Product

class ProductAdapter(
    private val products: List<Product>,
    private val filterByCategory: String?,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    private val filteredProducts = if (filterByCategory != null) {
        products.filter { product -> product.category == filterByCategory }
    } else products

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val entry = filteredProducts[position]
        holder.bind(entry, onClick)

        holder.binding.nameTextview.text = entry.name
        holder.binding.categoryTextview.text = entry.category
        holder.binding.quantityTextview.text = "Quantity: ${if (entry.quantity < 0) 0 else entry.quantity}"

        fun setTextColors(){
            val colour = if (entry.quantity in 6..10) Color.YELLOW
            else if (entry.quantity <= 5) Color.RED
            else Color.WHITE
            holder.binding.nameTextview.setTextColor(colour)
            holder.binding.quantityTextview.setTextColor(colour)
        }
        setTextColors()

        val imageResId = when(entry.imageRef){
            "oxygen_tank.png" -> R.drawable.oxygen_tank
            "emergency_axe.png" -> R.drawable.emergency_axe
            "thermal_blanket.png" -> R.drawable.thermal_blanket
            "repair_toolkit.png" -> R.drawable.repair_toolkit
            "yerbamate_clean_lemon_lime.png" -> R.drawable.yerbamate_clean_lemon_lime
            "water_filter.png" -> R.drawable.water_filter
            "wrench.png" -> R.drawable.wrench
            "solar_battery_pack.png" -> R.drawable.solar_battery_pack
            "fruit_mix.png" -> R.drawable.fruit_mix
            "co2_scrubber_gun.png" -> R.drawable.co2_scrubber_gun
            else -> R.drawable.daddy_pig
        }
        holder.binding.productImageview.setImageResource(imageResId)


        holder.binding.addButton.setOnClickListener {
            entry.quantity++
            holder.binding.quantityTextview.text = "Quantity: ${entry.quantity}"
            setTextColors()
        }

        holder.binding.subtractButton.setOnClickListener {
            if (entry.quantity > 0) entry.quantity--
            holder.binding.quantityTextview.text = "Quantity: ${entry.quantity}"
            setTextColors()
        }
    }

    override fun getItemCount(): Int = filteredProducts.size
}