package com.nvshink.shifttestcase.ui.user.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nvshink.domain.user.model.UserModel
import com.nvshink.shifttestcase.R
import com.nvshink.shifttestcase.ui.user.event.UserEvent
import com.nvshink.shifttestcase.ui.user.screen.currentuser.CurrentUserScreen
import com.nvshink.shifttestcase.ui.user.screen.list.UserEmptyList
import com.nvshink.shifttestcase.ui.user.screen.list.UsersList
import com.nvshink.shifttestcase.ui.user.state.UserUiState
import com.nvshink.shifttestcase.ui.utils.ContentType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    currentUserModifier: Modifier,
    userUiState: UserUiState,
    onEvent: (UserEvent) -> Unit,
    contentType: ContentType,
    innerPadding: PaddingValues
) {
    val lazyListState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val list = @Composable { modifier: Modifier ->
        Column(
            modifier = modifier,
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
                    onCardClick = { user: UserModel ->
                        Log.d("UI", user.toString())
                        onEvent(UserEvent.UpdateCurrentUser(user))
                        onEvent(UserEvent.HideList)
                        scope.launch { sheetState.show() }
                    },
                    onRefresh = { onEvent(UserEvent.RefreshList) },
                    onLoadMore = { onEvent(UserEvent.LoadMore) },
                    isLoad = userUiState is UserUiState.LoadingState,
                    errorMessage = if (userUiState is UserUiState.ErrorState) userUiState.error?.message
                        ?: "" else null,
                    isRefreshing = userUiState.isRefreshing,
                    lazyListState = lazyListState
                )
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
                list(Modifier.fillMaxSize())
                if (!userUiState.isShowingList) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            scope.launch { sheetState.hide() }
                            onEvent(UserEvent.UpdateCurrentUser(null))
                            onEvent(UserEvent.ShowList)
                        },
                        sheetState = sheetState
                    ) {
                        CurrentUserScreen(
                            modifier = currentUserModifier.fillMaxSize(),
                            user = if (userUiState is UserUiState.SuccessState) userUiState.currentUser else null,
                            contentType = contentType,
                            onBackPressed = {
                                scope.launch { sheetState.hide() }
                                onEvent(UserEvent.UpdateCurrentUser(null))
                                onEvent(UserEvent.ShowList)
                            })
                    }
                }
            }

            ContentType.LIST_AND_DETAIL -> {
                Row {
                    list(Modifier.weight(1f))
                    CurrentUserScreen(
                        modifier = currentUserModifier
                            .weight(1f)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .fillMaxHeight(),
                        user = if (userUiState is UserUiState.SuccessState) userUiState.currentUser else null,
                        contentType = contentType,
                        onBackPressed = {
                            onEvent(UserEvent.UpdateCurrentUser(null))
                            onEvent(UserEvent.ShowList)
                        })
                }
            }
        }
    }

}