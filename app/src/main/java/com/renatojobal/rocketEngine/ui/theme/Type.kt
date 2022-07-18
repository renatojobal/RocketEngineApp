package com.renatojobal.rocketEngine.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.renatojobal.rocketEngine.R

// Set of Material typography styles to start with

val Comfortaa = FontFamily(
    Font(R.font.comfortaa),
    Font(R.font.comfortaa_bold, FontWeight.Bold),
    Font(R.font.comfortaa_light, FontWeight.Light)
)

val Typography = Typography(
    defaultFontFamily = Comfortaa

)