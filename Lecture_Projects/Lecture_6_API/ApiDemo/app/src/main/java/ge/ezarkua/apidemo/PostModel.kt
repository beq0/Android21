package ge.ezarkua.apidemo

import com.google.gson.annotations.SerializedName

data class PostModel (val id: Int,
                      val userId: Int,
                      @SerializedName("title")
                      val postTitle: String
//,
 //                     val comments: List<FakeCommentClass>
)

data class PostsResponseModel(val list: List<PostModel>)

data class CommentModel(val id: Int, val name: String)

data class FakeCommentClass(val commentId: Int)