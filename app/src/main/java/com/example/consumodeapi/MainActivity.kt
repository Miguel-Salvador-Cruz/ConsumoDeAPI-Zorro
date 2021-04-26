package com.example.consumodeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumodeapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, CoroutineScope, androidx.appcompat.widget.SearchView.OnQueryTextListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ApiAdapter
    private val apiImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svApi.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ApiAdapter(apiImages)
        binding.rvApi.layoutManager = LinearLayoutManager(this)
        binding.rvApi.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomfox.ca/floof/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch{
            val call = getRetrofit().create(APIService::class.java).getApiByBreeds("")
            val puppies = call.body()
            runOnUiThread{
                if(call.isSuccessful) {
                    val images = puppies?.image ?: emptyList()
                    apiImages.clear()
                    apiImages.addAll(images)
                    adapter.notifyDataSetChanged()
                }else {
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")
}