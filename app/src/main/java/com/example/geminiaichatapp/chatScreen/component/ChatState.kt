package com.example.geminiaichatapp.chatScreen.component

import android.graphics.Bitmap
import com.example.geminiaichatapp.chatScreen.data.ChatModel

data class ChatState(
    val chatList: MutableList<ChatModel> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)
