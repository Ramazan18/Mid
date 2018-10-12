package com.example.ramazan.mid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_done.*
import kotlin.concurrent.thread

class DoneFragment : Fragment() {

    lateinit var user: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_done, container, false)

        val sharedPref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        user = sharedPref!!.getString("login", "0")
        val myDB = TodoDatabase.getInstance(activity!!)

        loadData(myDB!!)

        return root
    }

    fun loadData(myDB: TodoDatabase) {
        val dataset: ArrayList<Todo> = ArrayList()
        thread {
            val list = myDB.todoDao().getAll()
            for (i in 0..(list.size - 1)) {
                if (list[i].done && list[i].user == user) {
                    dataset.add(list[i])
                }
            }

            onDataLoaded(dataset)
        }
    }

    fun onDataLoaded(dataset: ArrayList<Todo>) {
        activity!!.runOnUiThread {
            doneRecycler.layoutManager = LinearLayoutManager(activity)
            doneRecycler.adapter = TodoAdapter(activity!!, dataset)
        }
    }
}