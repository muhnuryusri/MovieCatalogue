package com.application.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CastResponse(

	@field:SerializedName("cast")
	val cast: ArrayList<CastItem>,

	@field:SerializedName("id")
	val id: Int
)

data class CastItem(

	@field:SerializedName("character")
	val character: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("profile_path")
	val profilePath: String,

	@field:SerializedName("id")
	val id: Int
)
