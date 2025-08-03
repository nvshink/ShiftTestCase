package com.nvshink.shifttestcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import com.nvshink.shifttestcase.ui.ShiftTestCaseApp
import com.nvshink.shifttestcase.ui.theme.ShiftTestCaseTheme
import com.nvshink.shifttestcase.ui.user.screen.UserScreen
import com.nvshink.shifttestcase.ui.user.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val layoutDirection = LocalLayoutDirection.current
            ShiftTestCaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val layoutDirection = LocalLayoutDirection.current
                    Surface(
                        modifier = Modifier
                            .padding(
                                start = WindowInsets.safeDrawing.asPaddingValues()
                                    .calculateStartPadding(layoutDirection),
                                end = WindowInsets.safeDrawing.asPaddingValues()
                                    .calculateEndPadding(layoutDirection)
                            )
                    ) {
                        val windowSize = calculateWindowSizeClass(this)
                        ShiftTestCaseApp(windowSize = windowSize.widthSizeClass, innerPadding = innerPadding)
                    }
                }
            }
        }
    }
}