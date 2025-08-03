package com.nvshink.shifttestcase.ui.user.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nvshink.shifttestcase.ui.user.event.UserEvent
import com.nvshink.shifttestcase.ui.user.state.UserUiState
import com.nvshink.shifttestcase.ui.utils.ContentType

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    userUiState: UserUiState,
    onUserEvent: (UserEvent) -> Unit,
    contentType: ContentType,
    innerPadding: PaddingValues
) {
    Box(modifier = modifier){
        when (userUiState) {
            is UserUiState.LoadingState -> {
                CircularProgressIndicator()
            }

            is UserUiState.SuccessState -> {
                LazyColumn {
                    items(userUiState.userList) {
                        Text(text = "${it.name.first} ${it.name.last}")
                    }
                }
            }

            is UserUiState.ErrorState -> {
                Text(userUiState.error?.message ?: "error")
            }
        }
    }

}