package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.news.databinding.ActivityMainBinding
import com.example.news.ui.CategoryFragment
import com.example.news.ui.HomeFragment
import com.example.news.ui.SavedNewsFragment
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer,HomeFragment())
                commit()
            }
        }

        binding.bottomNavigationView.setOnItemSelectedListener(itemSelectedListener())

    }

    override fun onStart() {

        super.onStart()
    }

    inner class itemSelectedListener : NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val transaction = supportFragmentManager.beginTransaction()
            val id = item.itemId
            when(id){
                R.id.home -> {
                    transaction.replace(R.id.fragmentContainer,HomeFragment()).commit()
                }
                R.id.categories -> {
                    transaction.replace(R.id.fragmentContainer,CategoryFragment()).commit()
                }
                R.id.saved -> {
                    transaction.replace(R.id.fragmentContainer, SavedNewsFragment()).commit()
                }
            }
            return true
        }

    }

}