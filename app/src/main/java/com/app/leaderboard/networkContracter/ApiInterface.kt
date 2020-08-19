package com.app.leaderboard.networkContracter

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @GET("dummy-app-data.json")
    fun getCompany(): Call<JsonObject>
}
