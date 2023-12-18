package com.ieti.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ieti.myapplication.MainActivityBinding
import com.ieti.myapplication.Movie
import com.ieti.myapplication.MoviesResponse
import  com.ieti.myapplication.OmdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var binding: MainActivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        binding.searchButton.setOnClickListener { v ->
            val searchQuery: String = binding.searchQueryText.getText().toString()
            searchMovie(searchQuery)
        }
        binding.recyclerView.setLayoutManager(LinearLayoutManager(this))
        binding.recyclerView.setAdapter(MoviesAdapter(ArrayList()))
    }

    private fun searchMovie(searchQuery: String) {
        val service: OmdbApi =
            RetrofitClient.getRetrofitInstance().create(OmdbApi::class.java)
        val call: Call<MoviesResponse> = service.searchMovies(searchQuery, "a2935151")
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesSearchResponseDto>
            ) {
                if (response.isSuccessful()) {
                    val movieDto: MoviesResponse? = response.body()
                    val movies: List<Movie> = response.body().getSearch()
                    if (movies != null) {
                        val adapter = MoviesAdapter(movies)
                        binding.recyclerView.setAdapter(adapter)
                        Log.d("MainActivity", "Movie: $movieDto")
                    }
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("MainActivity", "Error: " + t.message, t)
            }
        })
    }
}