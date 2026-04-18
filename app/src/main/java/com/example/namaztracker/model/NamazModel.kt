package com.example.namaztracker.model

import com.example.namaztracker.R
import com.example.namaztracker.adapter.NamazStepModel
import java.io.Serializable

data class NamazModel(
    val date:String,
    val day:String
): Serializable

object NamazGuide{

    val namazSteps = listOf(
        NamazStepModel(
            "Step 1: Niyyah",
            "Make your intention to pray.",
            "1 point: Focus your heart on the specific prayer you are performing.",
            R.drawable.img1
        ),
        NamazStepModel(
            "Step 2: Takbiratul Ihram",
            "Raise your hands to your ears and say ‘Allahu Akbar’.",
            "1 point: This marks the official start of your prayer.",
            R.drawable.img2
        ),
        NamazStepModel(
            "Step 3: Qiyam (Position)",
            "Lower your hands and place them over your naval (right over left).",
            "1 point: Stand straight and maintain a humble posture.",
            R.drawable.img3
        ),
        NamazStepModel(
            "Step 4: Focus",
            "Keep your eyes focused on the ground in front of you.",
            "1 point: Concentration (Khushu) is essential for prayer.",
            null
        ),
        NamazStepModel(
            "Step 6: Ruku (Bowing)",
            "Bow down and place your hands on your knees.",
            "1 point: Keep your back straight and horizontal.",
            R.drawable.img4
        ),
        NamazStepModel(
            "Step 7: Rising from Ruku",
            "Return to standing up again.",
            "1 point: Say 'Sami Allahu Liman Hamidah' while rising.",
            null
        ),
        NamazStepModel(
            "Step 8: Sujud (Prostration)",
            "Go down to perform prostration.",
            "1 point: Ensure forehead, nose, and palms touch the ground.",
            R.drawable.img5
        ),
        NamazStepModel(
            "Step 9: Tasbih in Sujud",
            "Say ‘Subhana Rabbiyal A'la’ while in prostration.",
            "1 point: This is the position where a slave is closest to Allah.",
            R.drawable.img5
        ),
        NamazStepModel(
            "Step 10: Jalsa (Sitting)",
            "Rise up from sujud and sit for a moment.",
            "1 point: Sit calmly before going for the second prostration.",
            R.drawable.img6
        ),
        NamazStepModel(
            "Step 11: Second Sujud",
            "Return to the position of sujud.",
            "1 point: Repeat the prostration to complete the cycle.",
            R.drawable.img5
        ),
        NamazStepModel(
            "Step 12: Rising to Stand",
            "Arise from sujud to return to standing and say Allahu Akbar.",
            "1 point: This starts your next Rakah (unit) of prayer.",
            null
        ),
        NamazStepModel(
            "Step 13: Tashahhud",
            "Perform the Tashahhud at the end of every two rakah.",
            "1 point: Recite 'Attahiyat' while sitting on your left foot.",
            R.drawable.img6
        ),
        NamazStepModel(
            "Step 14: Salam (Right)",
            "Turn your face to the right shoulder and say 'Assalamu Alaikum wa Rahmatullah'.",
            "1 point: This signifies the end of the prayer for the right side.",
            R.drawable.img7
        ),
        NamazStepModel(
            "Step 15: Salam (Left)",
            "Turn your face to the left shoulder and say 'Assalamu Alaikum wa Rahmatullah'.",
            "1 point: This completes the entire prayer session.",
            R.drawable.img8
        )
    )

