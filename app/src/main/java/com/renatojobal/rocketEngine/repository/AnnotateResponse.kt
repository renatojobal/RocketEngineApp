package com.renatojobal.rocketEngine.repository

import com.google.gson.annotations.SerializedName

data class AnnotateResponse(

    @SerializedName("@text")
    private val text: String,

    @SerializedName("@confidence")
    private val confidence: String,

    @SerializedName("@support")
    private val support: String,

    @SerializedName("@types")
    private val types: String,

    @SerializedName("@sparql")
    private val sparql: String,

    @SerializedName("@policy")
    private val policy: String,

    @SerializedName("Resources")
    private val resources: List<Resource>? = null,
    // @JsonIgnore
    private val additionalProperties: Map<String, Object> = HashMap<String, Object>()

)

data class Resource(

    @SerializedName("@URI")
    private val uri: String,

    @SerializedName("@support")
    private val support: String,

    @SerializedName("@types")
    private val types: String,

    @SerializedName("@surfaceForm")
    private val surfaceForm: String,

    @SerializedName("@offset")
    private val offset: String,

    @SerializedName("@similarityScore")
    private val similarityScore: String,

    @SerializedName("@percentageOfSecondRank")
    private val percentageOfSecondRank: String,

    //@JsonIgnore
    private val additionalProperties: Map<String, Object> = HashMap<String, Object>()


)