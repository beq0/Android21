package ge.ezarkua.apidemo

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var imgTest : ImageView

    var jsonString = "[\n" +
            "  {\n" +
            "    \"userId\": 1,\n" +
            "    \"id\": 1,\n" +
            "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
            "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\",\n" +
            "    \"comments\":[\n" +
            "      {\n" +
            "       \"commentId\": 1\n" +
            "    },\n" +
            "    {\n" +
            "       \"commentId\": 2\n" +
            "    }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"userId\": 1,\n" +
            "    \"id\": 2,\n" +
            "    \"title\": \"qui est esse\",\n" +
            "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
            "  }]"

    var url = "https://neilpatel.com/wp-content/uploads/2017/09/image-editing-tools.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgTest = findViewById(R.id.imgTest)


        //var jsonObject = JSONObject(jsonString)

        var jsonArray = JSONArray(jsonString)
        var arrayObj = jsonArray[0] as JSONObject

        var commentArray = arrayObj.getJSONArray("comments")
        var firstComment = commentArray[0] as JSONObject
        val title = firstComment.getInt("commentId")


        findViewById<TextView>(R.id.textView).setText(("First comment id is$title"))



        Glide.with(this)
            .load(url)
            .circleCrop()
            /*.addListener(object: RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

            })*/
            .into(imgTest)

        getComments()
    }

    fun getPosts(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var fakeApi = retrofit.create(FakeApi::class.java)

        var postsCall = fakeApi.getPosts(1)

        postsCall.enqueue(object : Callback<PostModel>{
            override fun onFailure(call: Call<PostModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                if(response.isSuccessful){
                    Log.d("data", response.body().toString())
                }
            }

        })
    }


    fun getComments(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var fakeApi = retrofit.create(FakeApi::class.java)

        var postsCall = fakeApi.getComment(1)

        postsCall.enqueue(object : Callback<List<CommentModel>>{
            override fun onFailure(call: Call<List<CommentModel>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<CommentModel>>, response: Response<List<CommentModel>>) {
                if(response.isSuccessful){
                    Log.d("data", response.body().toString())
                }
            }

        })
    }
}