    val wuduSteps = listOf(
        NamazStepModel(
            "Step 1: Niyyah",
            "Make your intention to perform wudu.",
            "1 point: Intention should be in the heart to purify yourself for Allah.",
            null // R.drawable.wudu_niyyah
        ),
        NamazStepModel(
            "Step 2: Bismillah",
            "Say ‘Bismillah’ (In the name of Allah).",
            "1 point: Starting with Allah's name brings barakah in the act.",
            null
        ),
        NamazStepModel(
            "Step 3: Washing Hands",
            "Wash your hands up to the wrists three times.",
            "1 point: Make sure to clean between the fingers.",
            R.drawable.img13
        ),
        NamazStepModel(
            "Step 4: Rinsing Mouth",
            "Rinse your mouth three times.",
            "1 point: Use your right hand to put water into your mouth.",
            R.drawable.img14
        ),
        NamazStepModel(
            "Step 5: Cleaning Nose",
            "Sniff water into your nostrils and blow it out three times.",
            "1 point: Use the right hand to sniff and left hand to clean.",
            R.drawable.img15
        ),
        NamazStepModel(
            "Step 6: Washing Face",
            "Wash your entire face three times.",
            "1 point: From the forehead to the chin and ear to ear.",
            null
        ),
        NamazStepModel(
            "Step 7: Washing Arms",
            "Wash your right arm then left arm up to and including the elbows three times.",
            "1 point: Ensure no part of the arm remains dry.",
            R.drawable.img17
        ),
        NamazStepModel(
            "Step 8: Masah (Head)",
            "Wipe your wet hands over your head once.",
            "1 point: Start from the front to the back and then back to front.",
            null
        ),
        NamazStepModel(
            "Step 9: Cleaning Ears",
            "Wipe the inside and back of your ears once.",
            "1 point: Use your index fingers for the inside and thumbs for the back.",
            null
        ),
        NamazStepModel(
            "Step 10: Washing Feet",
            "Wash your right foot then left foot up to the ankles three times.",
            "1 point: Clean between the toes and include the ankles.",
            R.drawable.img110
        ),
        NamazStepModel(
            "Step 11: Shahada & Dua",
            "Recite the Shahada and the Dua after finishing Wudu.",
            "1 point: 'Ash-hadu alla ilaha illallah...' opens the gates of Jannah.",
            null
        )
    )

    val namazConditionsList = listOf(
        // --- KAB PARHI JATI HAI (Timings & Validity) ---
        NamazStepModel(
            "1. Prayer Timings",
            "Perform each prayer within its prescribed time (Fajr, Dhuhr, Asr, Maghrib, Isha).",
            "1 point: Namaz is not valid if performed before its time starts.",
            null
        ),
        NamazStepModel(
            "2. Forbidden Times",
            "Avoid praying during sunrise, sunset, and when the sun is at its zenith (Zawal).",
            "1 point: Only Sajda-e-Tilawat or urgent Janaza is allowed in some cases, but Nafl is haram.",
            null
        ),

        // --- KIYA KARNA CHAHIYE (Essential Do's) ---
        NamazStepModel(
            "3. Taharah (Purity)",
            "Ensure your body, clothes, and the place of prayer are clean.",
            "1 point: Perform Wudu or Ghusl (if needed) before standing for prayer.",
            null
        ),
        NamazStepModel(
            "4. Facing Qibla",
            "Face the direction of the Kaaba (Qibla) in Makkah.",
            "1 point: Facing the correct direction is a fundamental condition for validity.",
            null
        ),
        NamazStepModel(
            "5. Satar (Proper Dress)",
            "Cover the essential parts of the body (Satar).",
            "1 point: For men, naval to knees; for women, the whole body except face, hands, and feet.",
            null
        ),
        NamazStepModel(
            "6. Khushu (Concentration)",
            "Pray with a calm heart and focus on the meaning of what you recite.",
            "1 point: Imagine you are standing directly before Allah (SWT).",
            null
        ),

        // --- KIYA NAHI KARNA CHAHIYE (Strict Don'ts) ---
        NamazStepModel(
            "7. Moving Excessively",
            "Avoid unnecessary movements like scratching or fixing clothes repeatedly.",
            "1 point: 'Amal-e-Kaseer' (excessive movement) can break the Namaz.",
            null
        ),
        NamazStepModel(
            "8. Looking Around",
            "Do not look at the sky or look left and right during prayer.",
            "1 point: Your gaze should remain at the place of Sujud (prostration).",
            null
        ),
        NamazStepModel(
            "9. Eating or Drinking",
            "Do not swallow anything or chew while praying.",
            "1 point: Even a small particle swallowed intentionally breaks the prayer.",
            null
        ),
        NamazStepModel(
            "10. Laughing or Talking",
            "Do not speak to anyone or laugh out loud during the prayer.",
            "1 point: Laughing loudly enough for others to hear breaks both Namaz and Wudu.",
            null
        )
    )

