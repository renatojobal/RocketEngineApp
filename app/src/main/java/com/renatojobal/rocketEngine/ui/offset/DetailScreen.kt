package com.renatojobal.rocketEngine.ui.offset


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renatojobal.rocketEngine.SharedViewModel
import com.renatojobal.rocketEngine.repository.Entity
import com.renatojobal.rocketEngine.ui.dummyEntity
import com.renatojobal.rocketEngine.ui.theme.RocketEngineTheme
import com.renatojobal.rocketEngine.ui.trimDecimals

@ExperimentalMaterialApi
@Composable
fun DetailScreen(sharedViewModel: SharedViewModel) {

    // Show detail of the selected item

    val selectedEntity = sharedViewModel.selectedEntity.observeAsState()

//    EntityDetailPresenter(entity = dummyEntity)
    selectedEntity.value?.let{ safeEntity ->
        EntityDetailPresenter(entity = safeEntity)
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
fun EntityDetailPresenter(entity: Entity) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        PropertyLabeledPresenter(label = "Uri", content = entity.uri)
        Text(text = "Types:")
        entity.getTypesAsList().forEach { safeType ->
            PropertyLabeledPresenter(
                label = safeType.split(":")[0],
                content = safeType.split(":")[1])
        }


    }
}

@Preview(showBackground = true)
@Composable
fun EntityDetailPreview() {
    RocketEngineTheme {
        EntityDetailPresenter(dummyEntity)
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
