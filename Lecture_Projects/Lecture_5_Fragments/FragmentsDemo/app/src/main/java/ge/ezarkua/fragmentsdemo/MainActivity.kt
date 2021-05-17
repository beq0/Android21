package ge.ezarkua.fragmentsdemo

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var viewpager: ViewPager2

    private var fragment1 = SampleFragment1()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("LifeCycle", "activity created")

        viewpager = findViewById(R.id.viewPager)
        viewpager.adapter = ViewPagerAdapter(this)
        //var adapter = ViewPagerAdapter2()
        //viewpager.adapter = adapter
        //adapter.notifyDataSetChanged()
        //viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        button = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button.setOnClickListener {
            Log.d("Click", "I am from activity")
            Toast.makeText(this, "I am from activity", Toast.LENGTH_SHORT).show()
           var transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragment1)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        button2.setOnClickListener {
            Log.d("Click", "I am from activity")
           // Toast.makeText(this, "I am from activity", Toast.LENGTH_SHORT).show()

            var containerId = if(resources.configuration.orientation == ORIENTATION_PORTRAIT){
                R.id.fragmentContainer
            }else{
                R.id.fragmentContainer2
            }

           var transaction = supportFragmentManager.beginTransaction()
            transaction.add(containerId, SampleFragment2())
            //transaction.remove(fragment1)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("LifeCycle", "activity started")
    }

    override fun onResume() {
        super.onResume()

        Log.d("LifeCycle", "activity resumed")
    }

    override fun onPause() {
        super.onPause()

        Log.d("LifeCycle", "activity paused")
    }

    override fun onStop() {
        super.onStop()

        Log.d("LifeCycle", "activity stopped")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("LifeCycle", "activity destroyed")
    }
}