    val prayerTypesList = listOf(
        // --- FARD PRAYERS (Obligatory) ---
        NamazStepModel(
            "1. Fard (Compulsory)",
            "The 5 daily prayers that are mandatory for every Muslim.",
            "1 point: Missing these is a major sin. They include Fajr (2), Dhuhr (4), Asr (4), Maghrib (3), and Isha (4).",
            null
        ),

        // --- SUNNAT PRAYERS (Prophetic Tradition) ---
        NamazStepModel(
            "2. Sunnat-e-Mu'akkadah",
            "Prayers the Prophet (PBUH) performed regularly and emphasized.",
            "1 point: Highly recommended to perform. Includes Sunnats before/after Fard prayers.",
            null
        ),
        NamazStepModel(
            "3. Sunnat-e-Ghair Mu'akkadah",
            "Prayers the Prophet (PBUH) performed occasionally.",
            "1 point: Reward for performing them, but no sin for leaving them (e.g., 4 before Asr).",
            null
        ),

        // --- WAJIB PRAYERS (Necessary) ---
        NamazStepModel(
            "4. Wajib (Essential)",
            "Prayers that are necessary, almost like Fard.",
            "1 point: Includes Witr (after Isha), Eid prayers, and Sajda-e-Sahw if required.",
            null
        ),

        // --- NAFL PRAYERS (Voluntary) ---
        NamazStepModel(
            "5. Nafl (Extra Credit)",
            "Optional prayers performed for extra rewards and closeness to Allah.",
            "1 point: Includes Ishraq, Chasht, Tahajjud, and Salat-ul-Hajat.",
            null
        ),

        // --- SPECIAL OCCASION PRAYERS ---
        NamazStepModel(
            "6. Salatul Jumu'ah (Friday)",
            "The congregational prayer performed on Friday instead of Dhuhr.",
            "1 point: Mandatory for men to pray in the Masjid behind an Imam.",
            null
        ),
        NamazStepModel(
            "7. Salatul Janaza (Funeral)",
            "A communal obligation (Fard-e-Kifayah) for the deceased.",
            "1 point: Performed standing with 4 Takbirs; there is no Ruku or Sujud.",
            null
        ),
        NamazStepModel(
            "8. Salatul Kusuf/Khusuf",
            "Prayers performed during a Solar or Lunar eclipse.",
            "1 point: Performed to seek Allah's mercy during natural signs.",
            null
        ),
        NamazStepModel(
            "9. Salatul Istikhara",
            "A prayer for seeking guidance from Allah when making a decision.",
            "1 point: Performed as 2 Rakat Nafl followed by a specific Dua.",
            null
        ),
        NamazStepModel(
            "10. Salatul Tasbih",
            "A special prayer of forgiveness containing 300 Tasbihs.",
            "1 point: Can be performed once a day, week, month, or even once in a lifetime.",
            null
        )
    )

