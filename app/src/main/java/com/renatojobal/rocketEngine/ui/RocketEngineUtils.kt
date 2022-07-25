package com.renatojobal.rocketEngine.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.annotations.SerializedName
import com.renatojobal.rocketEngine.repository.Entity
import com.renatojobal.rocketEngine.ui.theme.RocketEngineTheme

val dummyEntity = Entity(
    uri="http://dbpedia.org/resource/Loja_Province",
    support="279",
    types="Wikidata:Q3455524,Schema:Place,Schema:AdministrativeArea,DBpedia:Region,DBpedia:PopulatedPlace,DBpedia:Place,DBpedia:Location,DBpedia:AdministrativeRegion",
    surfaceForm="Loja",
    offset="0",
    similarityScore="0.9920974241116997",
    percentageOfSecondRank="0.00443468556871218",
    thumbnail=null
)

@Composable
fun NotFound() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
        ,
        contentAlignment = Alignment.Center

    ) {
        Text(text = "Not Found")
    }
}

@Preview(showBackground = true)
@Composable
fun NotFoundPreview() {
    RocketEngineTheme {
        NotFound()
    }

}

const val NODE_TYPE_MULTIPLE_CHOICE = "multipleChoice"
const val NODE_TYPE_NUMBER = "number"
const val EMOJI_DEFAULT = "\uD83C\uDFED"

fun Double.trimDecimals() : String{
    return String.format("%.0f", this)
}
