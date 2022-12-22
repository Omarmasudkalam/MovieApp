package com.omk.MainScreen.ui.anim

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.omk.MainScreen.ui.theme.AppTheme

@Composable
fun getIconTint(selected: Boolean): State<Color> {
  return animateColorAsState(
    if (selected) {
      AppTheme.colors.iconInteractive
    } else {
      AppTheme.colors.iconInteractiveInactive
    }
  )
}