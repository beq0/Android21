package ge.ezarkua.intentsdemo

import android.app.Activity
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var etQuestion: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etQuestion = findViewById(R.id.et_question)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            Toast.makeText(this, data?.getStringExtra("result"), Toast.LENGTH_SHORT).show()
        }
    }

    fun onNewActivityClicked(view: View){
        var intent = Intent(this, TempActivity::class.java)
        intent.putExtra("text", "I am from first activity")
        startActivityForResult(intent, 100)
    }

    fun onAskClicked(view: View){
        var questionString = etQuestion.text.toString()
        if(questionString.isNotEmpty()) {
         /*   startActivity(Intent(this, RandomAnswerActivity::class.java).apply {
                putExtra(RandomAnswerActivity.EXTRA_QUESTION, questionString)
            })*/

            RandomAnswerActivity.start(this, questionString)
        }
    }

    fun onOpenButtonClicked(view: View){
       // var intent = Intent(ACTION_VIEW, Uri.parse("https://google.com"))
        //startActivity(intent)

        var intent = Intent(ACTION_DIAL)
        intent.setData(Uri.parse("tel:12345678"))
        //startActivity(intent)
        var chooserIntent = Intent.createChooser(intent, null)
        startActivity(chooserIntent)
    }


}