    val prayerTypesDetailedList = listOf(
        NamazStepModel(
            "Fajr Prayer",
            "Performed at dawn before sunrise.",
            "Rakat: 2 Sunnat (Mu'akkadah) + 2 Fard. Total: 4.",
            null
        ),
        NamazStepModel(
            "Dhuhr Prayer",
            "Performed after the sun passes its zenith.",
            "Rakat: 4 Sunnat + 4 Fard + 2 Sunnat + 2 Nafl. Total: 12.",
            null
        ),
        NamazStepModel(
            "Asr Prayer",
            "Performed in the afternoon.",
            "Rakat: 4 Sunnat (Ghair Mu'akkadah) + 4 Fard. Total: 8.",
            null
        ),
        NamazStepModel(
            "Maghrib Prayer",
            "Performed just after sunset.",
            "Rakat: 3 Fard + 2 Sunnat + 2 Nafl. Total: 7.",
            null
        ),
        NamazStepModel(
            "Isha Prayer",
            "Performed at night.",
            "Rakat: 4 Sunnat + 4 Fard + 2 Sunnat + 2 Nafl + 3 Witr + 2 Nafl. Total: 17.",
            null
        ),
        NamazStepModel(
            "Tahajjud (Nafl)",
            "Performed in the last third of the night.",
            "Rakat: Minimum 2, maximum 12. Usually prayed in sets of 2.",
            null
        ),
        NamazStepModel(
            "Ishraq (Nafl)",
            "Performed 15-20 mins after sunrise.",
            "Rakat: 2 or 4 Rakat. Great reward for purification and patience.",
            null
        ),
        NamazStepModel(
            "Eid Prayers (Wajib)",
            "Performed on Eid-ul-Fitr and Eid-ul-Adha.",
            "Rakat: 2 Rakat with 6 additional Takbirs.",
            null
        )
    )

    val namazDuasStepByStep = listOf(
        NamazStepModel(
            "1. Thana (Starting Dua)",
            "Recited immediately after Takbiratul Ihram.",
            "Arabic: سُبْحَانَكَ اللَّهُمَّ وَبِحَمْدِكَ وَتَبَارَكَ اسْمُكَ وَتَعَالَى جَدُّكَ وَلَا إِلَهَ غَيْرُكَ\n\nPoint: This praises Allah's purity and greatness before starting recitation.",
            null
        ),
        NamazStepModel(
            "2. Ta'awwudh & Tasmiyah",
            "Seeking refuge from Shaytan and starting in Allah's name.",
            "Arabic: أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيمِ. بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\n\nPoint: Recited before Surah Al-Fatiha in the first Rakah.",
            null
        ),
        NamazStepModel(
            "3. Tasbih Ruku",
            "Recited while bowing down.",
            "Arabic: سُبْحَانَ رَبِّيَ الْعَظِيمِ\n\nPoint: Recite 3, 5, or 7 times. It means 'Glory be to my Lord, the Most Magnificent'.",
            null
        ),
        NamazStepModel(
            "4. Tasmi & Tahmid",
            "Recited while rising from Ruku and standing straight.",
            "Arabic: سَمِعَ اللَّهُ لِمَنْ حَمِدَهُ. رَبَّنَا وَلَكَ الْحَمْدُ\n\nPoint: It means 'Allah hears who praises Him' and 'Our Lord, to You belongs all praise'.",
            null
        ),
        NamazStepModel(
            "5. Tasbih Sujud",
            "Recited while in prostration (Sajdah).",
            "Arabic: سُبْحَانَ رَبِّيَ الأَعْلَى\n\nPoint: Recite 3, 5, or 7 times. It means 'Glory be to my Lord, the Most High'.",
            null
        ),
        NamazStepModel(
            "6. Tashahhud (At-Tahiyyat)",
            "Recited during the sitting position (Qa'dah).",
            "Arabic: التَّحِيَّاتُ لِلَّهِ وَالصَّلَوَاتُ وَالطَّيِّبَاتُ...\n\nPoint: Keep the index finger raised during the Shahada part.",
            null
        ),
        NamazStepModel(
            "7. Durood-e-Ibrahim",
            "Sending blessings upon the Prophet (PBUH) after Tashahhud.",
            "Arabic: اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ...\n\nPoint: This is mandatory in the final sitting of every prayer.",
            null
        ),
        NamazStepModel(
            "8. Dua-e-Masura",
            "The final prayer before performing Salam.",
            "Arabic: اللَّهُمَّ إِنِّي ظَلَمْتُ نَفْسِي ظُلْمًا كَثِيرًا...\n\nPoint: You can also recite 'Rabbana Atina fid-dunya...' here.",
            null
        )
    )
}