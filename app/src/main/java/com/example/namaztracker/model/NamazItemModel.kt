package com.example.namaztracker.model

import com.example.namaztracker.R

class NamazItemModel{

    var name:String = ""
    var time:String = ""
    var drawableItem:Int = 0

    fun getNamazList():List<NamazItemModel>{

        val namazArray = ArrayList<NamazItemModel>()

        namazArray.add(
            NamazItemModel().apply {
                name = "Fajr"
                time = "5AM - 6AM"
                drawableItem = R.drawable.dwan
            }
        )
        namazArray.add(
            NamazItemModel().apply {
                name = "Zuhr"
                time = "1PM - 3PM"
                drawableItem = R.drawable.sun
            }
        )
        namazArray.add(
            NamazItemModel().apply {
                name = "Asr"
                time = "4:30PM - 6PM"
                drawableItem = R.drawable.dusk
            }
        )
        namazArray.add(
            NamazItemModel().apply {
                name = "Maghrib"
                time = "6:30PM - 7PM"
                drawableItem = R.drawable.dusk
            }
        )
        namazArray.add(
            NamazItemModel().apply {
                name = "Isha"
                time = "7:30PM - 10:30PM"
                drawableItem = R.drawable.moon
            }
        )

        return namazArray
    }

    fun getNamazTypeList(): List<NamazDetailModel> {
        return listOf(
            NamazDetailModel(
                name = "Fajr",
                rakat = "2 Sunnat + 2 Farz",
                timing = "04:45 AM - 06:15 AM",
                details = "Subah sadiq se suraj nikalne se pehle tak. 2 rakat sunnat muakkadah aur 2 farz hain. Bohat ahmiyat wali namaz.",
                drawableRes = R.drawable.namaz // icon
            ),
            NamazDetailModel(
                name = "Zuhr",
                rakat = "4 Sunnat + 4 Farz + 2 Sunnat + 2 Nafl",
                timing = "12:45 PM - 04:30 PM",
                details = "Dopahar ke waqt. 4 sunnat muakkadah, 4 farz, 2 sunnat muakkadah aur 2 nafl padhe ja sakte hain.",
                drawableRes = R.drawable.namaz
            ),
            NamazDetailModel(
                name = "Asr",
                rakat = "4 Farz (4 Sunnat ghair-muakkadah bhi padhi ja sakti hain)",
                timing = "04:45 PM - 06:15 PM",
                details = "Shaam se pehle padhi jati hai. Hanafi mazhab mein farz 4 rakat hain.",
                drawableRes = R.drawable.namaz
            ),
            NamazDetailModel(
                name = "Maghrib",
                rakat = "3 Farz + 2 Sunnat + 2 Nafl",
                timing = "06:30 PM - 07:45 PM",
                details = "Suraj doobne ke foran baad. 3 farz, 2 sunnat muakkadah aur 2 nafl.",
                drawableRes = R.drawable.namaz
            ),
            NamazDetailModel(
                name = "Isha",
                rakat = "4 Sunnat + 4 Farz + 2 Sunnat + 2 Nafl + 3 Witr",
                timing = "07:50 PM - 04:30 AM",
                details = "Raat ki namaz. 3 witr wajib hain. Sunnat muakkadah aur nafl bhi padhe ja sakte hain.",
                drawableRes = R.drawable.namaz
            )
        )
    }
}
