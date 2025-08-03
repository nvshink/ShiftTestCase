package com.nvshink.shifttestcase.ui.user.screen.currentuser

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.nvshink.domain.user.model.UserModel

@Composable
fun CurrentUserScreen(
    user: UserModel?,
    onBackPressed: () -> Unit
) {
    BackHandler {
        onBackPressed()
    }
    if(user == null) {
        CurrentUserEmptyScreen()
    } else {
        Column {
            AsyncImage(user.picture.large, contentDescription = null)
        }
    }
}