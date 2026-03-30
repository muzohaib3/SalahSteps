package com.example.namaztracker.model

data class FaqResponse(
    val faqs: List<IslamicFaqModel>
)

data class IslamicFaqModel(
    val question: String,
    val answer: String
)

object IslamicFAQ{

    val islamicFaqList = listOf(

        IslamicFaqModel(
            "What is Islam?",
            "Islam is a monotheistic religion that teaches belief in one God (Allah) and following the guidance revealed to the final Prophet Muhammad (ﷺ)."
        ),

        IslamicFaqModel(
            "Who is Allah?",
            "Allah is the one and only God, the Creator of the universe, all-powerful and merciful. Muslims worship only Allah without any partners."
        ),

        IslamicFaqModel(
            "Who was Prophet Muhammad (ﷺ)?",
            "Prophet Muhammad (ﷺ) is the last messenger sent by Allah to guide humanity. His teachings, called Sunnah, are followed by Muslims worldwide."
        ),

        IslamicFaqModel(
            "What is the Holy Quran?",
            "The Quran is the literal word of Allah revealed to Prophet Muhammad (ﷺ). It is the main source of Islamic guidance."
        ),

        IslamicFaqModel(
            "What are the Five Pillars of Islam?",
            "The Five Pillars are: Shahadah (faith), Salah (prayer), Sawm (fasting), Zakat (charity), and Hajj (pilgrimage to Makkah)."
        ),

        IslamicFaqModel(
            "Why do Muslims pray five times a day?",
            "Salah is a direct connection with Allah. Praying five times daily purifies the heart, brings discipline, and reminds Muslims of their purpose."
        ),

        IslamicFaqModel(
            "What is the purpose of fasting in Ramadan?",
            "Fasting teaches self-control, gratitude, compassion for the less fortunate, and strengthens one's faith and closeness to Allah."
        ),

        IslamicFaqModel(
            "What is Zakat?",
            "Zakat is an obligatory charity given to the poor (2.5% of savings). It purifies wealth and helps reduce poverty in society."
        ),

        IslamicFaqModel(
            "What happens after death in Islam?",
            "Islam teaches that after death, every soul enters Barzakh (a waiting period). On the Day of Judgment, everyone will be resurrected and held accountable."
        ),

        IslamicFaqModel(
            "What is Halal and Haram?",
            "Halal means permissible and allowed by Islamic law. Haram means prohibited. Muslims must eat, earn, and live in a halal way."
        )
    )

}
