package com.example.namaztracker.model

data class NamazDetailModel(
    var name: String,
    var rakat: String,
    var timing: String,
    var details: String,
    var jamaatTime: String? = null,      // e.g. "05:30 AM (Masjid)"
    var isWajib: Boolean = false,         // Witr wajib hota hai
    var drawableRes: Int? = null          // Icon(optional)
)