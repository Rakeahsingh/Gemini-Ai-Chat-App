package com.example.geminiaichatapp.chatScreen

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminiaichatapp.chatScreen.component.ChatEvent
import com.example.geminiaichatapp.chatScreen.component.ChatState
import com.example.geminiaichatapp.chatScreen.data.ChatModel
import com.example.geminiaichatapp.chatScreen.data.GeminiChatData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {

    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    fun onEvent(event: ChatEvent){
        when(event){
            is ChatEvent.SendChat -> {
                if (event.prompt.isNotEmpty()){
                    addChat(
                        prompt = event.prompt,
                        bitmap = event.bitmap
                    )

                    if (event.bitmap != null){
                        getResponseWithImage(event.prompt, event.bitmap)
                    }else{
                        getResponse(event.prompt)
                    }
                }
            }
            is ChatEvent.UpdateChat -> {
                _state.update {
                    it.copy(prompt = event.newPrompt)
                }
            }
        }
    }


    private fun addChat(prompt: String, bitmap: Bitmap?){
        _state.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, ChatModel(prompt, bitmap, true))
                },
                prompt = "",
                bitmap = null
            )
        }
    }


    private fun getResponse(prompt: String){
        viewModelScope.launch {
            val chat = GeminiChatData.getResponse(prompt)

            _state.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                    }
                )
            }
        }
    }

    private fun getResponseWithImage(prompt: String, bitmap: Bitmap){
        viewModelScope.launch {
            val chat = GeminiChatData.getResponseWithImage(prompt, bitmap)

            _state.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                    }
                )
            }
        }
    }

}