package com.nvshink.shifttestcase.ui.user.state

import com.nvshink.domain.user.model.UserModel
import com.nvshink.shifttestcase.ui.utils.ContentType

sealed interface UserUiState {
    val userList: List<UserModel>
    val isShowingList: Boolean
    val isRefreshing: Boolean
    val contentType: ContentType

    data class LoadingState(
        override val userList: List<UserModel> = emptyList(),
        override val isShowingList: Boolean = true,
        override val isRefreshing: Boolean = false,
        override val contentType: ContentType = ContentType.LIST_ONLY
    ) : UserUiState

    data class SuccessState(
        val currentUser: UserModel? = null,
        val isOnline: Boolean = true,
        override val userList: List<UserModel> = emptyList(),
        override val isShowingList: Boolean = true,
        override val isRefreshing: Boolean = false,
        override val contentType: ContentType = ContentType.LIST_ONLY
    ) : UserUiState

    data class ErrorState(
        val error: Throwable? = null,
        override val userList: List<UserModel> = emptyList(),
        override val isShowingList: Boolean = true,
        override val isRefreshing: Boolean = false,
        override val contentType: ContentType = ContentType.LIST_ONLY
    ) : UserUiState
}