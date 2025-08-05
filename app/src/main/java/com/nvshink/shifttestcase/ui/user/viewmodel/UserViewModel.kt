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
const val RESULTS = 20
const val VERSION = "1.4"

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
open class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _isRefresh = MutableStateFlow(false)
    private val _isLoadMore = MutableStateFlow(true)
    private val _contentType = MutableStateFlow(ContentType.LIST_ONLY)
    private val _pageInfoModel = MutableStateFlow<PageInfoModel?>(
        PageInfoModel(
            pageCount = 1,
            seed = SEED,
            results = RESULTS,
            version = VERSION
        )
    )
    private val _requestedPageInfoModel = MutableStateFlow(
        _pageInfoModel.value
    )
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.LoadingState())

    private val _loadedUsers = combine(_isRefresh, _requestedPageInfoModel) { _, requestedPageInfoModel ->
        repository.getUsers(requestedPageInfoModel)
    }.flatMapLatest { flow -> flow }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Resource.Loading
    )

    val uiState = combine(
        _uiState,
        _loadedUsers,
        _contentType
    ) { uiState, loadedUsers, contentType ->
        when (loadedUsers) {
            Resource.Loading -> {
                _uiState.update {
                    UserUiState.LoadingState(
                        userList = uiState.userList,
                        isShowingList = uiState.isShowingList,
                        isRefreshing = _isRefresh.value,
                        contentType = contentType
                    )
                }
            }

            is Resource.Success -> {
                _pageInfoModel.update {
                    loadedUsers.data.first
                }
                _uiState.update {
                    UserUiState.SuccessState(
                        userList = when {
                            _isRefresh.value -> {
                                _isRefresh.update { false }
                                loadedUsers.data.second
                            }
                            _isLoadMore.value -> {
                                _isLoadMore.update { false }
                                if (loadedUsers.isLocal) {
                                    emptyList()
                                } else {
                                    uiState.userList
                                } + loadedUsers.data.second
                            }

                            else -> {
                                uiState.userList
                            }
                        },
                        currentUser =
                            if (uiState is UserUiState.SuccessState) uiState.currentUser else null,
                        error = loadedUsers.onlineException,
                        isShowingList = uiState.isShowingList,
                        isRefreshing = _isRefresh.value,
                        isOnline = !loadedUsers.isLocal,
                        contentType = contentType,
                    )
                }
            }

            is Resource.Error -> {
                _uiState.update {
                    UserUiState.ErrorState(
                        error = loadedUsers.exception,
                        userList = uiState.userList,
                        isShowingList = uiState.isShowingList,
                        isRefreshing = _isRefresh.value,
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
            UserEvent.LoadMore -> {
                if (_uiState.value is UserUiState.SuccessState && (_uiState.value as UserUiState.SuccessState).isOnline) {
                    _isLoadMore.update { true }
                    _requestedPageInfoModel.update { it?.copy(pageCount = (_pageInfoModel.value?.pageCount ?: 0) + 1) }
                }
            }

            UserEvent.RefreshList -> {
                _isRefresh.update { true }
                _requestedPageInfoModel.update {
                    PageInfoModel(
                        pageCount = 1,
                        seed = SEED,
                        results = RESULTS,
                        version = VERSION
                    )
                }
            }

            is UserEvent.UpdateCurrentUser -> {
                _uiState.update {
                    when (it) {
                        is UserUiState.SuccessState -> it.copy(
                            currentUser = event.user,
                            isShowingList = event.user == null
                        )
                        else -> it
                    }
                }
            }

            UserEvent.HideList -> _uiState.update {
                when(it) {
                    is UserUiState.ErrorState -> { it.copy(isShowingList = false) }
                    is UserUiState.LoadingState -> { it.copy(isShowingList = false) }
                    is UserUiState.SuccessState -> { it.copy(isShowingList = false) }
                }
            }
            UserEvent.ShowList -> _uiState.update {
                when(it) {
                    is UserUiState.ErrorState -> { it.copy(isShowingList = true) }
                    is UserUiState.LoadingState -> { it.copy(isShowingList = true) }
                    is UserUiState.SuccessState -> { it.copy(isShowingList = true) }
                }
            }

            UserEvent.ClearError -> _uiState.update {
                when(it) {
                    is UserUiState.ErrorState -> { it.copy(error = null) }
                    is UserUiState.LoadingState -> { it.copy(error = null) }
                    is UserUiState.SuccessState -> { it.copy(error = null) }
                }
            }
        }
    }
}