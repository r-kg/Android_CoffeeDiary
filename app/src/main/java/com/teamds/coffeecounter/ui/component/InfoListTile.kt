package com.teamds.coffeecounter.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.teamds.coffeecounter.R

class InfoListTile @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    var leading : ImageView
    var title : TextView
    var prefix : TextView
    var value : TextView
    var suffix : TextView

    init {
        val v : View = inflate(context, R.layout.info_list_tile, this)

        leading = v.findViewById(R.id.tile_leading)
        title = v.findViewById(R.id.tile_title)
        prefix = v.findViewById(R.id.tile_prefix)
        value = v.findViewById(R.id.tile_value)
        suffix = v.findViewById(R.id.tile_suffix)

        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        attrs ?: return

        val attributeValues = context.obtainStyledAttributes(attrs, R.styleable.InfoListTile)
        with(attributeValues) {
            try {
                leading.setImageDrawable(getDrawable(R.styleable.InfoListTile_leading))
                title.text = getString(R.styleable.InfoListTile_title)
                prefix.text = getString(R.styleable.InfoListTile_prefix)
                value.text = getString(R.styleable.InfoListTile_value)
                suffix.text = getString(R.styleable.InfoListTile_suffix)
            } finally {
                recycle()
            }
        }
    }
}