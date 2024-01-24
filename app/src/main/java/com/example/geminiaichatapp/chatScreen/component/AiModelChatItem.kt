package com.example.geminiaichatapp.chatScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModelChatItem(
    response: String,
) {

    Column(
        modifier = Modifier.padding(
            end = 100.dp, bottom = 22.dp
        )
    ) {

        Text(
            text = response,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp),
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 15.sp
        )
    }

}