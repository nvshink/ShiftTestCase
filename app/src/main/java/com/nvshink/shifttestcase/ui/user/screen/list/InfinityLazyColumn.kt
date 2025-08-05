package com.nvshink.shifttestcase.ui.user.screen.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.nvshink.shifttestcase.R
import com.nvshink.shifttestcase.ui.user.state.UserUiState
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun <T> InfinityLazyColumn(
    modifier: Modifier = Modifier,
    items: List<T>,
    lazyListState: LazyListState,
    rowsArrangement: Dp,
    listItem: @Composable (T) -> Unit,
    isLoad: Boolean,
    errorMessage: String?,
    onLoadMore: () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(rowsArrangement),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(items) { index, item ->
            listItem(item)
        }
        item {
            if (errorMessage != null) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(errorMessage, modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = onLoadMore,
                        modifier = Modifier.weight(1f)
                    ) { Text(text = stringResource(R.string.button_retry_label)) }
                }
            }
            if (isLoad) CircularProgressIndicator()
        }
    }
    InfiniteListHandler(listState = lazyListState, buffer = 10, onLoadMore = onLoadMore)
}

@Composable
fun InfiniteListHandler(
    listState: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleItemIndex > (totalItemsNumber - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                Log.d("INFINITY_LIST", "Load new: ${loadMore.value}")
                if (loadMore.value) onLoadMore()
            }
    }
}