package com.example.news.utils

import com.example.news.R
import com.example.news.models.Category


val categories = listOf<Category>(
    Category(
        name = "Technology",
        imageResourceID = R.drawable.technology
    ),
    Category(
        name = "Science",
        imageResourceID = R.drawable.science
    ),
    Category(
        name = "Health",
        imageResourceID = R.drawable.health
    ),
    Category(
        name = "Business",
        imageResourceID = R.drawable.business
    ),
    Category(
        name = "Entertainment",
        imageResourceID = R.drawable.entertainment
    ),
    Category(
        name = "Sports",
        imageResourceID = R.drawable.sports
    )
)