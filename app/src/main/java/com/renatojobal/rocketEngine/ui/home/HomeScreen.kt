package com.renatojobal.rocketEngine.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.renatojobal.rocketEngine.R
import com.renatojobal.rocketEngine.SharedViewModel
import com.renatojobal.rocketEngine.repository.Entity
import com.renatojobal.rocketEngine.ui.theme.RocketEngineTheme

@Composable
fun HomeScreen(sharedViewModel: SharedViewModel, onResultSelected: () -> Unit) {


    val targetEntities by sharedViewModel.entities.observeAsState()
    var selectedEntity by remember { (mutableStateOf(targetEntities?.get(0))) }


    val animDuration = 1000
    val animDelay = 0




    BoxWithConstraints(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 48.dp)
            .fillMaxSize()
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

//            selectedCategory?.let { safeCategory ->
//                Text(text = safeCategory.title + " " + safeCategory.thumbnail,
//                style = MaterialTheme.typography.h5,
//                modifier = Modifier.padding(top=8.dp))
//            }
//
//            CircularProgressBar(
//                percentage = currentPercentage,
//                suffix = " kg",
//                insideText = selectedCategory?.categoryCarbonFootprintKg?.trimDecimals() ?: "0"
//            )

            ExpandableSearchView(
                searchDisplay = "",
                onSearchDisplayChanged = {
                     // Call to the shared view model in order to search
                     sharedViewModel.handleSearch(it)
                },
                expandedInitially = true,
                onSearchDisplayClosed = {}
            )
//            Button(
//                modifier = Modifier
//                    .padding(4.dp),
//                onClick = { onOffsetClicked() }
//            ) {
//                Text(text = "COMPENSAR", style = MaterialTheme.typography.button)
//            }
//            ActivitiesListPresenter(
//                sharedViewModel,
//                selectedCategory = selectedCategory
//            ) { wantedCategory ->
//                Timber.i("New category selected")
//                selectedCategory = wantedCategory
//
//            }
            EntityListPresenter(
                sharedViewModel = sharedViewModel,
                selectedCategory = selectedEntity){ wantedEntity ->
                selectedEntity = wantedEntity
                sharedViewModel.selectedEntity.value = wantedEntity
                onResultSelected()
            }
        }

    }

}


@Composable
fun CircularProgressBar(
    percentage: Float = 0f,
    suffix: String = "",
    insideText: String = percentage.toString(),
    fontSize: TextUnit = 28.sp,
    radius: Dp = 100.dp,
    color: Color = MaterialTheme.colors.onSurface,
    strokeWidth: Dp = 8.dp
) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2f)
            .padding(8.dp)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                -90f,
                (360 * percentage / 100),
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = insideText + suffix,
            color = MaterialTheme.colors.onSurface,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold

        )
    }

}

@Preview(showBackground = true)
@Composable
fun CircularProgressPreview() {
    CircularProgressBar(percentage = 50f)
}

@Composable
fun EntityChip(
    presentingEntity: Entity,
    selectedEntity: Entity?,
    onSelectedEntity: (Entity) -> Unit
) {

    val itemModifier: Modifier = if (selectedEntity == presentingEntity) {
        Modifier
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxWidth()
    } else {
        Modifier
            .fillMaxWidth()
    }

    BoxWithConstraints(
        modifier = itemModifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = (presentingEntity.thumbnail ?: "\uD83C\uDFED"),
                    modifier = Modifier.clickable { onSelectedEntity(presentingEntity) })
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable { onSelectedEntity(presentingEntity) },
                    text = presentingEntity.uri
                )

            }
        }
    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun ActivityItemPreview() {
//    val dummyActivity = Resource(percentage = 0.36, title = "Transport", thumbnail = "\uD83C\uDFED")
//    ActivityItem(dummyActivity, Resource()) {}
//}


@Composable
fun EntityListPresenter(
    sharedViewModel: SharedViewModel,
    selectedCategory: Entity?,
    onSelectedCategory: (Entity) -> Unit
) {


    val targetEntities by sharedViewModel.entities.observeAsState()


    Text(
        text = "Results",
        modifier = Modifier
            .padding(4.dp),
        style = MaterialTheme.typography.h5
    )
    targetEntities?.let { listCategories ->
        if (listCategories.isNotEmpty()) {
            LazyColumn {
                items(listCategories.size) { index ->
                    EntityChip(
                        presentingEntity = listCategories[index],
                        selectedEntity = selectedCategory
                    ) {
                        onSelectedCategory(it)
                    }
                }
            }
        } else {
            Text(text = "Nothing to show \uD83E\uDD32")
        }
    } ?: run {
        Text(text = "Nothing to show \uD83E\uDD32")
    }


}


@Composable
fun ExpandableSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    tint: Color = MaterialTheme.colors.onPrimary
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }


    Crossfade(targetState = expanded) { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpandedSearchView(
                searchDisplay = searchDisplay,
                onSearchDisplayChanged = onSearchDisplayChanged,
                onSearchDisplayClosed = onSearchDisplayClosed,
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint
            )

            false -> CollapsedSearchView(
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint
            )
        }
    }
}

@Composable
fun SearchIcon(iconTint: Color) {
    Icon(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "search icon",
        tint = iconTint
    )
}

@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onPrimary,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Search",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        IconButton(onClick = { onExpandedChanged(true) }) {
            SearchIcon(iconTint = tint)
        }
    }
}

@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onPrimary,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onExpandedChanged(false)
            onSearchDisplayClosed()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back icon",
                tint = tint
            )
        }
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onSearchDisplayChanged(it.text)
            },
            trailingIcon = {
                SearchIcon(iconTint = tint)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(textFieldFocusRequester),
            label = {
                Text(text = "Search", color = tint)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
}

@Preview
@Composable
fun CollapsedSearchViewPreview() {
    RocketEngineTheme {
        Surface(
            color = MaterialTheme.colors.primary
        ) {
            ExpandableSearchView(
                searchDisplay = "",
                onSearchDisplayChanged = {},
                onSearchDisplayClosed = {}
            )
        }
    }
}

@Preview
@Composable
fun ExpandedSearchViewPreview() {
    RocketEngineTheme {
        Surface(
            color = MaterialTheme.colors.primary
        ) {
            ExpandableSearchView(
                searchDisplay = "",
                onSearchDisplayChanged = {},
                expandedInitially = true,
                onSearchDisplayClosed = {}
            )
        }
    }
}



