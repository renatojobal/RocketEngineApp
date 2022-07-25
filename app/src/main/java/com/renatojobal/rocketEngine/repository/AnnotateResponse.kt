package com.renatojobal.rocketEngine.repository

import com.google.gson.annotations.SerializedName

data class AnnotateResponse(

    @SerializedName("@text")
    val text: String,

    @SerializedName("@confidence")
    val confidence: String,

    @SerializedName("@support")
    val support: String,

    @SerializedName("@types")
    val types: String,

    @SerializedName("@sparql")
    val sparql: String,

    @SerializedName("@policy")
    val policy: String,

    @SerializedName("Resources")
    val entities: List<Entity>? = null,
    // @JsonIgnore
    val additionalProperties: Map<String, Object> = HashMap()

)

data class Entity(

    @SerializedName("@URI")
    val uri: String,

    @SerializedName("@support")
    val support: String,

    @SerializedName("@types")
    val types: String,

    @SerializedName("@surfaceForm")
    val surfaceForm: String,

    @SerializedName("@offset")
    val offset: String,

    @SerializedName("@similarityScore")
    val similarityScore: String,

    @SerializedName("@percentageOfSecondRank")
    val percentageOfSecondRank: String,

    var thumbnail: String? = null, // An emoji

    //@JsonIgnore
    val additionalProperties: Map<String, Object> = HashMap()


){


    fun getTypesAsList(): List<String> {
        return this.types.split(",")

    }
}