package com.example.namaztracker.model

data class HadithResponse(
    val hadiths: List<HadithModel>
)

data class HadithModel(
    var arabic:String,
    var hadith:String,
    var reference:String,
)

object Hadith{

    val hadithList = listOf(

        HadithModel(
            "إِنَّمَا الأَعْمَالُ بِالنِّيَّاتِ",
            "Actions are judged by intentions.",
            "Sahih Bukhari 1"
        ),

        HadithModel(
            "الدِّينُ يُسْرٌ",
            "The religion (Islam) is easy.",
            "Sahih Bukhari 39"
        ),

        HadithModel(
            "لَا يُؤْمِنُ أَحَدُكُمْ حَتَّى يُحِبَّ لأَخِيهِ مَا يُحِبُّ لِنَفْسِهِ",
            "None of you truly believes until he loves for his brother what he loves for himself.",
            "Sahih Bukhari 13"
        ),

        HadithModel(
            "مَنْ سَلَكَ طَرِيقًا يَلْتَمِسُ فِيهِ عِلْمًا، سَهَّلَ اللَّهُ لَهُ بِهِ طَرِيقًا إِلَى الْجَنَّةِ",
            "Whoever travels a path seeking knowledge, Allah will make the path to Jannah easy for him.",
            "Sahih Muslim 2699"
        ),

        HadithModel(
            "الرَّاحِمُونَ يَرْحَمُهُمُ الرَّحْمَٰنُ",
            "The merciful will be shown mercy by the Most Merciful.",
            "Sunan al-Tirmidhi 1924 (Sahih)"
        ),

        HadithModel(
            "الْمُسْلِمُ مَنْ سَلِمَ الْمُسْلِمُونَ مِنْ لِسَانِهِ وَيَدِهِ",
            "A Muslim is one from whose tongue and hands other Muslims are safe.",
            "Sahih Bukhari 10"
        ),

        HadithModel(
            "الطُّهُورُ شَطْرُ الإِيمَانِ",
            "Purification is half of faith.",
            "Sahih Muslim 223"
        ),

        HadithModel(
            "الصَّدَقَةُ تُطْفِئُ الْخَطِيئَةَ"
            ,
            "Charity extinguishes sins.",
            "Sunan al-Tirmidhi 2616 (Sahih)"
        ),

        HadithModel(
            "مَنْ لَا يَرْحَمْ لَا يُرْحَمْ",
            "Whoever does not show mercy will not be shown mercy.",
            "Sahih Bukhari 6013"
        ),

        HadithModel(
            "الدُّعَاءُ هُوَ الْعِبَادَةُ",
            "Dua (supplication) is worship.",
            "Sunan al-Tirmidhi 2969 (Sahih)"
        )
    )
}
