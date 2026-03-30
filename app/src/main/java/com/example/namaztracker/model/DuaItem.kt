package com.example.namaztracker.model

data class DuaItem(
    val title: String,
    val arabic: String,
    val translation: String
)

object DuaData {

    val duaList = listOf(
        DuaItem(
            "Dua Before Sleeping",
            "اللَّهُمَّ بِاسْمِكَ أَمُوتُ وَأَحْيَا",
            "O Allah, in Your name I die and I live."
        ),
        DuaItem(
            "Dua After Waking Up",
            "الحمدُ للهِ الذي أحيانا بعد ما أماتنا وإليه النشور",
            "All praise is for Allah who gave us life after death."
        ),
        DuaItem(
            "Dua Before Eating",
            "بِسْمِ اللَّهِ",
            "In the name of Allah."
        ),
        DuaItem(
            "Dua After Eating",
            "الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مِنَ الْمُسْلِمِينَ",
            "Praise be to Allah who fed us and made us Muslims."
        ),
        DuaItem(
            "Dua When Entering Home",
            "بِسْمِ اللَّهِ وَلَجْنَا وَبِسْمِ اللَّهِ خَرَجْنَا وَعَلَى رَبِّنَا تَوَكَّلْنَا",
            "In the name of Allah we enter and leave, and upon our Lord we rely."
        ),
        DuaItem(
            "Dua When Leaving Home",
            "بِسْمِ اللَّهِ تَوَكَّلْتُ عَلَى اللَّهِ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ",
            "In the name of Allah, I trust in Allah."
        ),
        DuaItem(
            "Dua When Seeing Someone in Difficulty",
            "الحَمْدُ لِلَّهِ الَّذِي عَافَانِي مِمَّا ابْتَلاَكَ بِهِ",
            "Praise be to Allah who saved me from what He afflicted you with."
        ),
        DuaItem(
            "Dua For Forgiveness",
            "رَبِّ اغْفِرْ لِي وَتُبْ عَلَيَّ إِنَّكَ أَنْتَ التَّوَّابُ الرَّحِيمُ",
            "My Lord, forgive me and accept my repentance."
        ),
        DuaItem(
            "Dua For Parents",
            "رَّبِّ ارْحَمْهُمَا كَمَا رَبَّيَانِي صَغِيرًا",
            "My Lord, have mercy on them as they raised me."
        ),
        DuaItem(
            "Dua For Worry / Anxiety",
            "حَسْبُنَا اللَّهُ وَنِعْمَ الْوَكِيلُ",
            "Allah is sufficient for us."
        )
    )
}
