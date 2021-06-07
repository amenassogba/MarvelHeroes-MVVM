package com.ioinsiders.marvelheroes.ui.activities


import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ioinsiders.marvelheroes.R
import com.ioinsiders.marvelheroes.databinding.ActivityMainBinding
import com.ioinsiders.marvelheroes.models.DataStateEvent
import com.ioinsiders.marvelheroes.ui.adapters.HeroesAdapter
import com.ioinsiders.marvelheroes.viewmodels.HeroesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: HeroesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: HeroesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.i("Did create Mainactivity")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val wic = WindowInsetsControllerCompat(window, window.decorView)
            wic.isAppearanceLightStatusBars = true
            window.statusBarColor = getColor(R.color.marvelWhite)
        }

        setupTabLayout()
        setupRecycleView()
        collectStateFlow()

    }


    private fun setupTabLayout() {
        binding.tabLayout.apply {
            viewModel.tabTitles.forEach { addTab(newTab().setText(it)) }
        }
    }

    private fun setupRecycleView() {
        adapter = HeroesAdapter(this)
        binding.apply {
            heroesRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            heroesRecyclerView.adapter = adapter
        }
    }

    private fun collectStateFlow() {
        lifecycleScope.launch {
            viewModel.charactersStateFlow.collect { state ->
                Timber.i(state.toString())
                when(state) {
                    is DataStateEvent.Success -> adapter.submitList(state.data)
                    is DataStateEvent.Loading -> Unit // showProgress
                    is DataStateEvent.Failure -> {
                        Toast.makeText(this@MainActivity, state.reason, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }
}