package ge.ezarkua.apidemo

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface FakeApi {

    @GET("/posts/{id}")
    fun getPosts(@Path("id") id: Int): Call<PostModel>

   // @POST("/posts/{id}")
   // fun setPost(@Path("id") id: Int, @Body body: PostModel): Call<PostModel>


    @GET("/comments")
    fun getComment(@Query("postId") id: Int): Call<List<CommentModel>>


}