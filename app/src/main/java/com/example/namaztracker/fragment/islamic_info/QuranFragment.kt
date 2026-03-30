package com.example.namaztracker.fragment.islamic_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.namaztracker.databinding.FragmentQuranBinding
import com.example.namaztracker.loge
import com.example.namaztracker.popFragment


class QuranFragment : Fragment() {

    private var _binding: FragmentQuranBinding? = null
    private val binding get() = _binding!!
    private val TAG = this::class.simpleName
    private lateinit var webView: WebView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuranBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun initViews(){

        webView = binding.llWebView
        webView.webViewClient = WebViewClient()
        binding.btBack.setOnClickListener { popFragment(requireFragmentManager()) }
        binding.tvToolbarHeader.text = "Quran Kareem"
        openWebView()
    }

    private fun openWebView() {
        try {
            webView.settings.javaScriptEnabled = true;
            webView.loadUrl("https://quran.com/");
        }catch (e:Exception){
            loge("$TAG","${e.message}")
        }
    }

    inner class WebViewClient : android.webkit.WebViewClient() {

        // Load the URL
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        // ProgressBar will disappear once page is loaded
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar.visibility = View.GONE
        }
    }

}