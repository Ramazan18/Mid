package com.example.ramazan.mid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter(var context: Context, var dataset: ArrayList<Todo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TodoViewHolder(LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todo = dataset[position]

        holder.itemView.titleText.text = todo.title
        holder.itemView.dateText.text = todo.date
    }

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                /*context.startActivity(Intent(context, TodoDetailsActivity::class.java)
                        .putExtra("todo", dataset[adapterPosition]))*/


                val act = context as Activity

                act.startActivityForResult(Intent(context, TodoDetailsActivity::class.java)
                        .putExtra("todo", dataset[adapterPosition]), 2)
            }
        }
    }
}