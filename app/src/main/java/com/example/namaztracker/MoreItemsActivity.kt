package com.example.namaztracker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.namaztracker.R
import com.example.namaztracker.databinding.ActivityMoreItemsBinding
import com.example.namaztracker.fragment.islamic_info.HadithFragment
import com.example.namaztracker.fragment.islamic_info.IslamicInfoFragment
import com.example.namaztracker.fragment.islamic_info.MasnoonDuaeinFragment
import com.example.namaztracker.fragment.islamic_info.QiblaFinderFragment
import com.example.namaztracker.fragment.islamic_info.QuranFragment
import com.example.namaztracker.fragment.namaz.NamazFragment
import kotlin.compareTo
import kotlin.text.replace

class MoreItemsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoreItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val key = intent.getStringExtra("key") ?: "namaz"
            val fragment = getFragmentByKey(key)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    private fun getFragmentByKey(key: String): Fragment {
        return when (key.lowercase()) {
            "quran" -> QuranFragment()
            "qibla", "qibla_finder" -> QiblaFinderFragment()
            "masnoon_duas", "masnoon_duaein" -> MasnoonDuaeinFragment()
            "hadith" -> HadithFragment()
            "islamic_info", "islamic_faq" -> IslamicInfoFragment()
            "namaz" -> NamazFragment()
            else -> NamazFragment() // default
        }
    }

    // Back button handling
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}