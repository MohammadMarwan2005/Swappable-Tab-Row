package com.alaishat.mohammad.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.alaishat.mohammad.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // step 2:
        val tabItems = listOf(
            TabItem(
                "Home",
                Icons.Filled.Home,
                Icons.Outlined.Home,
            ),
            TabItem(
                "Noifics",
                Icons.Filled.Notifications,
                Icons.Outlined.Notifications,
            ),
            TabItem(
                "Info",
                Icons.Filled.Info,
                Icons.Outlined.Info,
            ),
            TabItem(
                "Account",
                Icons.Filled.AccountCircle,
                Icons.Outlined.AccountCircle,
            ),
            TabItem(
                "Home",
                Icons.Filled.Home,
                Icons.Outlined.Home,
            ),
            TabItem(
                "Noifics",
                Icons.Filled.Notifications,
                Icons.Outlined.Notifications,
            ),
            TabItem(
                "Info",
                Icons.Filled.Info,
                Icons.Outlined.Info,
            ),
            TabItem(
                "Account",
                Icons.Filled.AccountCircle,
                Icons.Outlined.AccountCircle,
            ),
        )
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // step 5: Make a state of selected tab index
                    var selectedTabIndex by remember {
                        mutableIntStateOf(0)
                    }
                    // step 7: Make a state of pager...
                    val pagerState = rememberPagerState(
                        initialPage = 0,
                        initialPageOffsetFraction = 0f
                    ) {
                        tabItems.size
                    }
                    // step 8: Refresh the Horizontal pager when you selectedTabIndex state changes...
                    LaunchedEffect(selectedTabIndex) {
                        pagerState.scrollToPage(selectedTabIndex)
                    }
                    // step 9: Refresh the selectedTab when the Horizontal pager.currentPage changes...
                    LaunchedEffect(pagerState.currentPage) {
                        selectedTabIndex = pagerState.currentPage
                    }

                    Column(modifier = Modifier.fillMaxSize()) {
                        // 3rd step:
                        ScrollableTabRow(
                            selectedTabIndex = selectedTabIndex, edgePadding = 10.dp,
                            containerColor = Color.LightGray, contentColor = Color.Red,
//                            divider = { Divider(thickness = 3.0.dp)}
                        ) { // There is something called TabRow()
                            // step 4:
                            tabItems.forEachIndexed { index, tabItem ->
                                Tab(
                                    selected = selectedTabIndex == index,
                                    onClick = {
                                        selectedTabIndex = index
                                        Toast.makeText(this@MainActivity, "${tabItem.title}", Toast.LENGTH_SHORT).show()
                                    },
                                    text = {
                                        Text(text = tabItem.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedTabIndex) tabItem.selectedIcon else tabItem.unselectedIcon,
                                            contentDescription = tabItem.title
                                        )
                                    }
                                )
                            }
                        }
                        // step 6: make a Horizontal Pager to
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize(),
                        ) { index ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = tabItems[index].title)
                            }
                        }
                    }
                }
            }
        }
    }
}
// step 1:
data class TabItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)