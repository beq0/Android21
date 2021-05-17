package ge.ezarkua.intentsdemo

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomAnswerActivity : AppCompatActivity() {
    private val BASE_URL = "https://yesno.wtf/"

    lateinit var txtQuestion: TextView
    lateinit var txtAnswer: TextView
    lateinit var imgAnswer: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_answer)

        initView()
        getResult()
    }

    fun initView() {
        txtAnswer = findViewById(R.id.txt_answer)
        txtQuestion = findViewById(R.id.txt_question)
        imgAnswer = findViewById(R.id.img_answer)

        txtQuestion.text = intent.getStringExtra(EXTRA_QUESTION)
    }

    fun getResult() {
        var retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var answerApi = retrofit.create(YesNoApi::class.java)

        var answerCall = answerApi.getAnswer()

        answerCall.enqueue(object : Callback<AnswerModel> {
            override fun onFailure(call: Call<AnswerModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<AnswerModel>, response: Response<AnswerModel>) {
                if (response.isSuccessful) {
                    var result = response.body()
                    txtAnswer.text = result?.answer

                    Glide.with(this@RandomAnswerActivity).asGif().load(result?.image)
                        .into(imgAnswer)

                    findViewById<LinearLayout>(R.id.content).visibility = View.VISIBLE
                    findViewById<ProgressBar>(R.id.loader).visibility = View.GONE

                }
            }

        })


    }

    companion object{
        const val EXTRA_QUESTION = "question"

        fun start(context: Context, questionString: String){
            context.startActivity(Intent(context, RandomAnswerActivity::class.java).apply {
                putExtra(EXTRA_QUESTION, questionString)
            })
        }
    }
}