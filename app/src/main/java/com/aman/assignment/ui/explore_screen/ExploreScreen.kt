package com.aman.assignment.ui.explore_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aman.assignment.R
import com.aman.assignment.ui.theme.AmanAssignmentTheme
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ExploreScreen(
    onNavigateToRefine: () -> Unit
) {


    Column {

        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }
            },
            title = {
                Column {
                    Text("Howdy, John Doe !!")
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                        Text(
                            text = "Jyoti Nager, Alwar",
                            fontSize = 12.sp
                        )
                    }
                }

            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
            actions = {
                IconButton(onClick = onNavigateToRefine ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }
            }
        )

        val tabs = Tab.entries.toList()

        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = {tabs.size}
        )


        val scope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = 0,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            indicator = {
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(it[pagerState.currentPage])
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }, text = {
                        Text(text = tab.name)
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.LightGray
                )
            }
        }


        SearchSection()


        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState
        ) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    count = when (tabs[pagerState.currentPage]) {
                        Tab.Personal -> 6
                        Tab.Business -> 2
                        Tab.Merchant -> 3
                    }
                ) {
                    ExploreCard(
                        ExploreCardItem(
                            image = R.drawable.person,
                            personName = "Aman Naruka",
                            city = "Alwar",
                            category = "Student",
                            progress = 0.2f,
                            tagline = "Coffe | Business | Freindship",
                            message = "Hi, Community I am open to new Connections 🙂",
                            distance = "2.2 Km"
                        )
                    )
                }
            }

        }

    }
}


@Composable
fun SearchSection() {

    var searchText by remember {
        mutableStateOf("")
    }

    Row(modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)) {

        BasicTextField(
            modifier = Modifier.weight(1f),
            value = searchText,
            onValueChange = {
                searchText = it
            },
            decorationBox = { content ->
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(10.dp)
                ) {

                    Icon(imageVector = Icons.Default.Search, contentDescription = null)

                    Spacer(modifier = Modifier.width(20.dp))

                    if (searchText.isEmpty()) {
                        Text(text = "Search")
                    } else {
                        content()
                    }
                }
            }
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
        }

    }
}

enum class Tab {
    Personal,
    Business,
    Merchant
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    AmanAssignmentTheme {
        ExploreScreen(onNavigateToRefine = {})
    }
}