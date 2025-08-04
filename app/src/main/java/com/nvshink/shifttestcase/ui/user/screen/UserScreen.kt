package com.nvshink.shifttestcase.ui.user.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nvshink.shifttestcase.R
import com.nvshink.shifttestcase.ui.user.event.UserEvent
import com.nvshink.shifttestcase.ui.user.screen.currentuser.CurrentUserScreen
import com.nvshink.shifttestcase.ui.user.screen.list.UserEmptyList
import com.nvshink.shifttestcase.ui.user.screen.list.UsersList
import com.nvshink.shifttestcase.ui.user.state.UserUiState
import com.nvshink.shifttestcase.ui.utils.ContentType

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    userUiState: UserUiState,
    onEvent: (UserEvent) -> Unit,
    contentType: ContentType,
    innerPadding: PaddingValues
) {
    val lazyListState = rememberLazyListState()
    val list = @Composable {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (userUiState is UserUiState.SuccessState && userUiState.userList.isEmpty()) {
                UserEmptyList(
                    title = stringResource(R.string.empty_list_title_users),
                    icon = Icons.Default.SearchOff,
                    iconDescription = stringResource(R.string.empty_list_icon_description_users),
                    onRefresh = { onEvent(UserEvent.RefreshList) }
                )
            } else {
                UsersList(
                    users = userUiState.userList,
                    onEvent = onEvent,
                    isRefreshing = userUiState.isRefreshing,
                    lazyListState = lazyListState
                )
            }
            when {
                userUiState is UserUiState.LoadingState -> CircularProgressIndicator()
                userUiState is UserUiState.ErrorState -> Text(userUiState.error?.message ?: "error")
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding), contentAlignment = Alignment.TopCenter
    ) {
        when (contentType) {
            ContentType.LIST_ONLY -> {
                if (userUiState is UserUiState.SuccessState && userUiState.currentUser != null) {
                    CurrentUserScreen(user = userUiState.currentUser, onBackPressed = {
                        onEvent(UserEvent.UpdateCurrentUser(null))
                    })
                } else {
                    list()
                }
            }

            ContentType.LIST_AND_DETAIL -> {
                Row {
                    list()
                    CurrentUserScreen(
                        user = if (userUiState is UserUiState.SuccessState) userUiState.currentUser else null,
                        onBackPressed = {
                            onEvent(UserEvent.UpdateCurrentUser(null))
                        })
                }
            }
        }
    }

}