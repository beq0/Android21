package ge.ezarkua.intentsdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class TempActivity : AppCompatActivity() {

    lateinit var activityDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        activityDescription = findViewById(R.id.text_temp_activity)

        activityDescription.text = intent.getStringExtra("text")
    }

    override fun onBackPressed() {
       // super.onBackPressed()
    }

    fun onNewTempActivityClicked(view: View){
        var intent = Intent(this, Temp2Activity::class.java)
        startActivity(intent)
    }

    fun closeActivity(view: View){
        var intent = Intent()
        intent.putExtra("result", "closed by click")

        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("result", "closed by click")
        })
        finish()
    }
}