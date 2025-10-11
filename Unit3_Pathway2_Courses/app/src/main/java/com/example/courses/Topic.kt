package com.example.courses

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    @DrawableRes val imageRes: Int,
    val availableCourses: Int
)