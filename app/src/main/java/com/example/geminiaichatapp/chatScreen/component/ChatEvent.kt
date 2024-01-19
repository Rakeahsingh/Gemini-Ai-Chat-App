package com.example.geminiaichatapp.chatScreen.component

import android.graphics.Bitmap

sealed class ChatEvent {

    data class UpdateChat(val newPrompt: String): ChatEvent()

    data class SendChat(val prompt: String, val bitmap: Bitmap?): ChatEvent()

}