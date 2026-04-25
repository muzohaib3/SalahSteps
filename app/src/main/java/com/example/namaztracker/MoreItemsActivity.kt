package com.example.namaztracker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.namaztracker.R
import com.example.namaztracker.databinding.ActivityMoreItemsBinding
import com.example.namaztracker.logi

class MoreItemsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoreItemsBinding
    private var navController: NavController? = null

    companion object {
        private const val TAG = "MoreActivity"
        private const val FRAGMENT_TYPE_KEY = "key"
        private const val FRAGMENT_DATA_KEY = "data"

        // Default fragments mapping
        private val FRAGMENT_MAP = mapOf(
            "quran" to R.id.quranFragment,
            "qibla" to R.id.qiblaFinderFragment,
            "qibla_finder" to R.id.qiblaFinderFragment,
            "masnoon_duas" to R.id.masnoonDuaeinFragment,
            "masnoon_duaein" to R.id.masnoonDuaeinFragment,
            "namaz" to R.id.namazFragment,
            "namaz_info" to R.id.namazFragment,
            "hadith" to R.id.hadithFragment,
            "namaz_guide" to R.id.namazGuideFragment,
            "islamic_info" to R.id.islamicInfoFragment,
            "islamic_faq" to R.id.islamicInfoFragment
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            // Initialize NavController safely
            if (!initializeNavController()) {
                showError("Failed to initialize navigation")
                return
            }

            // Get fragment type from intent
            val fragmentType = intent.getStringExtra(FRAGMENT_TYPE_KEY)
            val fragmentData = intent.getBundleExtra(FRAGMENT_DATA_KEY)

            logi(TAG, "Fragment type: $fragmentType")

            // Load respective fragment
            if (!loadRespectiveFragment(fragmentType, fragmentData)) {
                showError("Failed to load fragment: $fragmentType")
                loadDefaultFragment()
            }
        } catch (e: Exception) {
            logi(TAG, "Critical error in onCreate: ${e.message}")
            e.printStackTrace()
            showError("Initialization error: ${e.message}")
            loadDefaultFragment()
        }
    }

    /**
     * Initialize NavController with error handling
     */
    private fun initializeNavController(): Boolean {
        return try {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment

            if (navHostFragment == null) {
                logi(TAG, "NavHostFragment is null")
                return false
            }

            navController = navHostFragment.navController

            if (navController == null) {
                logi(TAG, "NavController is null")
                return false
            }

            logi(TAG, "NavController initialized successfully")
            true
        } catch (e: ClassCastException) {
            logi(TAG, "Error casting NavHostFragment: ${e.message}")
            false
        } catch (e: Exception) {
            logi(TAG, "Error initializing NavController: ${e.message}")
            false
        }
    }

    /**
     * Load fragment based on type using Navigation Graph
     */
    private fun loadRespectiveFragment(type: String?, bundle: Bundle? = null): Boolean {
        return try {
            // Validate NavController
            if (navController == null) {
                logi(TAG, "NavController is null, cannot navigate")
                return false
            }

            if (type.isNullOrBlank()) {
                logi(TAG, "Fragment type is null or blank")
                return false
            }

            // Get fragment ID from map
            val fragmentId = FRAGMENT_MAP[type.lowercase()]

            if (fragmentId == null) {
                logi(TAG, "Fragment type not found: $type")
                return false
            }

            // Navigate to fragment
            val navBundle = bundle ?: Bundle()
            navController?.navigate(fragmentId, navBundle)

            logi(TAG, "Successfully navigated to fragment: $type (ID: $fragmentId)")
            true
        } catch (e: IllegalArgumentException) {
            logi(TAG, "Invalid fragment destination: ${e.message}")
            false
        } catch (e: Exception) {
            logi(TAG, "Error loading fragment: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    /**
     * Load default fragment (Namaz)
     */
    private fun loadDefaultFragment() {
        try {
            if (navController != null) {
                navController?.navigate(R.id.namazFragment)
                logi(TAG, "Loaded default fragment: Namaz")
            }
        } catch (e: Exception) {
            logi(TAG, "Error loading default fragment: ${e.message}")
        }
    }

    /**
     * Show error message
     */
    private fun showError(message: String) {
        try {
            logi(TAG, "ERROR: $message")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Handle back navigation safely
     */
    override fun onSupportNavigateUp(): Boolean {
        return try {
            navController?.navigateUp() ?: false || super.onSupportNavigateUp()
        } catch (e: Exception) {
            logi(TAG, "Error in onSupportNavigateUp: ${e.message}")
            super.onSupportNavigateUp()
        }
    }

    override fun onBackPressed() {
        try {
            if (navController != null && navController?.navigateUp() == false) {
                super.onBackPressed()
            } else if (navController == null) {
                super.onBackPressed()
            }
        } catch (e: Exception) {
            logi(TAG, "Error in onBackPressed: ${e.message}")
            super.onBackPressed()
        }
    }

    /**
     * Handle pause state
     */
    override fun onPause() {
        super.onPause()
        try {
            logi(TAG, "Activity paused")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Handle resume state
     */
    override fun onResume() {
        super.onResume()
        try {
            logi(TAG, "Activity resumed")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Handle destroy state
     */
    override fun onDestroy() {
        super.onDestroy()
        try {
            navController = null
            logi(TAG, "Activity destroyed, NavController cleared")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}