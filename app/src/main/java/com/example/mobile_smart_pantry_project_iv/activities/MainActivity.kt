package com.example.mobile_smart_pantry_project_iv.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_smart_pantry_project_iv.activities.EditProductActivity
import com.example.mobile_smart_pantry_project_iv.R
import com.example.mobile_smart_pantry_project_iv.adapter.ProductAdapter
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityMainBinding
import com.example.mobile_smart_pantry_project_iv.model.Product
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()
    private val categorySet = mutableSetOf<String>()
    private var selectedCategory: String? = null
    private var searchViewText: String? = null

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

        loadListAdapter()
        loadFromJson()
        updateCategories()

        binding.saveToJsonButton.setOnClickListener { saveToJson() }
        binding.productSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewText = newText?.takeIf { it.isNotBlank() }
                loadListAdapter()
                return true
            }
        })
    }

    private fun loadListAdapter(){
        listAdapter = ProductAdapter(productList, selectedCategory, searchViewText) { product ->
            val intent = Intent(this, EditProductActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }
        binding.spaceItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.spaceItemsRecyclerView.adapter = listAdapter
    }

    private fun updateCategories() {
        categorySet.clear()
        categorySet.add("All")
        productList.forEach { categorySet.add(it.category) }

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categorySet.toList()
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = spinnerAdapter
        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = parent?.getItemAtPosition(position) as String
                if (selectedCategory == "All") selectedCategory = null
                loadListAdapter()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
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

            Toast.makeText(this, "Loaded products from JSON", Toast.LENGTH_SHORT)
                .show()
        } catch (ex: Exception) {
            Log.e("loadFromJson() exception", "$ex")
            ex.printStackTrace()
        }
    }

    private fun saveToJson(){
        try {
            val json = Json { prettyPrint = true }
            val jsonString = json.encodeToString(productList)

            val file = File(filesDir, "pantry.json")
            file.writeText(jsonString)

            Toast.makeText(this, "Saved ${productList.size} products to JSON", Toast.LENGTH_SHORT)
                .show()
        } catch (ex: Exception){
            Log.e("saveToJson() exception", "$ex")
            ex.printStackTrace()
        }
    }
}