package com.nvshink.shifttestcase.ui.user.screen.currentuser

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.net.toUri
import com.nvshink.shifttestcase.R

@Composable
fun ClickableEmail(
    email: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    val context = LocalContext.current
    Text(
        text = email,
        modifier = modifier
            .clickable {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:$email".toUri()
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                }
                try {
                    context.startActivity(intent)
                } catch (e: Error) {
                    Toast.makeText(context, R.string.error_email_app_not_found, Toast.LENGTH_SHORT)
                        .show()
                }
            },
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        style = style
    )
}

@Composable
fun ClickablePhone(
    phone: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    val context = LocalContext.current
    Text(
        text = phone,
        modifier = modifier
            .clickable {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:$phone".toUri()
                    putExtra(Intent.EXTRA_PHONE_NUMBER, arrayOf(phone))
                }
                try {
                    context.startActivity(intent)
                } catch (e: Error) {
                    Toast.makeText(context, R.string.error_email_app_not_found, Toast.LENGTH_SHORT)
                        .show()
                }
            },
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        style = style
    )
}

@Composable
fun ClickableAddress(
    address: String,
    latitude: Double,
    longitude: Double,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    val context = LocalContext.current
    Text(
        text = address,
        modifier = modifier
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = "geo:$latitude,$longitude?q=$latitude,$longitude($address)".toUri()
                }
                try {
                    context.startActivity(intent)
                } catch (e: Error) {
                    Toast.makeText(context, R.string.error_email_app_not_found, Toast.LENGTH_SHORT)
                        .show()
                }
            },
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        style = style
    )
}