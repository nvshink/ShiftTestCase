package com.nvshink.shifttestcase.ui.user.screen.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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