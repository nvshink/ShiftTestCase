package com.nvshink.shifttestcase.ui.user.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nvshink.domain.user.model.UserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersList(
    users: List<UserModel>,
    onCardClick: (user: UserModel) -> Unit,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    lazyListState: LazyListState,
    isLoad: Boolean,
    errorMessage: String?,
    isRefreshing: Boolean
) {
    Box {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh
        ) {
            InfinityLazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                items = users,
                rowsArrangement = 8.dp,
                listItem = {
                    UserCard(
                        user = it,
                        onCardClick = { onCardClick(it) }
                    )
                },
                lazyListState = lazyListState,
                isLoad = isLoad,
                errorMessage = errorMessage,
                onLoadMore = onLoadMore
            )
        }
    }
}