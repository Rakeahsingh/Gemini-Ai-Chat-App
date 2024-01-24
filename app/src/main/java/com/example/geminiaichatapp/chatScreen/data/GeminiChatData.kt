package com.example.geminiaichatapp.chatScreen.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.content

object GeminiChatData {

    private const val api_Key = "Enter Your Api Key"

    suspend fun getResponse(prompt: String): ChatModel {

        val harassmentSafety = SafetySetting(harmCategory = HarmCategory.HARASSMENT, threshold = BlockThreshold.ONLY_HIGH)
        val hateSpeechSafety = SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE)
        val sexualContent = SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE)

        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = api_Key,
            safetySettings = listOf(
                harassmentSafety, hateSpeechSafety, sexualContent
            )
        )

        return try {
            val response = generativeModel.generateContent(prompt)
            ChatModel(
                prompt = response.text ?: "error ",
                bitmap = null,
                isFromUser = false
            )

        }catch (e: Exception){
            ChatModel(
                prompt = e.message ?: "error ",
                bitmap = null,
                isFromUser = false
            )
        }

    }


    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap): ChatModel {

        val harassmentSafety = SafetySetting(harmCategory = HarmCategory.HARASSMENT, threshold = BlockThreshold.ONLY_HIGH)
        val hateSpeechSafety = SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE)
        val sexualContent = SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE)

        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = api_Key,
            safetySettings = listOf(
                harassmentSafety, hateSpeechSafety, sexualContent
            )
        )

        return try {

            val inputContent = content {
                image(bitmap)
                text(prompt)
            }

            val response = generativeModel.generateContent(inputContent)

             ChatModel(
                prompt = response.text ?: "error ",
                bitmap = null,
                isFromUser = false
            )

        }catch (e: Exception){
            ChatModel(
                prompt = e.message ?: "error ",
                bitmap = null,
                isFromUser = false
            )
        }

    }



}