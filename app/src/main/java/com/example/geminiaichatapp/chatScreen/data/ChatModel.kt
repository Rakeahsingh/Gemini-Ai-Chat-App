package com.example.geminiaichatapp.chatScreen.data

import android.graphics.Bitmap

data class ChatModel(
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean
)
