package com.example.mobile_smart_pantry_project_iv.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobile_smart_pantry_project_iv.R
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityEditProductBinding
import com.example.mobile_smart_pantry_project_iv.model.Product

class EditProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val product = intent.getSerializableExtra("product") as? Product ?: return
        binding.editName.setText(product.name)
        binding.editQuantity.setText(product.quantity.toString())
        binding.editCategory.setText(product.category)
        binding.editImageRef.setText(product.imageRef)

        binding.saveButton.setOnClickListener {
            val updatedProduct = product.copy(
                name = binding.editName.text.toString(),
                quantity = binding.editQuantity.text.toString().toIntOrNull() ?: 0,
                category = binding.editCategory.text.toString(),
                imageRef = binding.editImageRef.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("updatedProduct", updatedProduct)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}