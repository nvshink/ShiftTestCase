package com.nvshink.shifttestcase.ui.user.screen.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun <T> InfinityLazyColumn(
    items: List<T>,
    lazyListState: LazyListState,
    rowsArrangement: Dp,
    listItem: @Composable (T) -> Unit,
    isLoad: Boolean,
    errorMessage: String,
    onLoadMore: () -> Unit
) {
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(rowsArrangement)
    ) {
        itemsIndexed(items) { index, item ->
            listItem(item)
        }
        item {

        }
    }
    InfiniteListHandler(listState = lazyListState, buffer = 4, onLoadMore = onLoadMore)
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
                Log.d("INFINITY_LIST", "Load new: ${ loadMore.value.toString() }")
                if (loadMore.value) onLoadMore()
            }
    }
}