package com.example.mysharedpreferencesproject.restapi.model

data class UserModel(
	val userModel: List<UserModelItem?>? = null
)

data class UserModelItem(
	val albumId: Int? = null,
	val id: Int? = null,
	val title: String? = null,
	val url: String? = null,
	val thumbnailUrl: String? = null
)

