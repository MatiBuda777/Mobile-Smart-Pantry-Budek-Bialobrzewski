package com.example.mobile_smart_pantry_project_iv

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityMainBinding
import com.example.mobile_smart_pantry_project_iv.model.Product
import kotlinx.serialization.json.Json
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listAdapter = ProductAdapter(productList)
        binding.spaceItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.spaceItemsRecyclerView.adapter = listAdapter

        loadFromJson()
    }

    private fun loadFromJson(){
        try{
            val inputStream = resources.openRawResource(R.raw.pantry)
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            if (jsonString.isEmpty() || jsonString.isBlank()){
                Log.e("JSON", "Plik pantry.json jest pusty!")
                return
            }

            val json = Json { ignoreUnknownKeys = true }
            val loadedList = json.decodeFromString<List<Product>>(jsonString)

            productList.clear()
            productList.addAll(loadedList)

            listAdapter.notifyDataSetChanged()

            Toast.makeText(this, "Loaded products from JSON", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            Toast.makeText(this, "Load Exception", Toast.LENGTH_SHORT).show()
            Log.e("loadFromJson() exception", "$ex")
            ex.printStackTrace()
        }
    }

    private fun saveToJson(){
        try {
            //save to JSON
        } catch (ex: Exception){
            Toast.makeText(this, "Save Exception", Toast.LENGTH_SHORT).show()
            Log.e("saveToJson() exception", "$ex")
            ex.printStackTrace()
        }
    }
}