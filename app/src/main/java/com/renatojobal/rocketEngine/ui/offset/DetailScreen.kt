package com.renatojobal.rocketEngine.ui.offset


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.renatojobal.rocketEngine.SharedViewModel
import com.renatojobal.rocketEngine.repository.Entity
import com.renatojobal.rocketEngine.ui.dummyEntity
import com.renatojobal.rocketEngine.ui.theme.RocketEngineTheme

@ExperimentalMaterialApi
@Composable
fun DetailScreen(sharedViewModel: SharedViewModel) {

    // Show detail of the selected item

    val selectedEntity = sharedViewModel.selectedEntity.observeAsState()


    selectedEntity.value?.let{ safeEntity ->
        EntityDetailPresenter(entity = safeEntity, sharedViewModel = sharedViewModel)
    }


}

@Composable
fun PropertyLabeledPresenter(label: String, content: String) {
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = label)
        Text(text = content, modifier = Modifier.padding(start = 10.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun PropertyLabeledPreview() {
    RocketEngineTheme {
        Column {
            dummyEntity.getTypesAsList().forEach {
                PropertyLabeledPresenter(label = "Types", content = it)
            }
        }


    }
}

@Composable
fun EntityDetailPresenter(entity: Entity, sharedViewModel: SharedViewModel) {

    val fullEntityInfo = sharedViewModel.fullEntityInfo.observeAsState()

    val relatedLinks = sharedViewModel.relatedEntities.observeAsState()

    val thumbnail = fullEntityInfo
        .value?.getAsJsonArray("http://dbpedia.org/ontology/thumbnail")?.get(0)?.asJsonObject?.get("value")?.asString

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(12.dp)
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
    ) {

        if( ! thumbnail.isNullOrEmpty()){

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = entity.uri,
                contentScale = ContentScale.FillWidth
            )
        }



        PropertyLabeledPresenter(label = "Uri", content = entity.uri)
        Text(text = "Types:")
        entity.getTypesAsList().forEach { safeType ->

                PropertyLabeledPresenter(
                    label = safeType.split(":")[0],
                    content = safeType.split(":")[1])



        }

        Text(text = "Related links:")

        LazyColumn {
            item {

            }
        }

        relatedLinks.value?.forEach { relatedLink ->
            PropertyLabeledPresenter(label = "", content = relatedLink)
        }







    }
}

@Preview(showBackground = true)
@Composable
fun EntityDetailPreview() {
    RocketEngineTheme {
        EntityDetailPresenter(dummyEntity, sharedViewModel = SharedViewModel())
    }
}


//
//@ExperimentalMaterialApi
//@Composable
//fun ProjectCard(
//    project: Project,
//    onClick: (Project) -> Unit
//) {
//    Card(
//        onClick = { onClick(project) },
//        elevation = 10.dp,
//        backgroundColor = MaterialTheme.colors.primary,
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .height(120.dp)
//    ) {
//
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(11f)
//                    .padding(10.dp)
//            ) {
//                Text(text = project.name ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis)
//                Text(
//                    text = project.description ?: "",
//                    maxLines = 4,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//            Box(
//                modifier = Modifier
//                    .weight(6f)
//                    .fillMaxSize()
//                    .background(
//                        brush = Brush.horizontalGradient(
//                            colors = listOf(
//                                MaterialTheme.colors.primary,
//                                Color(0xFF141E32)
//                            ),
//                            startX = 0.0f,
//                            endX = 60f
//                        ),
//                        shape = MaterialTheme.shapes.small
//                    )
//
//            ) {
//                Image(
//                    painter = painterResource(R.drawable.team_trees_example),
//                    "team trees icon",
//                    modifier = Modifier
//                        .padding(start = 30.dp)
//                        .fillMaxSize(),
//                    contentScale = ContentScale.Crop
//                )
//            }
//
//
//        }
//
//    }
//
//
//}
//
//@ExperimentalMaterialApi
//@Preview(showBackground = true)
//@Composable
//fun ProjectCardPreview() {
//    RocketEngineTheme {
//        ProjectCard(
//            Project(
//                name = "TEAMTREES",
//                "Team Trees, stylized as #TEAMTREES, is a collaborative fundraiser that raised 20 million U.S. dollars before 2020 to plant 20 million trees. The initiative was started by American YouTubers MrBeast and Mark Rober"
//            )
//        ) {}
//    }
//}
//
//@ExperimentalMaterialApi
//@Preview(showBackground = true)
//@Composable
//fun DetailPreviewScreen() {
//    val list = listOf(
//        Project(
//            name = "TEAMTREES",
//            "Team Trees, stylized as #TEAMTREES, is a collaborative fundraiser that raised 20 million U.S. dollars before 2020 to plant 20 million trees. The initiative was started by American YouTubers MrBeast and Mark Rober"
//        ),
//        Project(
//            name = "TEAMTREES",
//            "Team Trees, stylized as #TEAMTREES, is a collaborative fundraiser that raised 20 million U.S. dollars before 2020 to plant 20 million trees. The initiative was started by American YouTubers MrBeast and Mark Rober"
//        ),
//        Project(
//            name = "TEAMTREES",
//            "Team Trees, stylized as #TEAMTREES, is a collaborative fundraiser that raised 20 million U.S. dollars before 2020 to plant 20 million trees. The initiative was started by American YouTubers MrBeast and Mark Rober"
//        ),
//        Project(
//            name = "TEAMTREES",
//            "Team Trees, stylized as #TEAMTREES, is a collaborative fundraiser that raised 20 million U.S. dollars before 2020 to plant 20 million trees. The initiative was started by American YouTubers MrBeast and Mark Rober"
//        )
//    )
//    RocketEngineTheme {
//        LazyColumn {
//            items(items = list, itemContent = { item ->
//
//                ProjectCard(project = item) {
//
//                }
//
//            })
//
//
//        }
//    }
//}
