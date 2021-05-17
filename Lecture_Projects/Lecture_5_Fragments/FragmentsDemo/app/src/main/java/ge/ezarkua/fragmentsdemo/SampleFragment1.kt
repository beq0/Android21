package ge.ezarkua.fragmentsdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class SampleFragment1 : Fragment() {

    private lateinit var button: Button

    lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_sample1, container, false)
        return myView
    }

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button = myView.findViewById(R.id.button2)
        button.setOnClickListener {
            //Log.d("Click", "I am from fragment")
            //Toast.makeText(activity, "I am from fragment", Toast.LENGTH_SHORT).show()
            //childFragmentManager.beginTransaction().add(R.id.fragmentLayout, SampleFragment2()).commit()
        }
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.button2)
        button.setOnClickListener {
            //Log.d("Click", "I am from fragment")
            //Toast.makeText(activity, "I am from fragment", Toast.LENGTH_SHORT).show()
            //childFragmentManager.beginTransaction().add(R.id.fragmentLayout, SampleFragment2(),"F2").commit()
        }
    }

}