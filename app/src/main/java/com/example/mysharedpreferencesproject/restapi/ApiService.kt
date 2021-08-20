package com.example.mysharedpreferencesproject.restapi


import com.example.mysharedpreferencesproject.restapi.model.CharacterModelItem
import com.example.mysharedpreferencesproject.restapi.model.UserModelItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/api/characters")
    fun getUser(): Call<List<CharacterModelItem>>

}