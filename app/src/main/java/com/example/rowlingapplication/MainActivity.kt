package com.example.rowlingapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rowlingapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: RowlingViewModel by viewModels()

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

        viewModel.fetchBooks()

        // Observe the LiveData from the ViewModel
        viewModel.books.observe(this) { result ->
            if (result.isSuccess) {
                val books = result.getOrNull()
                // Display the book titles
                binding.response.text = books?.joinToString("\n") { it.title } ?: "No books available"
            } else {
                val exception = result.exceptionOrNull()
                // Display the error message
                if (exception != null) {
                    Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}