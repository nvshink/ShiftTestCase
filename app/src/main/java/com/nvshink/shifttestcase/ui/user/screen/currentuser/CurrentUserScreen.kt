package com.nvshink.shifttestcase.ui.user.screen.currentuser

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PersonOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nvshink.domain.user.model.UserModel
import com.nvshink.shifttestcase.R
import com.nvshink.shifttestcase.ui.user.screen.currentuser.states.CurrentUserEmptyScreen
import com.nvshink.shifttestcase.ui.user.screen.currentuser.states.SuccessCurrentUserScreen
import com.nvshink.shifttestcase.ui.utils.ContentType

@Composable
fun CurrentUserScreen(
    modifier: Modifier = Modifier,
    user: UserModel?,
    contentType: ContentType,
    onBackPressed: () -> Unit
) {
    val scrollState = rememberScrollState()
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier) {
        if (user == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CurrentUserEmptyScreen(
                    title = stringResource(R.string.empty_current_user),
                    icon = Icons.Default.PersonOff,
                    iconDescription = stringResource(R.string.empty_current_user_icon_description),
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState),
            ) {
                CurrentUserScreenTopBar(
                    contentType = contentType,
                    onBackButtonClicked = onBackPressed
                )
                SuccessCurrentUserScreen(modifier = Modifier.fillMaxSize(), user = user)
            }
        }
    }
}

@Composable
fun CurrentUserScreenTopBar(
    modifier: Modifier = Modifier,
    contentType: ContentType,
    onBackButtonClicked: (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackButtonClicked != null) {
            when (contentType) {
                ContentType.LIST_ONLY -> null
                ContentType.LIST_AND_DETAIL -> {
                    IconButton(onClick = { onBackButtonClicked() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        }
        Row {
            if (actions != null) actions()
        }
    }
}