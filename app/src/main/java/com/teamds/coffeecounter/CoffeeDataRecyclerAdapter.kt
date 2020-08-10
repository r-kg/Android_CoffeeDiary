package com.teamds.coffeecounter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teamds.coffeecounter.databinding.RecyclerviewCoffeeDataItemBinding
import com.teamds.coffeecounter.model.coffeedb.CoffeeData


class CoffeeDataRecyclerAdapter(var itemSet: List<CoffeeData>) : RecyclerView.Adapter<CoffeeDataRecyclerAdapter.ViewHolder>() {

    var recylcerList : List<CoffeeData> = itemSet

    public inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var textCoffee : TextView = itemView.findViewById(R.id.text_coffee_name)
        var textDate : TextView = itemView.findViewById(R.id.text_date)
        var textSize : TextView = itemView.findViewById(R.id.text_size)
        var textShot : TextView = itemView.findViewById(R.id.text_coffee_shot)
        var textCaffeine : TextView = itemView.findViewById(R.id.text_caffeine)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_coffee_data_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textCoffee.text = "${recylcerList[position].coffee}"
        holder.textDate.text = "${recylcerList[position].date}"
        holder.textSize.text = "${recylcerList[position].size}"
        holder.textShot.text = "${recylcerList[position].shot}"
        holder.textCaffeine.text = "${recylcerList[position].caffeine}"
    }

    override fun getItemCount(): Int {
        return recylcerList.size
    }

    fun setRecyclerList(itemSet: List<CoffeeData>){
        recylcerList = itemSet
    }
}