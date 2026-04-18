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
}