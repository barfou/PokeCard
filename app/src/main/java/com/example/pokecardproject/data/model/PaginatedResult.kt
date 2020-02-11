package com.example.pokecardproject.data.model

import com.google.gson.annotations.SerializedName

/**
 * Response wrapper for paginated response from the api
 */
data class PaginatedResult<T>(

    @SerializedName("results") val results: List<T>,
    // Total number of element
    @SerializedName("count") val count: Int,
    // Url for the next page, empty if there is no page
    @SerializedName("next") val next: String?,
    // Url for the previous page, empty if there is no page
    @SerializedName("prev") val prev: String?
)