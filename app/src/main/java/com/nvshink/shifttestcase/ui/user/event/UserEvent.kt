package com.nvshink.shifttestcase.ui.user.event

import com.nvshink.domain.user.model.UserModel

sealed interface UserEvent {
    data object ShowList : UserEvent
    data object HideList : UserEvent
    data object RefreshList: UserEvent
    data object ShowToTopButton: UserEvent
    data object HideToTopButton: UserEvent
    data object LoadMore: UserEvent
    data class UpdateCurrentUser(val user: UserModel?) : UserEvent
}