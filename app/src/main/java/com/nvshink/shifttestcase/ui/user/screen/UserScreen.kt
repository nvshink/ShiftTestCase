package com.nvshink.shifttestcase.ui.user.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nvshink.shifttestcase.ui.user.event.UserEvent
import com.nvshink.shifttestcase.ui.user.screen.currentuser.CurrentUserScreen
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
    val list = {
        if (userUiState::class == UserUiState.SuccessState::class && userUiState. .isEmpty()) {
            EmptyItemScreen(
                title = emptyListTitle,
                icon = emptyListIcon,
                iconDescription = emptyListIconDescription,
                colors = colors
            )
        } else {
            UsersList(
                users = userUiState.userList,
                onEvent = onEvent,
                isRefreshing = userUiState.isRefreshing
            )
        }
    }

    Box(modifier = modifier.padding(innerPadding), contentAlignment = Alignment.Center){
        when (userUiState) {
            is UserUiState.LoadingState -> {
                CircularProgressIndicator()
            }

            is UserUiState.SuccessState -> {
                when(contentType){
                    ContentType.LIST_ONLY -> {
                        if(userUiState.currentUser == null) {
                            list()
                        } else {
                            CurrentUserScreen(user = userUiState.currentUser, onBackPressed = {
                                onEvent(UserEvent.UpdateCurrentUser(null))
                            })
                        }

                    }
                    ContentType.LIST_AND_DETAIL -> {
                        Row {
                            list()
                            CurrentUserScreen(user = userUiState.currentUser, onBackPressed = {
                                onEvent(UserEvent.UpdateCurrentUser(null))
                            })
                        }
                    }
                }

            }

            is UserUiState.ErrorState -> {
                Text(userUiState.error?.message ?: "error")
            }
        }
    }

}