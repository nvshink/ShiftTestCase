package com.nvshink.shifttestcase.ui.user.state

import com.nvshink.domain.user.model.UserModel
import com.nvshink.shifttestcase.ui.utils.ContentType

sealed interface UserUiState {
    val isShowingList: Boolean
    val isRefreshing: Boolean
    val contentType: ContentType

    data class LoadingState(
        override val isShowingList: Boolean = false,
        override val isRefreshing: Boolean = false,
        override val contentType: ContentType = ContentType.LIST_ONLY
    ) : UserUiState

    data class SuccessState(
        val userList: List<UserModel> = emptyList(),
        val currentUser: UserModel? = null,
        override val isShowingList: Boolean,
        override val isRefreshing: Boolean,
        override val contentType: ContentType
    ) : UserUiState

    data class ErrorState(
        val error: Throwable? = null,
        override val isShowingList: Boolean,
        override val isRefreshing: Boolean,
        override val contentType: ContentType
    ) : UserUiState
}