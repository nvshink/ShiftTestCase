package com.nvshink.shifttestcase.ui.user.screen.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nvshink.domain.user.model.UserModel
import com.nvshink.shifttestcase.R
import com.nvshink.shifttestcase.ui.user.event.UserEvent
import com.nvshink.shifttestcase.ui.utils.ContentType
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersList(
    users: List<UserModel>,
    onEvent: (UserEvent) -> Unit,
    isRefreshing: Boolean,
    isLoading: Boolean
) {
    val lazyColumnState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val isAtTop by remember {
        derivedStateOf {
            lazyColumnState.firstVisibleItemIndex == 0 && lazyColumnState.firstVisibleItemScrollOffset == 0
        }
    }
    if (isAtTop) {
        onEvent(UserEvent.HideToTopButton)
    } else {
        onEvent(UserEvent.ShowToTopButton)
    }
    Box {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { onEvent(UserEvent.RefreshList) }
        ) {

                InfinityLazyGrid(
                    items = listOfItems,
                    cellsArrangement = listArrangement,
                    listItem = listItem,
                    lazyGridState = lazyGridState,
                    onLoadMore = onLoadMore
                )
            }
            LazyColumn(
                state = lazyColumnState
            ) {
                items(users) {
                    UserCard(
                        user = it,
                        onCardClick = {
                            onEvent(UserEvent.UpdateCurrentUser(it))
                        }
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = !isAtTop,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        lazyColumnState.animateScrollToItem(0)
                    }
                },
                colors = IconButtonDefaults.iconButtonColors().copy(
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowUpward,
                    contentDescription = stringResource(R.string.icon_description_arrow_upward),
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }

}