package com.teamds.coffeecounter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.domain.entity.Page

class HomeGridAdapter(private var context: Context?, private var pageList: ArrayList<Page>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val page = pageList[position]
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val pageView : View = inflater.inflate(R.layout.item_home_grid, null)

        return pageView
    }

    override fun getCount(): Int {
        return pageList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}