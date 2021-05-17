package ge.ezarkua.intentsdemo

import retrofit2.Call
import retrofit2.http.GET

interface YesNoApi {

    @GET("/api")
    fun getAnswer(): Call<AnswerModel>
}