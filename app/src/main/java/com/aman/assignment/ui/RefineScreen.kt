package com.aman.assignment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aman.assignment.ui.theme.AmanAssignmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefineScreen(
    onNavigateUp: () -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {

        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
            title = {
                Text(text = "Refine")
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            AvailabilityDropDown()

            Spacer(modifier = Modifier.height(20.dp))

            StatusTextField()

            Spacer(modifier = Modifier.height(20.dp))

            HyperLocalDistance()

            Spacer(modifier = Modifier.height(20.dp))

            SelectPurposeSection()

            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(.5f),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Save & Explore")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

        }


    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectPurposeSection() {
    Column {

        Text(text = "SelectPurpose", color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(10.dp))

        val items = remember {
            mutableStateListOf(
                ItemChip(isSelected = true, text = "Coffee"),
                ItemChip(isSelected = false, text = "Business"),
                ItemChip(isSelected = false, text = "Hobbies"),
                ItemChip(isSelected = false, text = "Friendship"),
                ItemChip(isSelected = true, text = "Movies"),
                ItemChip(isSelected = false, text = "Dinning"),
                ItemChip(isSelected = true, text = "Dating"),
                ItemChip(isSelected = false, text = "Matrimony"),
            )
        }



        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
        ) {

            items.forEachIndexed { index, itemChip ->
                Chip(
                    text = itemChip.text,
                    isSelected = itemChip.isSelected,
                    onCLick = {
                        items[index] = items[index].copy(
                            isSelected = !items[index].isSelected
                        )
                    }
                )
            }
        }

    }

}

data class ItemChip(val isSelected: Boolean, val text: String)

@Composable
fun Chip(
    text: String,
    isSelected: Boolean,
    onCLick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .border(
                color = MaterialTheme.colorScheme.primary,
                width = 1.dp,
                shape = RoundedCornerShape(50.dp),
            )
            .background(
                shape = RoundedCornerShape(50.dp),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White
            )
            .clickable(onClick = onCLick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = text,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
fun HyperLocalDistance() {

    Column {
        var distance by rememberSaveable {
            mutableStateOf(0f)
        }

        Text(text = "Select Hyper Local Distance", color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(10.dp))

        SliderWithLabel(
            value = distance,
            onRadiusChange = {
                distance = it.toFloat()
            },
            valueRange = 0f..100f,
            finiteEnd = true,
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "1 km"
            )
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "100 km"
            )
        }

    }
}

@Composable
fun StatusTextField() {


    Column {
        var statusText by rememberSaveable {
            mutableStateOf("")
        }
        var isExpanded by remember {
            mutableStateOf(false)
        }
        Text(text = "Add your Status", color = MaterialTheme.colorScheme.primary)

        BasicTextField(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .toggleable(
                    value = isExpanded,
                    onValueChange = {
                        isExpanded = it
                    }
                ),
            value = statusText,
            onValueChange = {
                if (it.length <= 250) {
                    statusText = it
                }
            },
            decorationBox = { inputText ->
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp)
                        .fillMaxWidth(),
                ) {
                    if (statusText.isEmpty()) {
                        Text(text = "Hi community Im open to new connections \uD83D\uDE42")
                    } else inputText()
                }
            },

            )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(text = "${statusText.length} / 250")
        }
    }

}

@Composable
fun AvailabilityDropDown() {
    Column {

        var availability by rememberSaveable {
            mutableStateOf("Available,  HeyLet Us Connect")
        }
        var isExpanded by remember {
            mutableStateOf(false)
        }
        Text(text = "Select you availability", color = MaterialTheme.colorScheme.primary)

        BasicTextField(
            modifier =
            Modifier
                .padding(vertical = 10.dp)
                .toggleable(
                    value = isExpanded,
                    onValueChange = {
                        isExpanded = it
                    }
                ),
            value = availability,
            onValueChange = {},
            decorationBox = { inputText ->
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    inputText()
                    Icon(
                        modifier = Modifier.rotate(
                            degrees = if (isExpanded) 180f else 0f
                        ),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            },
            enabled = false
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { /*TODO*/ }) {
            (1..5).map {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Item $it",
                            color = Color.DarkGray
                        )
                    },
                    onClick = {
                        availability = "Item $it"
                        isExpanded = false
                    }
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun RefineScreenPreview() {
    AmanAssignmentTheme {
        RefineScreen(onNavigateUp = {})
    }
}