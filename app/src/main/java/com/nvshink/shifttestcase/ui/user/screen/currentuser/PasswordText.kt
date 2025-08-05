package com.nvshink.shifttestcase.ui.user.screen.currentuser

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nvshink.shifttestcase.R

@Composable
fun PasswordText(
    password: String
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Row {
        BasicTextField(
            value = password,
            onValueChange = {},
            readOnly = true,
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    innerTextField()
                }
            }
        )
        IconButton(
            onClick = { isPasswordVisible = !isPasswordVisible },
        ) {
            Icon(
                imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff
                else Icons.Default.Visibility,
                contentDescription = stringResource(R.string.icon_toggle_password_visibility)
            )
        }
        IconButton(
            onClick = {
                val clip = ClipData.newPlainText("label", password)
                ClipboardManager::class.java
                    .cast(context.getSystemService(Context.CLIPBOARD_SERVICE))
                    ?.setPrimaryClip(clip)

                Toast.makeText(context, R.string.button_password_copy_toast,  Toast.LENGTH_SHORT).show()
            },
        ) {
            Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = stringResource(R.string.icon_toggle_password_visibility)
            )
        }
    }
}