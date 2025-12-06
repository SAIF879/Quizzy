package com.example.quizzy.ui.screen.login.composbales

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzy.R

import org.koin.androidx.compose.koinViewModel

@Composable
fun StyledTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isEnabled: Boolean = true
) {
    val isFocused = remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(hint, color = Color.Gray) }, // HINT instead of label
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused.value = it.isFocused },
        singleLine = true,
        enabled = isEnabled,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF3F4F6),
            unfocusedContainerColor = Color(0xFFF3F4F6),

            // BORDER COLOR RULES
            focusedIndicatorColor = Color.Black,        // black on focus
            unfocusedIndicatorColor = Color.Transparent, // invisible border

            // TEXT
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,

            // PLACEHOLDER
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray
        )
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun StyledTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    Column(Modifier.padding(16.dp)) {
        StyledTextField(
            value = text,
            onValueChange = { text = it },
            hint = "School ID"
        )
        Spacer(modifier = Modifier.height(16.dp))
        StyledTextField(
            value = "Filled text",
            onValueChange = { },
            hint = "Student ID"
        )
        Spacer(modifier = Modifier.height(16.dp))
        StyledTextField(
            value = "",
            onValueChange = { },
            hint = "Disabled",
            isEnabled = false
        )
    }
}
