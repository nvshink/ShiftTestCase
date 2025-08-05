package com.nvshink.shifttestcase.ui.user.event

import com.nvshink.domain.user.model.UserModel

sealed interface UserEvent {
    data object RefreshList: UserEvent
    data object LoadMore: UserEvent
    data object HideList: UserEvent
    data object ShowList: UserEvent
    data class UpdateCurrentUser(val user: UserModel?) : UserEvent
}