package com.nvshink.shifttestcase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nvshink.shifttestcase.ui.user.screen.UserScreen
import com.nvshink.shifttestcase.ui.user.viewmodel.UserViewModel
import com.nvshink.shifttestcase.ui.utils.ContentType

@Composable
fun ShiftTestCaseApp(
    windowSize: WindowWidthSizeClass,
    innerPadding: PaddingValues
) {
    val contentType: ContentType
    var screensShape = RoundedCornerShape(0.dp)
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            contentType = ContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            contentType = ContentType.LIST_ONLY
            screensShape = MaterialTheme.shapes.extraLarge.copy(
                topEnd = CornerSize(
                    0.dp
                ), bottomEnd = CornerSize(0.dp)
            ) as RoundedCornerShape
        }

        WindowWidthSizeClass.Expanded -> {
            contentType = ContentType.LIST_AND_DETAIL
            screensShape = MaterialTheme.shapes.extraLarge.copy(
                topEnd = CornerSize(
                    0.dp
                ), bottomEnd = CornerSize(0.dp)
            ) as RoundedCornerShape
        }

        else -> {
            contentType = ContentType.LIST_ONLY
        }
    }



    val userViewModel: UserViewModel = hiltViewModel()
    val userUiState = userViewModel.uiState.collectAsState().value
    Box(modifier = Modifier.fillMaxSize()) {
        UserScreen(
            modifier = Modifier.fillMaxSize()
                .clip(screensShape)
                .background(MaterialTheme.colorScheme.surfaceContainer),
            currentUserModifier = Modifier
                .clip(screensShape),
            userUiState = userUiState,
            onEvent = userViewModel::onEvent,
            contentType = contentType,
            innerPadding = innerPadding,
        )
    }
}