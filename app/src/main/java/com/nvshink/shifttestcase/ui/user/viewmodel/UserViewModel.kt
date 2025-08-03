package com.nvshink.shifttestcase.ui.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nvshink.domain.pageinfo.PageInfoModel
import com.nvshink.domain.resource.Resource
import com.nvshink.domain.user.repository.UserRepository
import com.nvshink.shifttestcase.ui.user.event.UserEvent
import com.nvshink.shifttestcase.ui.user.state.UserUiState
import com.nvshink.shifttestcase.ui.utils.ContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

const val SEED = "withLoveToTheShiftTeam"
const val RESULTS = 10
const val VERSION = "1.4"

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
open class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _isRefresh = MutableStateFlow(false)
    private val _isLoadMore = MutableStateFlow(false)
    private val _contentType = MutableStateFlow(ContentType.LIST_ONLY)
    private val _pageInfoModel = MutableStateFlow<PageInfoModel?>(
        PageInfoModel(
            pageCount = 1,
            seed = SEED,
            results = RESULTS,
            version = VERSION
        )
    )

    private val _users = combine(
        _isRefresh,
        _pageInfoModel
    ) { isRefresh, pageInfoModel ->
        _isLoadMore.update { false }
        _isRefresh.update { false }
        repository.getUsers(pageInfoModel)
    }.flatMapLatest { flow -> flow }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            Resource.Loading
        )

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.LoadingState())

    val uiState = combine(
        _uiState,
        _users,
        _contentType
    ) { uiState, users, contentType ->
        when (users) {
            Resource.Loading -> {
                _uiState.update {
                    UserUiState.LoadingState(
                        isShowingList = uiState.isShowingList,
                        isAtTop = uiState.isAtTop,
                        isRefreshing = uiState.isRefreshing,
                        contentType = contentType
                    )
                }
            }

            is Resource.Success -> {
                _uiState.update {
                    UserUiState.SuccessState(
                        userList = users.data.second,
                        currentUser = if (uiState is UserUiState.SuccessState) uiState.currentUser else null,
                        isShowingList = uiState.isShowingList,
                        isAtTop = uiState.isAtTop,
                        isRefreshing = uiState.isRefreshing,
                        contentType = contentType,
                    )
                }
            }

            is Resource.Error -> {
                _uiState.update {
                    UserUiState.ErrorState(
                        error = users.exception,
                        isShowingList = uiState.isShowingList,
                        isAtTop = uiState.isAtTop,
                        isRefreshing = uiState.isRefreshing,
                        contentType = contentType
                    )
                }
            }
        }
        uiState
    }.stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(5000),
        UserUiState.LoadingState()
    )

    fun onEvent(event: UserEvent) {
        when (event) {
            UserEvent.HideList -> _uiState.update {
                when (it) {
                    is UserUiState.SuccessState -> it.copy(isShowingList = false)
                    is UserUiState.LoadingState -> it.copy(isShowingList = false)
                    is UserUiState.ErrorState -> it.copy(isShowingList = false)
                }
            }

            UserEvent.ShowList -> _uiState.update {
                when (it) {
                    is UserUiState.SuccessState -> it.copy(isShowingList = true)
                    is UserUiState.LoadingState -> it.copy(isShowingList = true)
                    is UserUiState.ErrorState -> it.copy(isShowingList = true)
                }
            }

            UserEvent.HideToTopButton -> _uiState.update {
                when (it) {
                    is UserUiState.SuccessState -> it.copy(isShowingList = false)
                    is UserUiState.LoadingState -> it.copy(isShowingList = false)
                    is UserUiState.ErrorState -> it.copy(isShowingList = false)
                }
            }

            UserEvent.ShowToTopButton -> _uiState.update {
                when (it) {
                    is UserUiState.SuccessState -> it.copy(isShowingList = true)
                    is UserUiState.LoadingState -> it.copy(isShowingList = true)
                    is UserUiState.ErrorState -> it.copy(isShowingList = true)
                }
            }

            UserEvent.LoadMore -> _isLoadMore.update { true }
            UserEvent.RefreshList -> {
                _pageInfoModel.update {
                    PageInfoModel(
                        pageCount = 1,
                        seed = SEED,
                        results = RESULTS,
                        version = VERSION
                    )
                }
                _isRefresh.update { true }
                _uiState.update { UserUiState.LoadingState(isRefreshing = true) }
            }

            is UserEvent.UpdateCurrentUser -> {
                _uiState.update {
                    when (it) {
                        is UserUiState.SuccessState -> it.copy(currentUser = event.user)
                        else -> it
                    }
                }
            }
        }
    }
}