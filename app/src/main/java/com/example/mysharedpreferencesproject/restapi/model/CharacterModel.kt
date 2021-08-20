package com.example.mysharedpreferencesproject.restapi.model

data class CharacterModel(
	val characterModel: List<CharacterModelItem?>? = null
)

data class CharacterModelItem(
	val birthday: String? = null,
	val img: String? = null,
	val betterCallSaulAppearance: List<Any?>? = null,
	val occupation: List<String?>? = null,
	val appearance: List<Int?>? = null,
	val portrayed: String? = null,
	val name: String? = null,
	val nickname: String? = null,
	val charId: Int? = null,
	val category: String? = null,
	val status: String? = null
)

