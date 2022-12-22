package com.omk.MainScreen.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.omk.MainScreen.ui.theme.AppTheme

@Composable
fun Card(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    color: Color = AppTheme.colors.uiBackground,
    contentColor: Color = AppTheme.colors.textPrimary,
    border: BorderStroke? = null,
    elevation: Dp = 1.dp,
    content: @Composable () -> Unit
) {
  Surface(
    modifier = modifier,
    shape = shape,
    color = color,
    contentColor = contentColor,
    elevation = elevation,
    border = border,
    content = content
  )
}
