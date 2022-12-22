package com.omk.MainScreen.ui.dashboard

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import com.omk.MainScreen.R
import com.omk.MainScreen.ui.anim.getIconTint
import com.omk.MainScreen.ui.components.Surface
import com.omk.MainScreen.ui.theme.AppTheme

enum class DashboardSections(
  @StringRes val title: Int,
  val icon: ImageVector,
  val route: String
) {
  HOME(R.string.dashboard_home, Icons.Default.Home, "dashboard/home"),
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    tabs: Array<DashboardSections>,
    color: Color = AppTheme.colors.uiBackground,
    contentColor: Color = AppTheme.colors.iconInteractive
) {

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route

  val dashboardSections = remember { DashboardSections.values() }
  val routes = remember { dashboardSections.map { it.route } }

  if (currentRoute in routes) {
    val currentSection = dashboardSections.first { it.route == currentRoute }

    Surface(
      color = color,
      contentColor = contentColor
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 65.dp)
      ) {
        tabs.forEach { section ->
          val selected = section == currentSection
          val tint by getIconTint(selected = selected)

          BottomNavigationItem(
            icon = {
              Icon(
                imageVector = section.icon,
                tint = tint,
                contentDescription = null
              )
            },
            text = {
              Text(
                text = stringResource(section.title),
                color = tint,
                fontSize = 8.sp,
                style = MaterialTheme.typography.button,
                maxLines = 1
              )
            },
            selected = selected,
            onSelected = {
              if (section.route != currentRoute) {
                navController.navigate(section.route) {
                  launchSingleTop = true
                  restoreState = true
                  popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                  }
                }
              }
            },
            modifier = BottomNavigationItemPadding.weight(1f)
          )
        }
      }

    }
  }
}

private val NavGraph.startDestination: NavDestination?
  get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
  return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}

@Composable
fun BottomNavigationItem(
  icon: @Composable BoxScope.() -> Unit,
  text: @Composable BoxScope.() -> Unit,
  selected: Boolean,
  onSelected: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.selectable(selected = selected, onClick = onSelected)
  ) {
    Box(
      modifier = Modifier.layoutId("icon"),
      content = icon
    )
    Spacer(modifier = Modifier.height(4.dp))
    Box(
      modifier = Modifier.layoutId("text"),
      content = text
    )
  }
}

private val BottomNavigationItemPadding = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
