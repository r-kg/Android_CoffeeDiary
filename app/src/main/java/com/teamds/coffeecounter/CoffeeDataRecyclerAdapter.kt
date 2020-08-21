package com.teamds.coffeecounter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teamds.coffeecounter.databinding.RecyclerviewCoffeeDataItemBinding
import com.teamds.coffeecounter.model.coffeedb.CoffeeData
import java.time.format.DateTimeFormatter
import java.util.*


class CoffeeDataRecyclerAdapter(var list: List<CoffeeData>) : RecyclerView.Adapter<CoffeeDataRecyclerAdapter.ViewHolder>() {

    private var recylcerList : List<CoffeeData> = list

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
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

        val coffeeText = arrayOf("아메리카노","라떼/카푸치노","에스프레소","콜드브루/드립","디카페인","카페인 음료")
        val sizeText = arrayOf("캔/Short","톨/Small","그란데/Medium","벤티/Large","리터/1L+")

        holder.textCoffee.text = coffeeText[recylcerList[position].coffee]
        holder.textDate.text = recylcerList[position].date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH시 mm분", Locale.KOREA))
        holder.textSize.text = sizeText[recylcerList[position].size]
        holder.textShot.text = when(recylcerList[position].shot){
            0 -> ""
            else -> "+${recylcerList[position].shot} 샷"
        }
        holder.textCaffeine.text = "${recylcerList[position].caffeine}mg"
    }

    override fun getItemCount(): Int {
        return recylcerList.size
    }

    fun setRecyclerList(list: List<CoffeeData>){
        recylcerList = list
    }
}