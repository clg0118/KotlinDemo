package com.ly.clg.kotlindemo

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Author: clg48764.
 * Date: 2019/1/21,16:50.
 */
class StringRvAdapter (val items: List<String>): RecyclerView.Adapter<StringRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = items[position]
    }


    class ViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
}