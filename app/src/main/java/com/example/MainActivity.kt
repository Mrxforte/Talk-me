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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mandatory Step 1: Configure Firebase App Check with launch intent before any UI or ViewModels
        configureAppCheck(this, intent)

        // Step 2: Inflate XML UI using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        // Hide bottom navigation on full-screen sub-flows (Lesson Detail, Exercise Session, AI Tutors, Flip Cards, Settings)
        val topLevelDestinations = setOf(
            R.id.homeFragment,
            R.id.lessonsFragment,
            R.id.dailyTasksFragment,
            R.id.practiceFragment,
            R.id.profileFragment
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility =
                if (destination.id in topLevelDestinations) View.VISIBLE else View.GONE
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
}
