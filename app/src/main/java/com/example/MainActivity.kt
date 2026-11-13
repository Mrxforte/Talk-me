package com.example

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.ai.configureAppCheck
import com.example.databinding.ActivityMainBinding
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.UserPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Single Activity host for the TalkMe application.
 * Configures Firebase App Check on launch before UI inflation, manages BottomNavigationView
 * across the 4 primary destinations (Home, Lessons, Practice, Profile), and observes theme preferences.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var learningRepository: LearningRepository

    @Inject
    lateinit var preferencesRepository: UserPreferencesRepository

    private lateinit var binding: ActivityMainBinding
    private var latestTopInset: Int = 0
    private var latestBottomInset: Int = 0
    private var isBottomNavVisible: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mandatory Step 1: Configure Firebase App Check with launch intent before any UI or ViewModels
        configureAppCheck(this, intent)

        // Step 2: Enable edge-to-edge system bar layout
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Step 3: Inflate XML UI using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Step 4: Apply status bar (top), navigation bar (bottom), and IME insets cleanly
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            latestTopInset = systemBars.top
            latestBottomInset = systemBars.bottom
            applyRootAndBottomBarInsets()
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        // Hide bottom navigation on full-screen sub-flows
        val topLevelDestinations = setOf(
            R.id.homeFragment,
            R.id.lessonsFragment,
            R.id.dailyTasksFragment,
            R.id.practiceFragment,
            R.id.profileFragment
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            isBottomNavVisible = destination.id in topLevelDestinations
            binding.bottomNavigation.visibility =
                if (isBottomNavVisible) View.VISIBLE else View.GONE
            applyRootAndBottomBarInsets()
        }

        lifecycleScope.launch {
            learningRepository.ensureSeeded()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                preferencesRepository.userPreferencesFlow
                    .map { it.themeMode }
                    .distinctUntilChanged()
                    .collect { mode ->
                        val nightMode = when (mode) {
                            "LIGHT" -> AppCompatDelegate.MODE_NIGHT_NO
                            "DARK" -> AppCompatDelegate.MODE_NIGHT_YES
                            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        }
                        if (AppCompatDelegate.getDefaultNightMode() != nightMode) {
                            AppCompatDelegate.setDefaultNightMode(nightMode)
                        }
                    }
            }
        }
    }

    private fun applyRootAndBottomBarInsets() {
        binding.root.setPadding(
            0,
            latestTopInset,
            0,
            if (isBottomNavVisible) 0 else latestBottomInset
        )
        binding.bottomNavigation.setPadding(0, 0, 0, latestBottomInset)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        return navHostFragment?.navController?.navigateUp() == true || super.onSupportNavigateUp()
    }
}
