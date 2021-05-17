package ge.ezarkua.fragmentsdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter2() : RecyclerView.Adapter<PagerViewHolder>(){
    var list = arrayListOf<String>("1", "2", "3", "4", "5")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.itemText.text = list.get(position)
    }

}

class PagerViewHolder(view: View): RecyclerView.ViewHolder(view){
    var itemText = view.findViewById<TextView>(R.id.itemText)
}