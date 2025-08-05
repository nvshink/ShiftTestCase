package com.nvshink.shifttestcase.ui.user.state

import com.nvshink.domain.user.model.UserModel
import com.nvshink.shifttestcase.ui.utils.ContentType


sealed interface UserUiState {
    val userList: List<UserModel>
    val error: Throwable?
    val isShowingList: Boolean
    val isRefreshing: Boolean
    val contentType: ContentType

    /**
     * @param userList List of users
     * @param error Error from data loading
     * @param isShowingList Define show list or item
     * @param isRefreshing Define refresh list or not
     * @param contentType Type of content displaying
     */
    data class LoadingState(
        override val userList: List<UserModel> = emptyList(),
        override val error: Throwable? = null,
        override val isShowingList: Boolean = true,
        override val isRefreshing: Boolean = false,
        override val contentType: ContentType = ContentType.LIST_ONLY
    ) : UserUiState

    /**
     * @param currentUser The current user taken for detailed viewing
     * @param isOnline Define loaded data from internet or cache
     * @param userList List of users
     * @param error Error from data loading
     * @param isShowingList Define show list or item
     * @param isRefreshing Define refresh list or not
     * @param contentType Type of content displaying
     */
    data class SuccessState(
        val currentUser: UserModel? = null,
        val isOnline: Boolean = true,
        override val userList: List<UserModel> = emptyList(),
        override val error: Throwable? = null,
        override val isShowingList: Boolean = true,
        override val isRefreshing: Boolean = false,
        override val contentType: ContentType = ContentType.LIST_ONLY
    ) : UserUiState

    /**
     * @param userList List of users
     * @param error Error from data loading
     * @param isShowingList Define show list or item
     * @param isRefreshing Define refresh list or not
     * @param contentType Type of content displaying
     */
    data class ErrorState(
        override val userList: List<UserModel> = emptyList(),
        override val error: Throwable? = null,
        override val isShowingList: Boolean = true,
        override val isRefreshing: Boolean = false,
        override val contentType: ContentType = ContentType.LIST_ONLY
    ) : UserUiState
}