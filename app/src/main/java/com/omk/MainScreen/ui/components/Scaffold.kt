package com.omk.MainScreen.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.omk.MainScreen.ui.theme.AppTheme

/**
 * Wrap Material [androidx.compose.material.Scaffold] and set [AppTheme] colors.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieAppScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable (() -> Unit) = {},
    bottomBar: @Composable (() -> Unit) = {},
    snackBarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    fab: @Composable (() -> Unit) = {},
    fabPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = AppTheme.colors.uiBackground,
    drawerContentColor: Color = AppTheme.colors.textSecondary,
    drawerScrimColor: Color = AppTheme.colors.uiBorder,
    backgroundColor: Color = AppTheme.colors.uiBackground,
    contentColor: Color = AppTheme.colors.textSecondary,
    bodyContent: @Composable (PaddingValues) -> Unit
) {
  Scaffold(
    modifier = modifier,
    scaffoldState = scaffoldState,
    topBar = topBar,
    bottomBar = bottomBar,
    snackbarHost = snackBarHost,
    floatingActionButton = fab,
    floatingActionButtonPosition = fabPosition,
    isFloatingActionButtonDocked = isFloatingActionButtonDocked,
    drawerContent = drawerContent,
    drawerShape = drawerShape,
    drawerElevation = drawerElevation,
    drawerBackgroundColor = drawerBackgroundColor,
    drawerContentColor = drawerContentColor,
    drawerScrimColor = drawerScrimColor,
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    content = bodyContent
  )
}
