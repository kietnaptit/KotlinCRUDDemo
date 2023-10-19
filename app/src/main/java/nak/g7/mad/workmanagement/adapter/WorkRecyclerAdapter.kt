package nak.g7.mad.workmanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import nak.g7.mad.workmanagement.R
import nak.g7.mad.workmanagement.model.Work

class WorkRecyclerAdapter (val workList: List<Work>, val itemClickListener: (Work) -> Unit) : RecyclerView.Adapter<WorkRecyclerAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var genderImage: ImageView = view.findViewById<ImageView>(R.id.genderImage)
        var workName: TextView = view.findViewById<TextView>(R.id.workName)
        var workDes: TextView = view.findViewById<TextView>(R.id.workDes)
        var workDate: TextView = view.findViewById<TextView>(R.id.workDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_work, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return workList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val work = workList[position]
        holder.workName.text = work.name
        holder.workDes.text = work.des
        holder.workDate.text = work.date
        if(work.gender.equals("Male")){
            holder.genderImage.setImageResource(R.drawable.man)
        }else if(work.gender.equals("Female")){
            holder.genderImage.setImageResource(R.drawable.woman)
        }
        holder.itemView.setOnClickListener {
            itemClickListener(work)

        }

    }
}