package com.nvshink.shifttestcase.ui.user.screen.list

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.nvshink.domain.user.model.UserModel

@Composable
fun UserCard(
    user: UserModel,
    onCardClick: () -> Unit
) {
    Card(onClick = onCardClick) {
        Row {
            AsyncImage(model = user.picture.thumbnail, contentDescription = null)
            Text("${user.name.first} ${user.name.last} ${user.username}")
        }
    }
}